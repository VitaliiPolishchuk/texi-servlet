<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="the.best.utils.ParamAttrConstant"%>
<%@ page import="the.best.utils.UrlConstant"%>
<%@ page import="the.best.utils.GoogleKey"%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>


<!DOCTYPE html>
<html>
  <head>
    <c:import url="components/head.jsp"/>

    <title>${car_page_title}</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
  </head>
  <body class="carousel-page">

        <c:import url="components/navbar.jsp"/>

    <div class="row ">
        <div class="col s0 m2"></div>
        <div class="col s12 m8">
    	<div class="container z-depth-1 login-container">
            <div class="row">
                <form carId="${car.id}" id="addCarForm" class="col s12" method="POST">
                    <div class="row">
                        <div class="input-field col s12">
                            <input value="${car.carName}" name="name" id="name" type="text" class="validate">
                            <label for="name"><fmt:message key="car.name"/><br></label>
                        </div>
                        <div class="input-field col s12">
                            <input value="${car.photoUrl}" name="photo-url" id="photo-url" type="text" class="validate">
                            <label for="photo-url"><fmt:message key="car.photo.url"/><br></label>
                        </div>
                        <div class="container ">
                            <img id="photo-display" src="${car.photoUrl}" alt="Car Photo" style="width:100%;height:20%;">
                        </div>

                        <div class="input-field col s12">
                            <select>
                              <c:forEach items="${car_types}" var="type">
                                     <c:choose>
                                         <c:when test="${type.id == car.carTypeId}">
                                              <option value="${type.id}">${type.typeName}</option>
                                         </c:when>
                                     </c:choose>
                                </c:forEach>
                                <c:forEach items="${car_types}" var="type">
                                   <c:choose>
                                       <c:when test="${type.id != car.carTypeId}">
                                            <option value="${type.id}">${type.typeName}</option>
                                       </c:when>
                                   </c:choose>
                              </c:forEach>
                            </select>
                            <label><fmt:message key="car.type"/></label>
                        </div>

                        <div class="input-field col s12">
                            <div class="row">
                                <div class="input-field col s6">
                                    <input name="location" placeholder="<fmt:message key="location"/>" id="location-input" type="text" class="validate">
                                    <label for="location-input"><fmt:message key="location"/></label>
                                </div>
                                <div class="input-field col s6">
                                    <input disabled value="${car.carLocationId}" name="location-id" id="location-id" type="text" class="validate">
                                    <label for="location-id"><fmt:message key="location.id"/><br></label>
                                </div>
                            </div>
                        </div>

                        <div class="z-depth-2" id="map" style="width:92%;height:300px;position:relative;left:4%">
                        </div>
                        <div class="input-field col s6">
                            <p>
                              <input value=1 name="availability" type="radio" id="available"

                               <c:choose>
                                   <c:when test="${car.isActive}">
                                       checked
                                   </c:when>
                               </c:choose>

                               />
                              <label for="available"><fmt:message key="available"/></label>
                            </p>
                        </div>
                        <div class="input-field col s6">
                            <p>
                              <input value=0 name="availability" type="radio" id="not-available"

                               <c:choose>
                                  <c:when test="${!car.isActive}">
                                      checked
                                  </c:when>
                              </c:choose>

                              />
                              <label for="not-available"><fmt:message key="not.available"/></label>
                            </p>
                        </div>

                    </div>
                    <div class="error-message"></div>
                    <div class="row">
                        <div class="input-field col s12">
                            <button class="btn waves-effect waves-light col s12 login-button submit">
                                <fmt:message key="submit"/>
                            </button>
                        </div>
                    </div>
                </form>

            </div>
            </div>
            <div class="col s0 m2"></div>
            </div>

    	</div>




    <c:import url="components/footer.jsp"/>



    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../../static/js/new_car.js"></script>
    <!--Google Map-->
    <script src="https://maps.googleapis.com/maps/api/js?key=<%=GoogleKey.MAPS_API_KEY%>&libraries=places&callback=initMap"
        async defer></script>




  </body>
</html>