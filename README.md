Heavily referenced this: https://www.javaguides.net/2019/03/login-form-using-jsp-servlet-jdbc-mysql-example.html

First go download Apache TomCat, mine is apache-tomcat-10.1.18-windows-x64 from https://tomcat.apache.org/download-10.cgi. Then download mysql-connector from https://dev.mysql.com/downloads/connector/j/ (select platform independent). Add these two .jar files into ...\webapp\WEB-INF\lib directory.

The file placement is very important, I don't know the specifics but this one worked at least, after a lot of googling. 

For Eclipse IDE, we should create a new Dynamic Web Project to start. It is under New... and Web folder. If you don't see Web, then you need to go to Help --> Install New Software, select one of the options in "Work with:", tick the one with "Web, XML, Java EE and OSGi Enterprise Development". After installing the "Web" should appear when you want to create new project.

Next we need to create a MySQL Database so we can retrieve the data from there. Create schema like login_app and create a users table:
<pre>
  CREATE TABLE users (
    username VARCHAR(45) PRIMARY KEY,
    password VARCHAR(45) NOT NULL,
    name VARCHAR(45) NOT NULL,
    phone_number VARCHAR(45) NOT NULL
);
</pre>

Insert random sample values:
<pre>
  INSERT INTO users (username, password, name, phone_number) VALUES
('john_doe', 'password123', 'John Doe', '123-456-7890'),
('jane_smith', 'letmein', 'Jane Smith', '987-654-3210'),
('alice_wonderland', 'alice123', 'Alice Wonderland', '555-123-4567');
</pre>

Now setup the folders and files, once done you can right click the entire project and select Run As --> Run on Server. Might have to select your TomCat version, mine is 10.1. Your MySQL server should still be running if you didn't close it. 

Now once it starts running our browser will open up url "http://localhost:8080/login-servlet-jdbc-example/" and we will see HTTP Status 404 - Not Found. We can deal with it but for now this is just an example. Go to "http://localhost:8080/login-servlet-jdbc-example/login.jsp" instead, it will load the simple .jsp (Java Server Pages) file. 

We can try to test with John Doe, so user = "john_doe", password = "password123", click Submit. 

We should see:
<pre>
You have logged in successfully
Welcome, John Doe

Your phone number: 123-456-7890
</pre>
