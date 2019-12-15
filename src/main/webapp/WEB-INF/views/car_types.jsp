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
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <c:import url="components/head.jsp"/>
    <title>Car Types
    </title>
  </head>
  <body>
    <c:import url="components/navbar.jsp"/>
    <div class="map-container container z-depth-1">
      <div class="row">
        <div class="col s6">
          <div class="row">
            <form method="POST" class="col s6" action="" id="mapForm">
              <ul class="collapsible collapsible-list">
                <c:forEach items="${car_types}" var="type">
                  <li>
                    <div class="collapsible-header" typeId="${type.id}">
                      <div class="inline">${type.typeName}
                      </div>
                      <a href="<%=UrlConstant.DELETE_CAR_TYPE%>?<%=ParamAttrConstant.TYPE_ID%>=${type.id}" class="right">
                        <i class="material-icons center icon-red waves-orange">delete
                        </i>
                      </a>
                      <a class="right">
                        <i class="waves-effect waves-light small material-icons center icon-orange">edit
                        </i>
                      </a>
                    </div>
                    <div class="collapsible-body">
                      <div class="content-collapsible-body">
                        <ul class="collection">
                          <c:forEach items="${cars.get(type.id)}" var="car">
                            <li href="#!" class="collection-item" carId="${car.id}">
                              <div>
                                <a href="<%=UrlConstant.EDIT_CAR%>?<%=ParamAttrConstant.CAR_ID%>=${car.id}">${car.carName}
                                </a>
                                <a href="<%=UrlConstant.DELETE_CAR%>?<%=ParamAttrConstant.CAR_ID%>=${car.id}" class="secondary-content">
                                  <i class="material-icons">delete
                                  </i>
                                </a>
                              </div>
                            </li>
                          </c:forEach>
                        </ul>
                      </div>
                    </div>
                  </li>
                </c:forEach>
              </ul>
            </form>
          </div>
        </div>
        <div class="col s6 car-type-container">
          <div class="z-depth-2" id="map">
          </div>
        </div>
      </div>
    </div>
    <div class="fixed-action-btn horizontal">
      <a class="btn-floating btn-large teal lighten-2">
        <i class="large material-icons">mode_edit
        </i>
      </a>
      <ul>
        <li>
          <a href="<%=UrlConstant.NEW_CAR%>" class="btn-floating teal lighten-2">
            <i class="material-icons">drive_eta
            </i>
          </a>
        </li>
      </ul>
    </div>
    <c:import url="components/footer.jsp"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js">
    </script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/js/materialize.min.js">
    </script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js">
    </script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js">
    </script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js">
    </script>
    <script type="text/javascript" src="../../static/js/cars.js">
    </script>
    <script src="https://maps.googleapis.com/maps/api/js?key=<%=GoogleKey.MAPS_API_KEY%>&libraries=places&callback=initMap"
            async defer>
    </script>
  </body>
</html>