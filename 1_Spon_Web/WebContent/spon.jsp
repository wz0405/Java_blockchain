<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<title>Insert title here</title>
</head>
<body>
<h1>${id }님 후원에 감사합니다</h1>
 <br>
 <form action="main" method="post">
 	<input type="hidden" name="sign" value="spon">
 	후원금<input name="amount"> <input type="submit" value="후원하기">
 </form>
</body>
</html>