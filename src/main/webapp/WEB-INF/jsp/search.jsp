<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="styles.jsp"%>
<title>Main page</title>
</head>
<body>
	<%@ include file="menu.jsp"%>
	<form method="get" action="search">
		<input name="searchString" id="searchStringBox" value="">
		<input type="submit" id="filterButton" value="Filtreeri"> <br/>
		<table class="listTable" id="listTable">
			<thead>
				<tr>
					<th scope="col">Eesnimi</th>
					<th scope="col">Perekonnanimi</th>
					<th scope="col">Kood</th>
					<th scope="col"></th>
				</tr>
			</thead>
			<c:forEach items="${persons}" var="person">
				<tr>
					<td>
						<div>
							<a href="view/${person.code}" id="view_${person.code}" >${person.givenName}</a>
						</div>
					</td>
					<td>${person.sureName}</td>
					<td>${person.code}</td>
					<td><a id="delete_${person.id}" href="delete/${person.id}">Kustuta</a></td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>
