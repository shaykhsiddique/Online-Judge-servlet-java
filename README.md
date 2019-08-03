Online-Judge-servlet-java
==========

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

**Login**

**Registration**

**Contest**

**Problems**

**Problem Details**

**Submissions**


Documentation
-----------
**Linux-Server:**
- Download *Apache Tomcat 8* webserver or newer version from here- [tomcat.apache.org](https://tomcat.apache.org/).
- Make sure have installed `JAVA`, `javac`, `gcc`, `g++`, `python`. [ *judgeserver used system compiler* ]
- For easy installation/testing install *Eclipse Java EE IDE* [eclipse.org](https://www.eclipse.org/).
- Download Git repository into *Apache Tomcat 8*.

```bash
	$ git clone https://github.com/shaykhsiddique/Online-Judge-servlet-java.git
```
- If configure with *Eclipse* Make sure the server is working correctly.
- Change `Database.java` with database configuration. `src/main/me.shaykhsiddique/Database.java`
- `onlinejudge2019.sql` contains the database schema, run it on mysql server.

```bash
	Admin username: shaykhsiddique
	Admin password: 123456
```
- Maven build
- Maven install.

The web application is ready to go......

For any Query: [shaykhsiddiqee@gmail.com](mailto:shaykhsiddiqee@gmail.com)

**Thank You** 
