[mysql-deployment reference](https://kubernetes.io/docs/tasks/run-application/run-single-instance-stateful-application/#deploy-mysql)

# Database 생성

```bash
$ kubectl run -it --rm --image=mysql:5.6 --restart=Never mysql-client -- mysql -h mysql -ppassword
If you don't see a command prompt, try pressing enter.

mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| mysql              |
| performance_schema |
+--------------------+
3 rows in set (0.00 sec)

mysql> CREATE DATABASE HOME_DB default CHARACTER SET UTF8;
Query OK, 1 row affected (0.00 sec)

mysql> quit
Bye
pod "mysql-client" deleted
```