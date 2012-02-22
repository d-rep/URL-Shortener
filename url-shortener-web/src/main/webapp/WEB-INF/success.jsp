<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML>
<html>
   <head>
      <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
      <title>URL Successfully Added</title>
   </head>
   <body>
      <h1>Success!</h1>
      Your new URL has been created.  Test it out by <s:a href="%{shortUrl.shortUrl}" target="_blank">clicking here</s:a>.
   </body>
</html>
