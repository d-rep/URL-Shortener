URL Shortener
=============

This is a simple URL Shortener service, where you can enter a long URL along with an abbreviated version, then the short URL can be used in place of the full/expanded one.

Copyright &copy; 2012-, [Drew Repasky].  Licensed under [Apache License, Version 2.0].


Software Requirements / Prerequisites
-------------------------------------
This project requires a [Java Development Kit] v1.6 or newer and [Apache Maven] 3 to compile the source code.

If you simply want to test drive the application, it does not require a stand-alone database or Java application server.  Out of the box, it will use an in-memory H2 database instance and can be started on an embedded version of Tomcat, using a Maven command.


Building
--------
First, download the latest and greatest source code:

    git clone git://github.com/d-rep/URL-Shortener.git

Build the code with the following commands:

    cd URL-Shortener/url-shortener-build
    mvn clean install


Running
-------
The above commands will produce a WAR file in the directory `url-shortener-web/target/` that can be deployed to any recent Java application server (Servlet 2.4 or greater).

Alternatively, you can simply run it like this from the command line, which uses embedded Tomcat 7 as the app server and H2 as the database:

    cd url-shortener-web
    mvn tomcat7:run

Then open your browser to this address: [http://localhost:8080/url-shortener-web/](http://localhost:8080/url-shortener-web/)


Editing
-------
You don't have to use an IDE, but if you want to use Eclipse, then you can import the projects into Eclipse.  Click File -> Import -> Maven -> Existing Maven Projects -> Select root directory: the URL-Shortener location -> click Finish

Now you can go to the Servers view, and create a new Tomcat v7.0 Server.  Then you'll be able to right-click the url-shortener-web project -> Run As -> Run on Server


Technologies
------------
This is a web application written in Java.  It illustrates how to use [Struts 2] and [Spring 3.2] frameworks.  Struts Actions and Tags handle the display logic, and Spring dependency injection is used for services and wiring things together.

It uses JPA 2.0 for data persistence, with help from the [Spring Data JPA] project.  Validation is done using [JSR 303].


Deploying
---------
For testing purposes, this application needs only Maven to run.  However, for production usage, you will need a full-fledged database and application server.


### Database ###
On your application server, you should create a JNDI datasource with the name `jdbc/url_test`.  Drivers are bundled in this application for [MySQL] 5 and [H2].

If you want to use MySQL, then the JNDI entry is all you need. You can optionally remove the H2 configuration entry (`spring-h2-database.xml`) referenced inside this file: `url-shortener-web/src/main/webapp/WEB-INF/web.xml`.

If you want to use a different database, then add the driver to your `pom.xml` files (in the build and domain projects) and then update `env-prod.properties` with the appropriate database dialect.

### App Server ###
This application uses JavaEE 6 API's, but it assumes you will be deploying to a simple servlet container like [Apache Tomcat], so implementation libraries are included by default.

**The default configuration will build a WAR file that is _not_ appropriate for a full-fledged JavaEE 6 server!**  For instance, if you are using IBM Websphere 8, then the Java Persistence API 2.0 (JSR 317) and the Bean Validation API (JSR 303) dependencies will already be provided, so you don't need Maven to include those in the WAR.

Just edit this file -- `url-shortener-build/pom.xml` -- and change the `<scope/>` of hibernate-entitymanager from "runtime" to "provided".  Note: this is untested.  I made a design decision to lock in hibernate-validator as the JSR 303 implementation and use Hibernate-specific validators like @NotBlank and @URL, so code changes will be required if that's not your provider.




[Drew Repasky]: https://www.linkedin.com/in/drewrepasky
[Apache License, Version 2.0]: http://www.apache.org/licenses/LICENSE-2.0.html
[Java Development Kit]: http://www.oracle.com/technetwork/java/javase/downloads/index.html
[Apache Maven]: http://maven.apache.org/download.html
[MySQL]: http://dev.mysql.com/downloads/
[H2]: http://www.h2database.com/html/main.html
[Struts 2]: http://struts.apache.org/release/2.3.x/docs/guides.html
[Spring 3.2]: http://docs.spring.io/spring/docs/3.2.4.RELEASE/spring-framework-reference/html/
[Apache Tomcat]: http://tomcat.apache.org/
[JSR 303]: http://beanvalidation.org/1.0/spec/
[Spring Data JPA]: http://docs.spring.io/spring-data/jpa/docs/1.4.1.RELEASE/reference/html/

