<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Test</title>
<link rel="stylesheet"
	href="<c:url value="/static/css/bootstrap.min.css"/>">
<script src="<c:url value="/static/js/jquery.min.js"/>"></script>
<script src="<c:url value="/static/js/bootstrap.min.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/static/css/test.css"/>">
<script src="<c:url value="/static/js/Chart.min.js"/>"></script>
<script src="<c:url value="/static/js/MyChart.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/static/css/test.css"/>">
</head>
<body>
	<div class="container">
		<div class="top row">
			<div class="col-sm-2">
				<a href="<c:url value="index"/>"><button type="button"
						class="btn btn-default-left">AVALEHT</button></a>
			</div>
			<div class="col-sm-7">
				<p></p>
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
			<div class="padding col-sm-12">
				<div class="col-sm-5">
					<p>VIIMANE SÜNKRONISEERIMINE 12 h tagasi</p>
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
											class="col-sm-7" class="form-control" id="year" type="text">
									</div>
									<div class="form-group">
										<label class="col-sm-5" for="year">Salasõna</label> <input
											class="col-sm-7" class="form-control" id="year"
											type="password">
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Logi</button>
								</div>
							</div>
						</div>
					</div>
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
						<tr>
							<td>1</td>
							<td>109782</td>
							<td>1423695</td>
							<td>135036</td>
							<td>1479173</td>
							<td>Infinity</td>
							<td rowspan="2" class="2rowButton"><a
								href="<c:url value="comparison"/>"><button type="button"
										class="btn btn-info">Vaata</button></a></td>
						</tr>
						<tr>
							<td>1</td>
							<td>135036</td>
							<td>1479173</td>
							<td>109782</td>
							<td>1423695</td>
							<td>Infinity</td>
						</tr>
						<tr>
							<td>1</td>
							<td>109782</td>
							<td>1423695</td>
							<td>135036</td>
							<td>1479173</td>
							<td>Infinity</td>
							<td rowspan="2" class="2rowButton"><a
								href="<c:url value="comparison"/>"><button type="button"
										class="btn btn-info">Vaata</button></a></td>
						</tr>
						<tr>
							<td>1</td>
							<td>135036</td>
							<td>1479173</td>
							<td>109782</td>
							<td>1423695</td>
							<td>Infinity</td>
						</tr>
						<tr>
							<td>1</td>
							<td>109782</td>
							<td>1423695</td>
							<td>135036</td>
							<td>1479173</td>
							<td>Infinity</td>
							<td rowspan="2" class="2rowButton"><a
								href="<c:url value="comparison"/>"><button type="button"
										class="btn btn-info">Vaata</button></a></td>
						</tr>
						<tr>
							<td>1</td>
							<td>135036</td>
							<td>1479173</td>
							<td>109782</td>
							<td>1423695</td>
							<td>Infinity</td>
						</tr>
						<tr>
							<td>1</td>
							<td>109782</td>
							<td>1423695</td>
							<td>135036</td>
							<td>1479173</td>
							<td>Infinity</td>
							<td rowspan="2" class="2rowButton"><a
								href="<c:url value="comparison"/>"><button type="button"
										class="btn btn-info">Vaata</button></a></td>
						</tr>
						<tr>
							<td>1</td>
							<td>135036</td>
							<td>1479173</td>
							<td>109782</td>
							<td>1423695</td>
							<td>Infinity</td>
						</tr>
						<tr>
							<td>1</td>
							<td>109782</td>
							<td>1423695</td>
							<td>135036</td>
							<td>1479173</td>
							<td>Infinity</td>
							<td rowspan="2" class="2rowButton"><a
								href="<c:url value="comparison"/>"><button type="button"
										class="btn btn-info">Vaata</button></a></td>
						</tr>
						<tr>
							<td>1</td>
							<td>135036</td>
							<td>1479173</td>
							<td>109782</td>
							<td>1423695</td>
							<td>Infinity</td>
						</tr>
						<tr>
							<td>1</td>
							<td>109782</td>
							<td>1423695</td>
							<td>135036</td>
							<td>1479173</td>
							<td>Infinity</td>
							<td rowspan="2" class="2rowButton"><a
								href="<c:url value="comparison"/>"><button type="button"
										class="btn btn-info">Vaata</button></a></td>
						</tr>
						<tr>
							<td>1</td>
							<td>135036</td>
							<td>1479173</td>
							<td>109782</td>
							<td>1423695</td>
							<td>Infinity</td>
						</tr>
						<tr>
							<td>1</td>
							<td>109782</td>
							<td>1423695</td>
							<td>135036</td>
							<td>1479173</td>
							<td>Infinity</td>
							<td rowspan="2" class="2rowButton"><a
								href="<c:url value="comparison"/>"><button type="button"
										class="btn btn-info">Vaata</button></a></td>
						</tr>
						<tr>
							<td>1</td>
							<td>135036</td>
							<td>1479173</td>
							<td>109782</td>
							<td>1423695</td>
							<td>Infinity</td>
						</tr>
					</tbody>
				</table>
				<div class="centered-text">
					<ul class="pagination">
						<li><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
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
