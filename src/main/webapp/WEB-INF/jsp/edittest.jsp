<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Edit Test</title>
<link rel="stylesheet"
	href="<c:url value="/static/css/bootstrap.min.css"/>">
<script src="<c:url value="/static/js/jquery.min.js"/>"></script>
<script src="<c:url value="/static/js/bootstrap.min.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/static/css/edittest.css"/>">
</head>
<body>
	<div class="page-container">
		<div class="container-fluid">
			<div class="page-bg">
				<form:form method="post" action="${theAction}" id="taskForm"
					modelAttribute="taskForm">
					<c:url
						value="request.getAttribute(\"javax.servlet.forward.request_uri\")"
						var="theAction" />

					<form:input type="hidden" path="task.id" />
					<div class="top row">

						<div class="col-sm-2">
							<a href="<c:url value="index"/>"><button type="button"
									class="btn btn-default-left">AVALEHT</button></a>
						</div>
						<div class="col-sm-10">
							<a href="<c:url value="editround"/>"><button type="button"
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
							<!-- http://www.bootply.com/eijj8IllcO -->
							<tbody class="tbody">
								<tr>
									<td><p>Algoritmid ja andmestruktuurid IA17 IA18 | I231
											| 5. koduülesanne | 2015/2016 | Kevadsemester</p></td>
									<td><button type="button" class="btn btn-info"
												data-dismiss="modal">Vaata</button></td>
										<td><button type="button" class="btn btn-danger"
												data-dismiss="modal">Eemalda</button></td>
								</tr>
								<tr>
									<td><p>Algoritmid ja andmestruktuurid IA17 IA18 | I231
											| 5. koduülesanne | 2015/2016 | Kevadsemester</p></td>
									<td><button type="button" class="btn btn-info"
												data-dismiss="modal">Vaata</button></td>
										<td><button type="button" class="btn btn-danger"
												data-dismiss="modal">Eemalda</button></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="row">
						<div class="col-sm-6">
							<h3>VOORUD</h3>
							<table class="table table-striped">
								<!-- http://www.bootply.com/eijj8IllcO -->
								<tbody class="myTbody">

									<tr>
										<td>Algoritmid ja andmestruktuurid IA17 IA18 | I231 | 5.
											koduülesanne | 2015/2016 | Kevadsemester</td>
										<td><button type="button" class="btn btn-info"
												data-dismiss="modal">Vaata</button></td>
										<td><button type="button" class="btn btn-info"
												data-dismiss="modal">Lisa</button></td>
									</tr><tr>
										<td>Algoritmid ja andmestruktuurid IA17 IA18 | I231 | 5.
											koduülesanne | 2015/2016 | Kevadsemester</td>
										<td><button type="button" class="btn btn-info"
												data-dismiss="modal">Vaata</button></td>
										<td><button type="button" class="btn btn-info"
												data-dismiss="modal">Lisa</button></td>
									</tr><tr>
										<td>Algoritmid ja andmestruktuurid IA17 IA18 | I231 | 5.
											koduülesanne | 2015/2016 | Kevadsemester</td>
										<td><button type="button" class="btn btn-info"
												data-dismiss="modal">Vaata</button></td>
										<td><button type="button" class="btn btn-info"
												data-dismiss="modal">Lisa</button></td>
									</tr><tr>
										<td>Algoritmid ja andmestruktuurid IA17 IA18 | I231 | 5.
											koduülesanne | 2015/2016 | Kevadsemester</td>
										<td><button type="button" class="btn btn-info"
												data-dismiss="modal">Vaata</button></td>
										<td><button type="button" class="btn btn-info"
												data-dismiss="modal">Lisa</button></td>
									</tr><tr>
										<td>Algoritmid ja andmestruktuurid IA17 IA18 | I231 | 5.
											koduülesanne | 2015/2016 | Kevadsemester</td>
										<td><button type="button" class="btn btn-info"
												data-dismiss="modal">Vaata</button></td>
										<td><button type="button" class="btn btn-info"
												data-dismiss="modal">Lisa</button></td>
									</tr><tr>
										<td>Algoritmid ja andmestruktuurid IA17 IA18 | I231 | 5.
											koduülesanne | 2015/2016 | Kevadsemester</td>
										<td><button type="button" class="btn btn-info"
												data-dismiss="modal">Vaata</button></td>
										<td><button type="button" class="btn btn-info"
												data-dismiss="modal">Lisa</button></td>
									</tr><tr>
										<td>Algoritmid ja andmestruktuurid IA17 IA18 | I231 | 5.
											koduülesanne | 2015/2016 | Kevadsemester</td>
										<td><button type="button" class="btn btn-info"
												data-dismiss="modal">Vaata</button></td>
										<td><button type="button" class="btn btn-info"
												data-dismiss="modal">Lisa</button></td>
									</tr>

									<%-- <c:forEach items="${taskForm.roundsNotInTask}" var="round"
										varStatus="teamsLoop">
										<tr>
											<td><p>${round.roundName}|${round.subject}|
													${round.year} | ${round.semester}</p></td>
											<td>
												<div class="checkbox">
													<form:checkbox value=""
														path="roundsNotInTask[${teamsLoop.index}].id" />
												</div>
											</td>
										</tr>
									</c:forEach> --%>
								</tbody>
							</table>
						</div>
						<div class="col-sm-6">
							<h3>TÄIENDAV INFO</h3>
							<div style="margin-right: 20px;">
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
											<label><input type="checkbox" checked="checked"
												value=""></label>
										</div>
									</div>
									<div>
										<button type="button" class="btn btn-info">LOOBU</button>
										<%-- 										<a href="<c:url value="edittask"/>">  --%>
										<form:input path="addTaskButton"
											class="btn btn-info pull-right" type="submit"
											value="SALVESTA" />
										</a>
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