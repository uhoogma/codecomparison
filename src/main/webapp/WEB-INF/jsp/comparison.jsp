<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Comparison</title>
<link rel="stylesheet"
	href="<c:url value="/static/css/bootstrap.min.css"/>">
<script src="<c:url value="/static/js/jquery.min.js"/>"></script>
<script src="<c:url value="/static/js/bootstrap.min.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/static/css/jquery-ui.css"/>">
<script src="<c:url value="/static/js/jquery-ui.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/static/css/style.css"/>">
<script src="<c:url value="/static/codemirror/lib/codemirror.js"/>"></script>
<link rel="stylesheet"
	href="<c:url value="/static/codemirror/lib/codemirror.css"/>">
<script src="<c:url value="/static/codemirror/mode/clike/clike.js"/>"></script>
<script src="<c:url value="/static/js/codemirror.js"/>"></script>
<link rel="icon" type="image/png"
	href="<c:url value="/static/css/images/Penguin_3.png"/>" />
</head>
<body>
	<div class="page-container">
		<div class="container-fluid">
			<div class="page-bg">
				<div class="top row">
					<div class="col-sm-12">
						<a href="<c:url value="/index"/>"><button type="button"
								class="btn btn-default-left">AVALEHT</button></a> <a
							href="<c:url value="${comparisonForm.returnLink}/0"/>">
							<button type="button" class="btn btn-primary">TAGASI
								ANALÜÜSI JUURDE</button>
						</a>
					</div>
				</div>
				<div class="row">
					<h3 class="col-sm-12 centered-text">Jaccardi koefitsent ->
						${comparisonForm.savedComparison.largestSimilarityResultAsString} |
						Jaccardi koefitsent
						<-${comparisonForm.savedComparison.smallestSimilarityResultAsString}</h3>
				</div>
				<div class="row">
					<div class="col-sm-6">
						<div>
							<p>Voor: ${comparisonForm.firstRound.roundName}|${comparisonForm.firstRound.subject}|
								${comparisonForm.firstRound.year}|
								${comparisonForm.firstRound.semester}</p>
						</div>
						<div>Tudengi nimi: ${comparisonForm.firstStudent.fullName} |
							Tudengi Id: ${comparisonForm.firstStudent.moodleId } | Katse Id:
							${comparisonForm.savedComparison.firstAttemptId }</div>
						<div class="code-div">
							<textarea id="java-code" style="display: none;">${comparisonForm.firstCode}</textarea>
						</div>
					</div>

					<div class="col-sm-6">
						<div>
							<p>Voor: ${comparisonForm.secondRound.roundName}|${comparisonForm.secondRound.subject}|
								${comparisonForm.secondRound.year}|
								${comparisonForm.secondRound.semester}</p>
						</div>
						<div>Tudengi nimi: ${comparisonForm.secondStudent.fullName}
							| Tudengi Id: ${comparisonForm.secondStudent.moodleId } | Katse
							Id: ${comparisonForm.savedComparison.secondAttemptId }</div>
						<div class="code-div">
							<textarea id="java-code2" style="display: none;">${comparisonForm.secondCode}</textarea>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
