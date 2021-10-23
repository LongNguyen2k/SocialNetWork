<%-- 
    Document   : base-admin
    Created on : Oct 12, 2021, 9:42:02 AM
    Author     : Windows 10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title><tiles:insertAttribute name="title"/></title>
         <link rel="stylesheet" href="<c:url value="/css/Footer-Dark.css" />"/>
       
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

        <!-- Popper JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
       <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
       <script src="<c:url value="/js/stats.js" />" ></script>
    </head>
    <body>
        <h1 class="text-center text-dark"> <spring:message code="label.mainLabel" /></h1>
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-xs-12 bg-light">
                     <!-- Left-->
                     <tiles:insertAttribute name="left"  />
                </div>
                <div class="col-md-8 col-xs-12">
                      <!-- Content-->
                      <tiles:insertAttribute name="content" />
                </div>
           </div> 
            <!-- Footer-->
          
            
        </div>
        <tiles:insertAttribute name="footer" />
    </body>
</html>
