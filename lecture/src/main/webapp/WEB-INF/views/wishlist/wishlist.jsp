<%@page import="wish_list.ClassInfo"%>
<%@page import="wish_list.Wish"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script>

function addToWishList(classId, studentId){
	
	$.ajax({
		type: "post",
		url: "/lecture/wishlist/add",
		data: { classId: classId, studentId: studentId },
		success: function(data){
			console.log(data);
			location.reload();
		},
		error: function(err){
			console.log(err);
		}
	});
	
}

function removeFromWishList(classId, studentId){
	
	$.ajax({
		type: "post",
		url: "/lecture/wishlist/delete",
		data: { classId: classId, studentId: studentId },
		success: function(data){
			console.log(data);
			location.reload();
		},
		error: function(err){
			console.log(err);
		}
	});
	
}

</script>
<style>
table{
border: 1px solid black;
border-collapse: collapse;
text-align: center;
}
th{
border: 1px solid black;
}
td{
border: 1px solid black;
}
</style>
</head>
<body>

<table>

<tr>
<th>관심등록</th>
<th>학수번호</th>
<th>이수구분</th>
<th>개설학과</th>
<th>교과목명</th>
<th>담당교수</th>
<th>학점</th>
<th>강의시간</th>
<th>강의실</th>
</tr>

<%
ArrayList<ClassInfo> classInfoList = (ArrayList<ClassInfo>)request.getAttribute("classInfoList");
for(ClassInfo classInfo : classInfoList) {
%>

<tr>
<td><button onclick="addToWishList('<%= classInfo.getClassId() %>', '<%= session.getAttribute("id") %>')">등록</button></td>
<td><%= classInfo.getCourseId() %></td>
<td><%= classInfo.getClassification() %></td>
<td><%= classInfo.getDepartmentName() %></td>
<td><%= classInfo.getCourseName() %></td>
<td><%= classInfo.getProfessorName() %></td>
<td><%= classInfo.getCredit() %></td>
<td>
<%
String dayOfWeek = classInfo.getDayOfWeek();
String startTime = classInfo.getStartTime();
String endTime = classInfo.getEndTime();
String classTime = (startTime.equals(endTime)) ? dayOfWeek + "(" + startTime + ")" : dayOfWeek + "(" + startTime +  "-" + endTime + ")";
%>
<%= classTime %>
</td>
<td><%= classInfo.getRoomNo() %></td>
</tr>

<% } %>

</table>

<h2>희망과목 내역</h2>
<p>[ 최소신청학점: 1학점 | 최대신청학점: 19학점 | 신청학점: <%= request.getAttribute("totalCredit") %>학점 ]</p>
<table>

<tr>
<th>삭제</th>
<th>학수번호</th>
<th>이수구분</th>
<th>개설학과</th>
<th>교과목명</th>
<th>담당교수</th>
<th>학점</th>
<th>강의시간</th>
<th>강의실</th>
<th>재수강</th>
</tr>

<%
ArrayList<Wish> wishList = (ArrayList<Wish>)request.getAttribute("wishList");
for(Wish wish : wishList) {
%>

<tr>
<td><button onclick="removeFromWishList('<%= wish.getClassId()%>', '<%= wish.getStudentId() %>')">삭제</button></td>
<td><%= wish.getCourseId() %></td>
<td><%= wish.getClassification() %></td>
<td><%= wish.getDepartmentName() %></td>
<td><%= wish.getCourseName() %></td>
<td><%= wish.getProfessorName() %></td>
<td><%= wish.getCredit() %></td>
<td>
<%
String dayOfWeek = wish.getDayOfWeek();
String startTime = wish.getStartTime();
String endTime = wish.getEndTime();
String classTime = (startTime.equals(endTime)) ? dayOfWeek + "(" + startTime + ")" : dayOfWeek + "(" + startTime +  "-" + endTime + ")";
%>
<%= classTime %>
</td>
<td><%= wish.getRoomNo() %></td>
<td></td>
</tr>

<% } %>

</table>

</body>
</html>