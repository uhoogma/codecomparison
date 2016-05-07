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
</head>
<body>
	<div class="page-container">
		<div class="container-fluid">
			<div class="page-bg">
				<div class="top row">
					<div class="col-sm-12">
						<a href="<c:url value="index"/>"><button type="button"
								class="btn btn-default-left">AVALEHT</button></a> <a
							href="<c:url value="test"/>">
							<button type="button" class="btn btn-primary">TAGASI
								ANALÜÜSI JUURDE</button>
						</a>
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
					<h3 class="col-sm-12 centered-text">Jaccardi koefitsent 6.00</h3>
				</div>
				<div class="row">
					<div class="col-sm-6">
						<div>
							<p>Algoritmid ja andmestruktuurid IA17 IA18 | I231 | 5.
								koduülesanne | 2015/2016 | Kevadsemester</p>
						</div>
						<div>Tudengi nimi | Tudengi Id | Katse Id</div>
						<div class="code-div">
							<textarea id="java-code" style="display: none;">
package com.googlecode.ounit;

import java.io.File;

public class OunitUtil1 {

    /**
     * Helper function to recursively delete a directory.
     *
     * @param path
     * @return
     */
    public static boolean deleteDirectory(File path) {
        if (path.exists()) {
            File[] files = path.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    file.delete();
                }
            }
        }
        return (path.delete());
    }
}
</textarea>
						</div>
					</div>

					<div class="col-sm-6">
						<div>
							<p>Algoritmid ja andmestruktuurid IA17 IA18 | I231 | 5.
								koduülesanne | 2015/2016 | Kevadsemester</p>
						</div>
						<div>Tudengi nimi | Tudengi Id | Katse Id</div>
						<div class="code-div">
							<textarea id="java-code2" style="display: none;">
package com.googlecode.ounit;

import java.io.File;

public class OunitUtil2 {

    /**
     * Helper function to recursively delete a directory.
     *
     * @param path
     * @return
     */
    public static boolean deleteDirectory(File path) {
        if (path.exists()) {
            File[] files = path.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    file.delete();
                }
            }
        }
        return (path.delete());
    }
}
</textarea>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
