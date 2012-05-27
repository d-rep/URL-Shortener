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
      <table>
         <thead>
         	<tr>
	            <th>Short URL</th>
	            <th>Full URL</th>
         	</tr>
         </thead>
         <s:iterator value="shortUrlList">
         <tbody>
         	<tr>
	            <td><s:property value="shortUrl"/></td>
	            <td><s:property value="fullUrl"/></td>
         	</tr>
         </tbody>
         </s:iterator>
      </table>
   </body>
</html>
