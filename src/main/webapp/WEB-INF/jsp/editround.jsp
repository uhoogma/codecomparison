<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Edit Round</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/static/css/bootstrap.min.css"/>">
<script type="text/javascript"
	src="<c:url value="/static/js/jquery.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/static/js/bootstrap.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/static/js/general.js"/>"></script>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/static/css/edittest.css"/>">
	<link rel="icon" type="image/png"
	href="<c:url value="/static/css/images/Penguin_3.png"/>" />
</head>
<body>
	<div class="page-container">
		<div class="container-fluid">
			<div class="page-bg">
				<div class="top row">
					<div class="col-sm-2">
						<a href="<c:url value="/index"/>"><button type="button"
								class="btn btn-default-left">AVALEHT</button></a>
					</div>
					<div class="col-sm-7">
						<p>UUE VOORU SISESTAMINE</p>
					</div>
				</div>
				<c:if test="${not empty errors}">
					<div class="row bottom">
						<div class="padding alert alert-danger">
							<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
							<strong>Oht!<br></strong>
							<c:forEach items="${errors}" var="error">
								<c:out value="${error}"></c:out>
								<br>
							</c:forEach>
						</div>
					</div>
				</c:if>
				<div style="margin: 20px;">
					<form:form method="post" action="" id="roundForm"
						modelAttribute="roundForm">
						<form:input type="hidden" id="roundId" path="round.id" />
						<form:input type="hidden" id="taskId" path="round.task_id" />
						<div class="row bottom">
							<div class="form-group">
								<label class="col-sm-3" for="year">aasta</label>
								<form:input class="col-sm-9" id="yearBox" placeholder="2015/2016 (max 9 märki)" path="round.year" />
							</div>
						</div>
						<div class="row bottom">
							<div class="form-group">
								<label class="col-sm-3" for="semester">semester</label>
								<form:input class="col-sm-9" id="semesterBox" placeholder="Kevadsemester (max 20 märki)"
									path="round.semester" />
							</div>
						</div>
						<div class="row bottom">
							<div class="form-group">
								<label class="col-sm-3" for="subject">aine nimi</label>
								<form:input class="col-sm-9" id="subjectBox" placeholder="I231 (max 6 märki)"
									path="round.subject" />
							</div>
						</div>
						<div class="row bottom">
							<div class="form-group">
								<label class="col-sm-3" for="round">vooru nimi</label>
								<form:input class="col-sm-9" id="roundNameBox" placeholder="Puude ülesanne - Algoritmid ja andmestruktuurid IA17 IA18 (max 100 märki)"
									path="round.roundName" />
							</div>
						</div>
						<div class="row bottom">
							<div class="form-group">
								<label class="col-sm-3" for="moodle_id">Moodle id</label>
								<form:input class="col-sm-9" id="urlBox" placeholder="123456 (vaid URL-i numbriline osa)" path="round.url" />
							</div>
						</div>
						<div class="row">
							<div>
								<a href="<c:url value="/index"/>">
									<button type="button" class="btn btn-info">LOOBU</button>
								</a>
								<div class="pull-right">
									<button type="button" class="btn btn-danger"
										data-toggle="modal" data-target="#confirmDelete">KUSTUTA
										VOOR JA KOGU SEOTUD INFO</button>
									<div class="modal fade" id="confirmDelete" role="dialog">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal">&times;</button>
													<h4 class="modal-title">Kinnita kustutamine</h4>
												</div>
												<div class="modal-body">
													<button type="button" class="btn btn-default"
														data-dismiss="modal">Loobu</button>
													<button type="button" id="deleteRoundButton"
														class="btn btn-danger pull-right" data-dismiss="modal">KUSTUTA</button>
												</div>
											</div>
										</div>
									</div>
									<form:input path="round.saveRoundButton" class="btn btn-info"
										type="submit" value="Salvesta" />
								</div>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>