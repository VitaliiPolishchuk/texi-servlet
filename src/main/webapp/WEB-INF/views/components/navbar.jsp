<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="the.best.utils.ParamAttrConstant"%>
<%@ page import="the.best.utils.UrlConstant"%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<nav>
    <div class="nav-wrapper teal darken-1 z-depth-1">
        <a href="<%=UrlConstant.ORDER%>" class="brand-logo">TESLA</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
           <li>
                <a href="<%=UrlConstant.LANGUAGE%>?<%=ParamAttrConstant.LOCALE%>=uk"><fmt:message key="navbar.uk"/></a>
           </li>
           <li>
                <a href="<%=UrlConstant.LANGUAGE%>?<%=ParamAttrConstant.LOCALE%>=en"><fmt:message key="navbar.en"/></a>
           </li>
           <li>
                <c:choose>
                    <c:when test="${not empty user}">
                    <a href="<%=UrlConstant.LOGOUT%>"><fmt:message key="log_out"/></a>
                    </c:when>
                    <c:otherwise>
                        <a href="<%=UrlConstant.LOGIN%>"><fmt:message key="log_in"/></a>
                    </c:otherwise>
                </c:choose>
           </li>
        </ul>
     </div>
</nav>