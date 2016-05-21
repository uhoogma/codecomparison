<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Testid</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/static/css/bootstrap.min.css"/>">
<script type="text/javascript"
	src="<c:url value="/static/js/jquery.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/static/js/bootstrap.min.js"/>"></script>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/static/css/jquery-ui.css"/>">
<script type="text/javascript"
	src="<c:url value="/static/js/jquery-ui.js"/>"></script>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/static/css/style.css"/>">
<script type="text/javascript">
	$(function() {
		$("#tabs").tabs();
	});
</script>
<link rel="icon" type="image/png"
	href="<c:url value="/static/css/images/Penguin_3.png"/>" />
</head>
<body>
	<div class="page-container">
		<div class="container-fluid">
			<div class="page-bg">
				<div class="top row">
					<div class="col-sm-6">
						<button type="button" class="btn btn-default-left">AVALEHT</button>
					</div>

					<div class="col-sm-6 pull-right">
						<a href="<c:url value="edittask"/>"><button type="button"
								class="btn btn-info pull-right">LOO UUS TEST</button></a>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div id="tabs">
							<ul>
								<li><a href="#tabs-1">Aktiivsed</a></li>
								<li><a href="#tabs-2">Varjatud</a></li>
							</ul>
							<div id="tabs-1">
								<table class="table table-striped">
									<!-- http://www.bootply.com/eijj8IllcO -->
									<tbody>
										<c:forEach items="${active}" var="task">
											<tr>
												<td class="task-td"><a
													href="<c:url value="/edittask/${task.id}"/>">${task.taskName}</a>
												</td>
												<td class="rounds-td"><c:forEach items="${task.rounds}"
														var="round">
														<p>${round.roundName}|${round.subject}|${round.year}|
															${round.semester}</p>
													</c:forEach></td>
												<td class="button-td"><a href="<c:url value="task/${task.id}/0"/>"><button
															type="button" class="btn btn-info">Info</button></a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<div id="tabs-2">
								<table class="table table-striped">
									<tbody>
										<c:forEach items="${hidden}" var="task">
											<tr>
												<td class="task-td"><a
													href="<c:url value="/edittask/${task.id}"/>">${task.taskName}</a>
												</td>
												<td class="rounds-td"><c:forEach items="${task.rounds}"
														var="round">
														<p>${round.roundName}|${round.subject}|${round.year}|
															${round.semester}</p>
													</c:forEach></td>
												<td class="button-td"><a
													href="<c:url value="task/${task.id}/0"/>">
														<button type="button" class="btn btn-info">Info</button>
												</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
