URL Shortener
=============

This is a simple URL Shortener service, where you can enter a long URL along with an abbreviated version, then the short URL can be used in place of the full/expanded one.

Copyright &copy; 2012-, [Drew Repasky].  Licensed under [Apache License, Version 2.0].


Software Requirements / Prerequisites
-------------------------------------
This project requires Maven 3 and a Java Development Kit (JDK) v1.6 or newer to compile the code.

The default database is configured to be MySQL 5.  You must update this file with your database settings: `url-shortener-domain/src/main/resources/dev.properties`


Building
--------
The latest and greatest source of the URL Shortener can be cloned from [GitHub].

Build the code with the following commands, which will produce a WAR file that can be deployed to any recent Java application server.

    cd url-shortener-build
    mvn clean install


Or you can simply run it like this from the command line, which uses Jetty:

    cd url-shortener-web
    mvn jetty:run

Then open your browser to this address: http://localhost:8080/url-shortener-web/


Technologies
------------
This is a web application written in Java.  It illustrates how to use Struts 2 and Spring 3.1 frameworks.  Struts Actions and Tags handle the display logic, and Spring dependency injection is used for services and wiring things together.

It uses JPA 2.0 for data persistence, with help from the [Spring Data JPA] project.  Validation is done using [JSR 303].


Application Server
------------------
This application uses JavaEE 6 API's, but it assumes you will be deploying to a simple servlet container like [Apache Tomcat], so implementation libraries are included by default.

**The default configuration will build a WAR file that is _not_ appropriate for a full-fledged JavaEE 6 server!**  For instance, if you are using IBM Websphere 8, then the Java Persistence API 2.0 (JSR 317) and the Bean Validation API (JSR 303) dependencies will already be provided, so you don't need Maven to include those in the WAR.

Just edit this file -- `url-shortener-build/pom.xml` -- and change the <scope/> of hibernate-entitymanager and hibernate-validator from "runtime" to "provided".  (Note: this is untested.)

You should also configure in a JNDI datasource and rip out the Apache DBCP (database connection pooling) configuration from this file: `url-shortener-domain/src/main/resources/spring-dataSource.xml`



[Drew Repasky]: http://twitter.com/drewrepasky
[Apache License, Version 2.0]: http://www.apache.org/licenses/LICENSE-2.0.html
[Apache Tomcat]: http://tomcat.apache.org/
[JSR 303]: http://en.wikipedia.org/wiki/JSR_303
[Spring Data JPA]: http://www.springsource.org/spring-data/jpa
[GitHub]:https://github.com/d-rep/

