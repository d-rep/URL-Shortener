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
    mvn clean package


Running
-------
The above commands will produce a WAR file in the directory `url-shortener-web/target/` that can be deployed to any recent Java application server (Servlet 2.4 or greater).

Alternatively, you can simply run it like this from the command line, which uses embedded Tomcat 6.0.35:

    cd url-shortener-web
    mvn tomcat6:run

Then open your browser to this address: http://localhost:8080/url-shortener-web/


Editing
-------
If you want to use Eclipse as your IDE, then simply run the following under your URL-Shortener directory:

    cd url-shortener-domain
    mvn eclipse:eclipse -DdownloadSources=true -DdownloadJavadocs=true

    cd ..
    cd url-shortener-web
    mvn eclipse:eclipse -DdownloadSources=true -DdownloadJavadocs=true -Dwtpversion=2.0

Then you will be able to import the projects into Eclipse.

Alternatively, if you use _m2eclipse_, then you can simply click File -> Import... -> Existing Maven Projects.


Technologies
------------
This is a web application written in Java.  It illustrates how to use [Struts 2] and [Spring 3.1] frameworks.  Struts Actions and Tags handle the display logic, and Spring dependency injection is used for services and wiring things together.

It uses JPA 2.0 for data persistence, with help from the [Spring Data JPA] project.  Validation is done using [JSR 303].


Deploying
---------
For testing purposes, this application needs only Maven to run.  However, for production usage, you will need a full-fledged database and application server.


### Database ###
On your application server, you should create a JNDI datasource with the name `jdbc/url_test`.  By default, drivers are included for [MySQL] 5.

Next, make sure to remove the H2 configuration file (`spring-h2-database.xml`) from this file: `url-shortener-web/src/main/webapp/WEB-INF/web.xml`

### App Server ###
This application uses JavaEE 6 API's, but it assumes you will be deploying to a simple servlet container like [Apache Tomcat], so implementation libraries are included by default.

**The default configuration will build a WAR file that is _not_ appropriate for a full-fledged JavaEE 6 server!**  For instance, if you are using IBM Websphere 8, then the Java Persistence API 2.0 (JSR 317) and the Bean Validation API (JSR 303) dependencies will already be provided, so you don't need Maven to include those in the WAR.

Just edit this file -- `url-shortener-build/pom.xml` -- and change the `<scope/>` of hibernate-entitymanager from "runtime" to "provided".  Note: this is untested.  I made a design decision to lock in hibernate-validator as the JSR 303 implementation and use Hibernate-specific validators like @NotBlank and @URL, so code changes will be required if that's not your provider.




[Drew Repasky]: http://twitter.com/drewrepasky
[Apache License, Version 2.0]: http://www.apache.org/licenses/LICENSE-2.0.html
[Java Development Kit]: http://www.oracle.com/technetwork/java/javase/downloads/index.html
[Apache Maven]: http://maven.apache.org/download.html
[MySQL]: http://dev.mysql.com/downloads/
[Struts 2]: http://struts.apache.org/2.3.1.1/docs/guides.html
[Spring 3.1]: http://static.springsource.org/spring/docs/3.1.0.RELEASE/reference/html/
[Apache Tomcat]: http://tomcat.apache.org/
[JSR 303]: http://beanvalidation.org/1.0/spec/
[Spring Data JPA]: http://www.springsource.org/spring-data/jpa

