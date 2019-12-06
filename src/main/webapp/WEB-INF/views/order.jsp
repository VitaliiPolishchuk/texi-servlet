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
    <title>Booking trip</title>
  </head>
  <body>

       <c:import url="components/navbar.jsp"/>

      <div class="map-container container z-depth-1">
          <div class="row">
              <div class="col s6">

                <div id="title"><fmt:message key="order.greetings"/></div>


                <div class="row">
                  <form method="POST" class="col s6" action="/order" id="mapForm">
                    <div class="row">
                      <div class="input-field col s6">
                        <input name="<%=ParamAttrConstant.ORIGIN%>" placeholder="<fmt:message key="origin"/>" id="origin" type="text" class="validate">
                        <label for="origin"><fmt:message key="origin"/></label>
                      </div>
                    </div>
                    <div class="row">
                      <div class="input-field col s6">
                        <input name="<%=ParamAttrConstant.DESTINATION%>" placeholder="<fmt:message key="destination"/>" id="destination" type="text" class="validate">
                        <label class="active" for="destination"><fmt:message key="destination"/></label>
                      </div>
                    </div>
                    <div class="row">
                      <div class="input-field col s12">
                        <input name="<%=ParamAttrConstant.DISCOUNT%>" placeholder="<fmt:message key="discount"/>" id="discount" type="text" class="validate">
                        <label class="active" for="discount"><fmt:message key="discount"/></label>
                      </div>
                    </div>
                    <div class="error-message"></div>
                    <div class="row">
                        <div class="input-field col s5 m5">
                            <button class="z-depth-2 waves-effect waves-light btn" type="submit" name="action"><fmt:message key="book_now.button"/>
                                <i class="material-icons left">drive_eta</i>
                            </button>
                        </div>
                    </div>
                  </form>
                </div>
              </div>
              <div class="col s6">
                  <div class="z-depth-2" id="map"></div>
              </div>
            </div>
  </div>

  <c:import url="components/footer.jsp"/>

  <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/js/materialize.min.js"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="../../static/js/map.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=<%=GoogleKey.MAPS_API_KEY%>&libraries=places&callback=initMapFrom"
        async defer></script>
  </body>
</html>