<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="the.best.utils.ParamAttrConstant"%>
<%@ page import="the.best.utils.UrlConstant"%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<html class="carousel-page">
  <head>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <c:import url="components/head.jsp"/>
    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  </head>
  <body>
    <c:import url="components/navbar.jsp"/>
    <div class="carousel-wrapper">
      <div class="carousel">
        <c:forEach items="${orders}" var="order">
          <div class="carousel-item">
            <div class="col s12 m6">
              <div class="card white darken-1">
                <div id="parent" class="card-content black-text">
                  <h5 id="card-title">${order.carType.typeName}
                  </h5>
                  <h6>${order.carType.capacity}
                    <fmt:message key="car_type_choose.places"/>
                    </h5>
                  <img src="${order.car.photoUrl}">
                  <h6 id="child">${order.updatePrice(order.discountPrice)} $
                  </h6>
                  <c:choose>
                    <c:when test="${order.wholePrice != order.discountPrice}">
                      <h6 class="original-price">${order.updatePrice(order.wholePrice)} $
                      </h6>
                    </c:when>
                  </c:choose>
                  <h5>${order.car.carName}
                  </h5>
                  <p>
                    <fmt:message key="car_type_choose.arrives_in"/> ${order.convertIntoMinutesTimeToReachOrigin()}
                    <fmt:message key="car_type_choose.min"/>. ${order.buildStringTimeToReachDestination()}
                  </p>
                  <div class="card-action">
                    <a class="waves-effect waves-light btn" href="<%=UrlConstant.PROCESS_ORDER%>?<%=ParamAttrConstant.CAR_ID%>=${order.car.id}">
                      <fmt:message key="car_type_choose.choose"/>
                    </a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </c:forEach>
      </div>
    </div>
    <c:import url="components/footer.jsp"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js">
    </script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/js/materialize.min.js">
    </script>
    <script type="text/javascript" src="../../static/js/car-type-choose.js">
    </script>
  </body>
</html>
