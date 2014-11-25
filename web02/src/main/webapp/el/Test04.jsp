<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>연산자</h1>
<h2>산술 연산자</h2>
\${10 + 20} = ${10 + 20}<br>
\${10 - 20} = ${10 - 20}<br>
\${10 * 20} = ${10 * 20}<br>
\${10 / 20} = ${10 / 20}<br>
\${10 div 20} = ${10 div 20}<br>
\${10 mod 20} = ${10 mod 20}<br>

<h2>논리 연산자</h2>
\${true && false} = ${true && false} <br>
\${true || false} = ${true || false}<br>
\${true and false} = ${true and false}<br>
\${true or false} = ${true or false}<br>
\${!true} = ${!true}<br>
\${not true} = ${not true}<br>

<h2>관계 연산자</h2>
\${10 == 11} = ${10 == 11}<br>
\${10 eq 11} = ${10 eq 11}<br>

\${10 != 11} = ${10 != 11}<br>
\${10 ne 11} = ${10 ne 11}<br>

\${10 < 11} = ${10 < 11}<br>
\${10 lt 11} = ${10 lt 11}<br>

\${10 > 11} = ${10 > 11}<br>
\${10 gt 11} = ${10 gt 11}<br>

\${10 <= 11} = ${10 <= 11}<br>
\${10 le 11} = ${10 le 11}<br>

\${10 >= 11} = ${10 >= 11}<br>
\${10 ge 11} = ${10 ge 11}<br>

</body>
</html>