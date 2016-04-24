<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="styles.jsp"%>
<title>Add person</title>
</head>
<body>
	<%@ include file="menu.jsp"%>
	<c:url value="save" var="theAction" />
	<form:form method="post" action="${theAction}" id="customerForm"
		modelAttribute="personForm">
		<form:input type="hidden" path="person.id" />
		<table class="formTable" id="formTable">
			<tbody>
				<tr>
					<td>Eesnimi:</td>
					<td><form:input id="firstNameBox" path="person.givenName"
							disabled="${personForm.disabled}" /><br /></td>
				</tr>
				<tr>
					<td>Perekonnanimi:</td>
					<td><form:input id="surnameBox" path="person.sureName"
							disabled="${personForm.disabled}" /><br /></td>
				</tr>
				<tr>
					<td>Kood:</td>
					<td><form:input id="codeBox" path="person.code"
							disabled="${personForm.disabled}" /><br /></td>
				</tr>
				<tr>
					<td>Kliendi tüüp:</td>
					<td><form:select id="customerTypeSelect"
							path="person.customerType" items="${personForm.customerGroups}"
							disabled="${personForm.disabled}" /></td>
				</tr>
				<tr>
					<td>Telefonid:</td>
					<td></td>
				</tr>
				<c:forEach items="${personForm.person.phones}" varStatus="status">
					<tr>
						<td>&nbsp;</td>
						<td><input id="phones${status.index}.id"
							name="phones[${status.index}].id" type="hidden" value="">
							<form:select id="phones${status.index}.type"
								path="person.phones[${status.index}].phoneType"
								items="${personForm.phoneTypes}"
								disabled="${personForm.disabled}" /> <form:input
								id="phones${status.index}.value"
								path="person.phones[${status.index}].number"
								disabled="${personForm.disabled}" /> <form:input
								disabled="${personForm.disabled}" class="linkButton"
								id="phones${status.index}.deletePressed"
								path="person.phones[${status.index}].deleteButton" type="submit"
								value="kustuta" /></td>
					</tr>
				</c:forEach>
				<c:if test="${not personForm.disabled}">
					<tr>
						<td colspan="2" align="right" ><form:input id="addPhoneButton"
								path="addPhoneButton" class="linkButton" type="submit"
								value="Lisa telefon" /></td>
					</tr>
				</c:if>
				<tr>
					<c:choose>
						<c:when test="${personForm.disabled}">
							<td colspan="2" align="right"><br> <a id="backLink"
								href="<c:url value='/search' />">Tagasi</a></td>
						</c:when>
						<c:otherwise>
							<td colspan="2" align="right"><form:input path="addButton"
									type="submit" value="Lisa" /></td>
						</c:otherwise>
					</c:choose>
				</tr>
			</tbody>
		</table>
	</form:form>
</body>
</html>
