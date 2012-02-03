<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
   <head>
      <title>Add URL</title>
   </head>
   <body>
      <h1>URL was not saved due to the following problems:</h1>
      <ul>
         <s:iterator value="violations">
            <li><s:property/></li>
         </s:iterator>
      </ul>
   </body>
</html>
