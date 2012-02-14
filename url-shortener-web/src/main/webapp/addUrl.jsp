<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML>
<html>
   <head>
      <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
      <title>URL Shortener - Add URL</title>
   </head>
   <body>
      <h1>Add New URL</h1>
      <s:form action="add">
         <s:textfield name="shortUrl.fullUrl" label="Please enter a URL to shorten" required="true"/>
         <s:textfield name="shortUrl.shortUrl" label="Custom Alias" required="true"/>
         <s:submit/>
      </s:form>
   </body>
</html>
