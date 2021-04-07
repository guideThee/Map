<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table>
				<tr>
				<td>id</td>
				<td>用户名</td>
				<td>方案名称</td>
				<td>总距离</td>
				<td>创造时间</td>
				<td>读取</td>
			</tr>
		<c:forEach items="${PLANS }" var="plan">
			<tr>
				<td>${plan.planId }</td>
				<td>${plan.userLoginname }</td>
				<td>${plan.planName }</td>
				<td>${plan.distance }</td>
				<td>${plan.createTime }</td>
				<td><a href="loadPlan?planId=${plan.planId}">读取</a></td>
			</tr>
		</c:forEach>
	</table>	
</body>
</html>