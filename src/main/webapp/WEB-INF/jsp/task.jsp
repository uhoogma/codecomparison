<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Test</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/static/css/bootstrap.min.css"/>">
<script type="text/javascript"
	src="<c:url value="/static/js/jquery.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/static/js/bootstrap.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/static/js/Chart.min.js"/>"></script>
<script type="text/javascript">
	${taskForm.chartScript}
</script>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/static/css/test.css"/>">
<script type="text/javascript"
	src="<c:url value="/static/js/general.js"/>"></script>
<link rel="icon" type="image/png"
	href="<c:url value="/static/css/images/Penguin_3.png"/>" />
</head>
<body>
	<div class="container">
		<div class="top row">
			<div class="col-sm-2">
				<a href="<c:url value="/index"/>"><button type="button"
						class="btn btn-default-left">AVALEHT</button></a>
			</div>
			<div class="col-sm-10">
				<table class="table">
					<tbody>
						<tr>
							<td class="task-td"><a
								href="<c:url value="/edittask/${taskForm.task.id}"/>">${taskForm.task.taskName}</a>
							</td>
							<td class="rounds-td"><c:forEach
									items="${taskForm.task.rounds}" var="round">
									<p>${round.roundName}|${round.subject}|${round.year}|${round.semester}</p>
								</c:forEach></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="row">
			<div class="padding alert alert-danger">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Danger!</strong> This alert box could indicate a dangerous
				or potentially negative action.
			</div>
		</div>
		<div class="row">
			<div class="padding">
				<div class="col-sm-4">
					<p>VIIMANE SÜNKRONISEERIMINE ${taskForm.lastSyncDifference}</p>
				</div>
				<div class="col-sm-3">
					<button type="button" class="btn btn-primary" data-toggle="modal"
						data-target="#credentials">SÜNKRONISEERI NÜÜD</button>
					<div class="modal fade" id="credentials" role="dialog">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">Sisesta Moodle logimisandmed</h4>
								</div>
								<div class="modal-body">
									<div class="form-group">
										<label class="col-sm-5" for="year">Kasutajanimi</label> <input
											class="col-sm-7 form-control" id="user" type="text">
									</div>
									<div class="form-group">
										<label class="col-sm-5" for="year">Salasõna</label> <input
											class="col-sm-7 form-control" id="password" type="password">
									</div>
								</div>
								<div class="modal-footer">
									<div class="form-group">
										<button type="button" id="synchronize"
											class="btn btn-primary col-sm-2 pull-right"
											data-dismiss="modal">Logi</button>
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-3">
					<table>
						<tr>
							<td>Eira</td>
							<td style="padding: 5px;"><input type="text" name="k"
								id="noise" value="${taskForm.task.k}" /></td>
							<td>märki</td>
						</tr>
						<tr>
							<td>Sobita</td>
							<td style="padding: 5px;"><input type="text" name="t"
								id="match" value="${taskForm.task.t}" /></td>
							<td>märki</td>
						</tr>
					</table>
				</div>
				<div class="col-sm-2">
					<button type="button" class="btn btn-primary" id="analyzeTask"
						data-target="#credentials">ANALÜÜSI</button>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-8">
				<table class="table table-striped table-hover"
					style="border: 2px solid black;">
					<thead>
						<tr>
							<th>Jrkn</th>
							<th>Tudeng1</th>
							<th>Katse1</th>
							<th>Tudeng2</th>
							<th>Katse2</th>
							<th>Tulemus</th>
							<th>Tegevus</th>
						</tr>
					</thead>
					<tbody class="myTbody">
						<c:set var="count" value="${taskForm.sequentialNumber}"
							scope="page" />
						<c:forEach items="${taskForm.comparisons}" var="comp">
							<c:set var="count" value="${count + 1}" scope="page" />

							<tr>
								<td>${count}</td>
								<td>${comp.firstStudentId}</td>
								<td>${comp.firstAttemptId}</td>
								<td>${comp.secondStudentId}</td>
								<td>${comp.secondAttemptId}</td>
								<td>${comp.largestSimilarityResultAsString}</td>
								<td rowspan="2" class="twoRowButton comparison-separator"><a
									href="<c:url value="/comparison/${comp.id}"/>"><button
											type="button" class="btn btn-info">Vaata</button></a></td>
							</tr>
							<tr>
								<td class="comparison-separator">${count}</td>
								<td class="comparison-separator">${comp.secondStudentId}</td>
								<td class="comparison-separator">${comp.secondAttemptId}</td>
								<td class="comparison-separator">${comp.firstStudentId}</td>
								<td class="comparison-separator">${comp.firstAttemptId}</td>
								<td class="comparison-separator">${comp.smallestSimilarityResultAsString}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="centered-text">
					<ul class="pagination">
						<c:set var="num" scope="page" value="${taskForm.sequentialNumber}" />
						<c:forEach items="${taskForm.pages}" var="page">
							<c:set var="key" scope="page" value="${page.getKey()}" />
							<li><a
								href="<c:url value="/task/${taskForm.task.id}/${page.getValue()}"/>">
									<c:if test="${num/8 == key}">
										<b>
									</c:if>${page.getKey()+1}<c:if test="${num/8 == key}">
										</b>
									</c:if>
							</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="col-sm-4">
				<h2 class="centered-text">Tulemused</h2>
				<canvas id="canvas" width="400" height="500"></canvas>
			</div>
		</div>
	</div>
</body>
</html>
