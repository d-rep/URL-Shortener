<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML>
<html>
   <head>
      <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
      <title>Add URL</title>
   </head>
   <body>
      <h1>URL List</h1>
      <table cellpadding="0" cellspacing="0">
         <thead>
            <th>Short URL</th>
            <th>Full URL</th>
         </thead>
         <s:iterator value="shortUrlList">
         <tbody>
            <td><s:property value="shortUrl"/></td>
            <td><s:property value="fullUrl"/></td>
         </tbody>
         </s:iterator>
      </table>
   </body>
</html>
