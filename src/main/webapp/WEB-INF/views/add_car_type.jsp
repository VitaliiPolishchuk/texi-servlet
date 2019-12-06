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
    <c:import url="components/head.jsp"/>
    <title>Add car type</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
  </head>
  <body class="carousel-page">

        <c:import url="components/navbar.jsp"/>


    <div class="row ">
    <div class="col s0 m3"></div>
    <div class="col s12 m6">
	<div class="container z-depth-1 login-container">
        <div class="row">
            <form id="loginForm" class="col s12" method="POST" action="<%=UrlConstant.LOGIN%>">
                <div class="row">
                    <div class="input-field col s12">
                        <input name="<%=ParamAttrConstant.EMAIL%>" id="email" type="email" class="validate">
                        <label for="email"><fmt:message key="email"/><br></label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <p class="range-field">
                          <input type="range" id="test5" min="0" max="100" />
                        </p>
                    </div>
                </div>

                <a class="sing-up-link" href="<%=UrlConstant.SING_UP%>"><fmt:message key="sing_up"/></a> <br/> <br/>
                <div class="error-message"></div>
                <div class="row">
                    <div class="input-field col s12">
                        <button class="btn waves-effect waves-light col s12 login-button login">
                            <fmt:message key="log_in"/>
                        </button>
                    </div>
                </div>
            </form>
        </div>
        </div>
        <div class="col s0 m3"></div>
        </div>

	</div>

    <c:import url="components/footer.jsp"/>

	<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="../../static/js/login.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/js/materialize.min.js"></script>




  </body>
</html>