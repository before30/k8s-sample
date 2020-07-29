TAG="\n\n\033[0;32m\#\#\# "
END=" \#\#\# \033[0m\n"

.PHONY: deploy-dev-mode
deploy-dev-mode: ## deploy dev mode to 9rum (prerequisites: brew install skaffold)
	@echo ${TAG}starting run${END}
	@skaffold dev --trigger polling

.PHONY: deploy
deploy: ## deploy to 9rum (prerequisites: brew install skaffold)
	@echo ${TAG}starting run${END}
	@skaffold run
	@echo ${TAG}completed${END}

.PHONY: help
help:
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | \
		awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'


.DEFAULT_GOAL = help
