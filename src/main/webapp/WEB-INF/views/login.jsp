<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<html>
 <head>
  <title>Yahoo!!</title>
 </head>
 <body>
      <p><font color="red">${error}</font></p>
      <form action="login" method="POST">
          Name : <input name="login" type="text" /> Password : <input name="password" type="password" /> <input type="submit" />
      </form>
 </body>
</html>
  