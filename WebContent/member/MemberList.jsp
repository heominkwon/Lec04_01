
<%@page import="heo.vo.Member"%>
<%@page import="java.util.ArrayList"%>
<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 목록</title>
</head>
<body>
<jsp:include page="/Header.jsp"/>
<h1>회원목록</h1>
<p><a href='add.do'>신규 회원</a></p>
<%
// header의 jsp:useBean 정의로 변경, c태크 사용
//ArrayList<Member> members = (ArrayList<Member>)request.getAttribute("members");
/* for(Member member : members) { */
%>
<c:forEach var="member" items="${members}">
${member.no},
<a href='update.do?no=${member.no}'>${member.name}</a>,
${member.email},
${member.createdDate}
<a href='delete.do?no=${member.no}'>[삭제]</a><br>
</c:forEach>
<jsp:include page="/Tail.jsp"/>
</body>
</html>