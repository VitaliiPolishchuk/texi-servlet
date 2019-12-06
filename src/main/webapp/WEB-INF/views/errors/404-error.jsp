<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="the.best.utils.ParamAttrConstant"%>
<%@ page import="the.best.utils.UrlConstant"%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="not_found"/></title>
    <c:import url="../components/head.jsp"/>
</head>
<c:import url="../components/navbar.jsp"/>
<body>

<div class="row ">
    <div class="col s0 m1"></div>
    <div class="col s12 m10">
	<div class="container z-depth-1 login-container">
            <h2><fmt:message key="not_found"/>!</h2>
            <a href="/"><fmt:message key="order"/></a>
    </div>
    </div>
    <div class="col s0 m1"></div>
    </div>

	</div>

</section>

<c:import url="../components/footer.jsp"/>

</body>
</html>