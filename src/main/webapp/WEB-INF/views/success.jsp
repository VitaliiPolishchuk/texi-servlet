<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="the.best.utils.ParamAttrConstant"%>
<%@ page import="the.best.utils.UrlConstant"%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<!DOCTYPE html>
<html>
  <head>


    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <c:import url="components/head.jsp"/>
    <title>Success</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
  </head>
  <body>

    <c:import url="components/navbar.jsp"/>

	<div class="container">

        <fmt:message key="success.banner"/>

	</div>

    <c:import url="components/footer.jsp"/>

	<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
     <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/js/materialize.min.js"></script>
  </body>
</html>