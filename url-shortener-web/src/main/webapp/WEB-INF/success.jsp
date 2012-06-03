<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML>
<html>
   <head>
      <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
      <title>URL Successfully Shortened</title>
      <link rel="stylesheet" type="text/css" href="static/styles.css"/>
   </head>
   <body>
      <h1>Success!</h1>
      Your new URL has been created: <s:a href="%{shortUrl.shortUrl}" target="_blank"><s:url value="%{shortUrl.shortUrl}" forceAddSchemeHostAndPort="true"/></s:a>
   </body>
</html>
