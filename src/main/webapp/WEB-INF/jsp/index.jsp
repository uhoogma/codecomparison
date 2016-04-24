<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Testid</title>
<link rel="stylesheet"
	href="<c:url value="/static/css/bootstrap.min.css"/>">
<script src="<c:url value="/static/js/jquery.min.js"/>"></script>
<script src="<c:url value="/static/js/bootstrap.min.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/static/css/jquery-ui.css"/>">
<script src="<c:url value="/static/js/jquery-ui.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/static/css/style.css"/>">
<script>
	$(function() {
		$("#tabs").tabs();
	});
</script>
</head>
<body>
	<div class="page-container">
		<div class="container-fluid">
			<div class="page-bg">
				<div class="top row">
					<div class="col-sm-2">
						<button type="button" class="btn btn-default-left">AVALEHT</button>
					</div>
					<div class="col-sm-7">
						<p></p>
					</div>
					<div class="col-sm-3">
						<a href="<c:url value="edittest"/>"><button type="button"
								class="btn btn-info pull-right">LOO UUS TEST</button></a>
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
					<div class="col-sm-12">
						<div id="tabs">
							<ul>
								<li><a href="#tabs-1">Aktiivsed</a></li>
								<li><a href="#tabs-2">Varjatud</a></li>
							</ul>
							<div id="tabs-1">
								<table class="table table-striped">
									<!-- http://www.bootply.com/eijj8IllcO -->
									<tbody class="tbody">
										<tr>
											<td><p>Algoritmid ja andmestruktuurid IA17 IA18 |
													I231 | 5. koduülesanne | 2015/2016 | Kevadsemester</p></td>
											<td><a href="<c:url value="test"/>"><button
														type="button" class="btn btn-info">Info</button></a></td>
										</tr>
										<tr>
											<td><p>Algoritmid ja andmestruktuurid IA17 IA18 |
													I231 | 5. koduülesanne | 2015/2016 | Kevadsemester</p></td>
											<td><a href="<c:url value="test"/>"><button
														type="button" class="btn btn-info">Info</button></a></td>
										</tr>
										<tr>
											<td><p>Algoritmid ja andmestruktuurid IA17 IA18 |
													I231 | 5. koduülesanne | 2015/2016 | Kevadsemester</p></td>
											<td><a href="<c:url value="test"/>"><button
														type="button" class="btn btn-info">Info</button></a></td>
										</tr>
										<tr>
											<td><p>Algoritmid ja andmestruktuurid IA17 IA18 |
													I231 | 5. koduülesanne | 2015/2016 | Kevadsemester</p></td>
											<td><a href="<c:url value="test"/>"><button
														type="button" class="btn btn-info">Info</button></a></td>
										</tr>
										<tr>
											<td><p>Algoritmid ja andmestruktuurid IA17 IA18 |
													I231 | 5. koduülesanne | 2015/2016 | Kevadsemester</p></td>
											<td><a href="<c:url value="test"/>"><button
														type="button" class="btn btn-info">Info</button></a></td>
										</tr>
										<tr>
											<td><p>Algoritmid ja andmestruktuurid IA17 IA18 |
													I231 | 5. koduülesanne | 2015/2016 | Kevadsemester</p></td>
											<td><a href="<c:url value="test"/>"><button
														type="button" class="btn btn-info">Info</button></a></td>
										</tr>
										<tr>
											<td><p>Algoritmid ja andmestruktuurid IA17 IA18 |
													I231 | 5. koduülesanne | 2015/2016 | Kevadsemester</p></td>
											<td><a href="<c:url value="test"/>"><button
														type="button" class="btn btn-info">Info</button></a></td>
										</tr>
										<tr>
											<td><p>Algoritmid ja andmestruktuurid IA17 IA18 |
													I231 | 5. koduülesanne | 2015/2016 | Kevadsemester</p></td>
											<td><a href="<c:url value="test"/>"><button
														type="button" class="btn btn-info">Info</button></a></td>
										</tr>
										<tr>
											<td><p>Algoritmid ja andmestruktuurid IA17 IA18 |
													I231 | 5. koduülesanne | 2015/2016 | Kevadsemester</p></td>
											<td><a href="<c:url value="test"/>"><button
														type="button" class="btn btn-info">Info</button></a></td>
										</tr>
									</tbody>
								</table>
							</div>
							<div id="tabs-2">
								<table class="table table-striped">
									<tbody>
										<tr>
											<td><p>Algoritmid ja andmestruktuurid IA17 IA18 |
													I231 | 5. koduülesanne | 2015/2016 | Kevadsemester</p></td>
											<td><a href="<c:url value="test"/>"><button
														type="button" class="btn btn-info">Info</button></a></td>
										</tr>
										<tr>
											<td><p>Algoritmid ja andmestruktuurid IA17 IA18 |
													I231 | 5. koduülesanne | 2015/2016 | Kevadsemester</p></td>
											<td><a href="<c:url value="test"/>"><button
														type="button" class="btn btn-info">Info</button></a></td>
										</tr>
										<tr>
											<td><p>Algoritmid ja andmestruktuurid IA17 IA18 |
													I231 | 5. koduülesanne | 2015/2016 | Kevadsemester</p></td>
											<td><a href="<c:url value="test"/>"><button
														type="button" class="btn btn-info">Info</button></a></td>
										</tr>
										<tr>
											<td><p>Algoritmid ja andmestruktuurid IA17 IA18 |
													I231 | 5. koduülesanne | 2015/2016 | Kevadsemester</p></td>
											<td><a href="<c:url value="test"/>"><button
														type="button" class="btn btn-info">Info</button></a></td>
										</tr>
										<tr>
											<td><p>Algoritmid ja andmestruktuurid IA17 IA18 |
													I231 | 5. koduülesanne | 2015/2016 | Kevadsemester</p></td>
											<td><a href="<c:url value="test"/>"><button
														type="button" class="btn btn-info">Info</button></a></td>
										</tr>
										<tr>
											<td><p>Algoritmid ja andmestruktuurid IA17 IA18 |
													I231 | 5. koduülesanne | 2015/2016 | Kevadsemester</p></td>
											<td><a href="<c:url value="test"/>"><button
														type="button" class="btn btn-info">Info</button></a></td>
										</tr>
										<tr>
											<td><p>Algoritmid ja andmestruktuurid IA17 IA18 |
													I231 | 5. koduülesanne | 2015/2016 | Kevadsemester</p></td>
											<td><a href="<c:url value="test"/>"><button
														type="button" class="btn btn-info">Info</button></a></td>
										</tr>
										<tr>
											<td><p>Algoritmid ja andmestruktuurid IA17 IA18 |
													I231 | 5. koduülesanne | 2015/2016 | Kevadsemester</p></td>
											<td><a href="<c:url value="test"/>"><button
														type="button" class="btn btn-info">Info</button></a></td>
										</tr>
										<tr>
											<td><p>Algoritmid ja andmestruktuurid IA17 IA18 |
													I231 | 5. koduülesanne | 2015/2016 | Kevadsemester</p></td>
											<td><a href="<c:url value="test"/>"><button
														type="button" class="btn btn-info">Info</button></a></td>
										</tr>
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
