<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Edit Task</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/static/css/bootstrap.min.css"/>">
<script type="text/javascript"
	src="<c:url value="/static/js/jquery.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/static/js/bootstrap.min.js"/>"></script>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/static/css/edittest.css"/>">
<script type="text/javascript"
	src="<c:url value="/static/js/general.js"/>"></script>
</head>
<body>
	<div class="page-container">
		<div class="container-fluid">
			<div class="page-bg">
				<form:form method="post" action="" id="taskForm"
					modelAttribute="taskForm">
					<form:input type="hidden" path="task.id" />
					<div class="top row">
						<div class="col-sm-2">
							<a href="<c:url value="/index"/>"><button type="button"
									class="btn btn-default-left">AVALEHT</button></a>
						</div>
						<div class="col-sm-10">
							<a href="<c:url value="/editround"/>"><button type="button"
									class="btn btn-info pull-right">LISA VOOR</button></a>
						</div>
					</div>
					<div class="row">
						<div class="padding alert alert-danger">
							<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
							<strong>Danger!</strong> This alert box could indicate a
							dangerous or potentially negative action.
						</div>
					</div>

					<div class="row">
						<table class="table" style="margin: 20px;">
							<tbody class="tbody">
								<c:forEach items="${taskForm.roundsInTask}" var="round"
									varStatus="loop">
									<tr>
										<td><p>${round.roundName}|${round.subject}|
												${round.year} | ${round.semester}</p></td>
										<td><a href="<c:url value="/editround/${round.id}"/>"><button
													type="button" class="btn btn-info" path="${round.id}">Vaata</button>
										</a></td>
										<td><button type="button" class="btn btn-danger"
												id="${round.id}" onClick="removeRoundFromTask(this.id)">Eemalda</button></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="row">
						<div class="col-sm-6">
							<h3>VOORUD</h3>
							<table class="table table-striped">
								<!-- http://www.bootply.com/eijj8IllcO -->
								<tbody class="myTbody">
									<c:forEach items="${taskForm.roundsNotInTask}" var="round"
										varStatus="loop">
										<tr>
											<td><p>${round.roundName}|${round.subject}|
													${round.year} | ${round.semester}</p></td>
											<td><a href="<c:url value="/editround/${round.id}"/>"><button
														type="button" class="btn btn-info" path="${round.id}">Vaata</button>
											</a></td>
											<td><button type="button" class="btn btn-info"
													id="${round.id}" onClick="addRoundToTask(this.id)">Lisa</button></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div class="col-sm-6">
							<h3>TÄIENDAV INFO</h3>
							<div style="margin-right: 20px;">
								<form:input type="hidden" id="taskId" path="task.id" />
								<div class="row">
									<div class="form-group">
										<label class="col-sm-4" for="taskName">Testi nimi</label>
										<form:input class="col-sm-8" id="nameBox" path="task.taskName" />
									</div>
								</div>
								<div>
									<div style="float: left;">Algkoodi eemaldamine võrdlusest
										on vajalik</div>
									<div class="checkbox" style="text-align: right;">
										<label><input type="checkbox" checked="checked"
											value=""></label>
									</div>
								</div>
								<div>
									<div style="float: left;">Lae üles algkood</div>
									<div class="pull-right">
										<span class="btn btn-default btn-file"> <input
											type="file">
										</span>
									</div>
									<div style="clear: both;">
										<div style="float: left;">Test on aktiivne</div>
										<div class="checkbox" style="text-align: right;">
											<label><input type="checkbox" checked="checked"></label>
										</div>
									</div>
									<div>
										<button type="button" class="btn btn-info">LOOBU</button>
										<form:input path="addTaskButton"
											class="btn btn-info pull-right" type="submit"
											value="SALVESTA" />
									</div>
								</div>
								<div style="margin-top: 30px;">
									<button type="button" class="btn btn-danger"
										data-toggle="modal" data-target="#confirmDelete">KUSTUTA
										TEST JA KOGU SEOTUD INFO</button>
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
													<button type="button" class="btn btn-danger pull-right"
														data-dismiss="modal">Kustuta</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>