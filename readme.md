This project requires Maven to build and run, and a JDK.

Build it like this:
cd url-shortener-build
mvn clean install

Run it like this:
cd url-shortener-web
mvn jetty:run

Then open your browser to this address:
http://localhost:8080/url-shortener-web/SaveUrlForm.action
