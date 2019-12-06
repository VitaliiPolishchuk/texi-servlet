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
    <title>Sing Up</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
  </head>
  <body>

    <c:import url="components/navbar.jsp"/>

	<div class="row ">
        <div class="col s0 m3"></div>
        <div class="col s12 m6">
    	<div class="container z-depth-1 login-container">
            <div class="row">
                <form class="col s12" method="POST" action="/singup">
                    <div class="row">
                        <div class="input-field col s12">
                            <input name="<%=ParamAttrConstant.FIRST_NAME%>" id="first_name" type="text" class="validate">
                            <label for="first_name"><fmt:message key="first_name"/></label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input name="<%=ParamAttrConstant.LAST_NAME%>" id="last_name" type="text" class="validate">
                            <label for="last_name"><fmt:message key="last_name"/></label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input name="email" id="<%=ParamAttrConstant.EMAIL%>" type="email" class="validate">
                            <label for="email"><fmt:message key="email"/></label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input name="<%=ParamAttrConstant.PASSWORD%>" id="password" type="password" class="validate">
                            <label for="password"><fmt:message key="password"/></label>
                        </div>
                    </div>

                    <div class="row">
                        <div class="input-field col s12">
                            <button class="btn waves-effect waves-light col s12 login-button" type="submit">
                                <fmt:message key="sing_up"/>
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
            <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/js/materialize.min.js"></script>

  </body>
</html>