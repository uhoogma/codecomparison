<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Edit Round</title>
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
				<div class="top row">
					<div class="col-sm-2">
						<a href="<c:url value="index"/>"><button type="button"
								class="btn btn-default-left">AVALEHT</button></a>
					</div>
					<div class="col-sm-7">
						<p>UUE VOORU SISESTAMINE</p>
					</div>
				</div>
				<div class="row">
					<div class="padding alert alert-danger">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<strong>Danger!</strong> This alert box could indicate a dangerous
						or potentially negative action.
					</div>
				</div>
				<div style="margin: 20px;">
					<div class="row">
						<div class="form-group">
							<label class="col-sm-3" for="year">aasta</label> <input
								class="col-sm-9" class="form-control" id="year" type="text">
						</div>
					</div>
					<div class="row">
						<div class="form-group">
							<label class="col-sm-3" for="semester">semester</label> <input
								class="col-sm-9" class="form-control" id="semester" type="text">
						</div>
					</div>
					<div class="row">
						<div class="form-group">
							<label class="col-sm-3" for="subject">aine nimi</label> <input
								class="col-sm-9" class="form-control" id="subject" type="text">
						</div>
					</div>
					<div class="row">
						<div class="form-group">
							<label class="col-sm-3" for="round">vooru nimi</label> <input
								class="col-sm-9" class="form-control" id="round" type="text">
						</div>
					</div>
					<div class="row">
						<div class="form-group">
							<label class="col-sm-3" for="moodle_id">Moodle id</label> <input
								class="col-sm-9" class="form-control" id="moodle_id" type="text">
						</div>
					</div>
					<div class="row">
						<div>
							<button type="button" class="btn btn-info">LOOBU</button>
							<a href="<c:url value="edittest"/>">
								<button type="button" class="btn btn-info pull-right">SALVESTA</button>
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>