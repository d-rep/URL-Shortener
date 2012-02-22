URL Shortener
=============

This is a simple URL Shortener service, where you can enter a long URL along with an abbreviated version, then the short URL can be used in place of the full/expanded one.

Copyright &copy; 2012-, [Drew Repasky].  Licensed under [Apache License, Version 2.0].


Software Requirements / Prerequisites
-------------------------------------
This project requires [Apache Maven] 3 and a Java Development Kit (JDK) v1.6 or newer to compile the code.  This does not require a stand-alone Java application server be installed.

The default database is configured to be [MySQL] 5.  *You must update this file with your database settings before the project will work*: `url-shortener-domain/src/main/resources/dev.properties`


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

Alternatively, you can simply run it like this from the command line, which uses embedded Jetty:

    cd url-shortener-web
    mvn jetty:run

In place of Jetty, the project is also setup to use embedded Tomcat 6.0.35:

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


Application Server
------------------
This application uses JavaEE 6 API's, but it assumes you will be deploying to a simple servlet container like [Apache Tomcat], so implementation libraries are included by default.

**The default configuration will build a WAR file that is _not_ appropriate for a full-fledged JavaEE 6 server!**  For instance, if you are using IBM Websphere 8, then the Java Persistence API 2.0 (JSR 317) and the Bean Validation API (JSR 303) dependencies will already be provided, so you don't need Maven to include those in the WAR.

Just edit this file -- `url-shortener-build/pom.xml` -- and change the `<scope/>` of hibernate-entitymanager from "runtime" to "provided".  Note: this is untested.  I made a design decision to lock in hibernate-validator as the JSR 303 implementation and use Hibernate-specific validators like @NotBlank and @URL, so code changes will be required if that's not your provider.

You should also configure in a JNDI datasource and rip out the Apache DBCP (database connection pooling) configuration from this file: `url-shortener-domain/src/main/resources/spring-dataSource.xml`


[Drew Repasky]: http://twitter.com/drewrepasky
[Apache License, Version 2.0]: http://www.apache.org/licenses/LICENSE-2.0.html
[Apache Maven]: http://maven.apache.org/download.html
[MySQL]: http://dev.mysql.com/downloads/
[Struts 2]: http://struts.apache.org/2.3.1.1/docs/guides.html
[Spring 3.1]: http://static.springsource.org/spring/docs/3.1.0.RELEASE/reference/html/
[Apache Tomcat]: http://tomcat.apache.org/
[JSR 303]: http://beanvalidation.org/1.0/spec/
[Spring Data JPA]: http://www.springsource.org/spring-data/jpa

