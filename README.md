Online-Judge-servlet-java
==========
	
[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](http://shaykhsiddique.me)

Online Judge a dynamic web *Maven Project*. Used Servlet - Java for UI used Bootstrap4 and freemarker template engine . Contest will be hosted with acm standard contest rules.

Description
--------------

*Maven Dependency:*
- **servlet-spec**- `javax.servlet:javax.servlet-api`
- **junit4**- `junit:junit`
- **mysql-connector-j**- `mysql:mysql-connector-java`
- **freemarker-osgi**- `org.freemarker:freemarker`
- **jBCrypt**- `org.mindrot:jbcrypt`


Compiler works with linux server system compiler. Supported languages are:

- C
- C++
- JAVA
- PYTHON

Database class contains the mysql server location. Path of database class: `src/main/me.shaykhsiddique/Database.java`

```java
	private String database_name = "onlinejudge2019";    			//set database name
	private String host = "jdbc:mysql://localhost:3306/";    			//set host url
	private String user = "shaykh";    						//set username for database
	private String password = "******";    					//set password for database

```

Class `JudgeServer.java` will access read and write permission, for compiling and judging inputs and outputs. This process is started in `src/me.shaykhsiddique.JudgeServer/BackgroundJudge.java`.


*Added Some Screenshots:*

**Homepage**

![homepage](https://user-images.githubusercontent.com/18369069/62414304-68421e00-b63b-11e9-83a9-f59c6347b2d6.png)

**Login**
![login](https://user-images.githubusercontent.com/18369069/62414335-c7a02e00-b63b-11e9-957c-d781dfd41a6c.png)
**Registration**
![registration](https://user-images.githubusercontent.com/18369069/62414337-ca9b1e80-b63b-11e9-95cd-7ef9728d6c57.png)
**Contest**
![Contest](https://user-images.githubusercontent.com/18369069/62414743-988cbb00-b641-11e9-8ace-6fce23b7eabd.png)
**Problems**
![problems](https://user-images.githubusercontent.com/18369069/62415084-2f5b7680-b646-11e9-8ad1-14e944eb0f16.png)
**Problem Details**
![problemDetails](https://user-images.githubusercontent.com/18369069/62415065-e0addc80-b645-11e9-9312-c65a811a1920.png)
**Submissions**
![submissions](https://user-images.githubusercontent.com/18369069/62414755-d2f65800-b641-11e9-8d3a-a318d704513b.png)

Documentation
-----------
**Linux-Server:**
- Download *Apache Tomcat 8* webserver or newer version from here- [tomcat.apache.org](https://tomcat.apache.org/).
- Make sure have installed `JAVA`, `javac`, `gcc`, `g++`, `python`. [ *judgeserver used system compiler* ]
- For easy installation/testing install *Eclipse Java EE IDE* [eclipse.org](https://www.eclipse.org/).
- Download Git repository into *Apache Tomcat 8*.

```sh
	$ git clone https://github.com/shaykhsiddique/Online-Judge-servlet-java.git
```
- If configure with *Eclipse* Make sure the server is working correctly.
- Change `Database.java` with database configuration. `src/main/me.shaykhsiddique/Database.java`
- `onlinejudge2019.sql` contains the database schema, run it on mysql server.

```sh
	Admin username: shaykhsiddique
	Admin password: 123456
```
- Maven build
- Maven install.

The web application is ready to go......

For any Query: [shaykhsiddiqee@gmail.com](mailto:shaykhsiddiqee@gmail.com)

**Thank You** 
