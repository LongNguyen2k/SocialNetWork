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
        
        <link rel="stylesheet" href="<c:url value="/css/notificationStyle.css" />"/>
        <link rel="stylesheet" href="<c:url value="/css/commentStyle.css" />"/>
        <link rel="stylesheet" href="<c:url value="/bootstrap/css/bootstrap.min.css" />" />
        <link rel="stylesheet" href="<c:url value="/fonts/ionicons.min.css" />" />
        <link rel="stylesheet" href="<c:url value="/css/Footer-Dark.css" />"/>
        <link rel="stylesheet" href="<c:url value="/css/Highlight-Clean.css" />" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.css" />
        <link rel="stylesheet" href="<c:url value="/css/Login-Form-Clean.css" />"/>
        <link rel="stylesheet" href="<c:url value="/css/Navigation-Clean1.css" />"/>
        <link rel="stylesheet" href="<c:url value="/css/styles.css" />"/>
        <link rel="stylesheet" href="<c:url value="/css/untitled.css" />"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"/>
        <link rel="stylesheet" href="<c:url value="/css/EditProfile.css" />"/>
        <script src="<c:url value="/js/jquery.min.js" />"></script>
        <script src="<c:url value="/bootstrap/js/bootstrap.min.js" />"></script>
        <script src="<c:url value="/js/bs-animation.js" />"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
        <script src="<c:url value="/js/timeCalculate.js" />"></script>
        <script src="<c:url value="/js/functionAPI.js" />"></script>
    </head>
    <body>
       
        <!-- Header-->
        <tiles:insertAttribute name="header"  />
        <!-- Content-->
        <tiles:insertAttribute name="content" />
        <!-- Footer-->
        <tiles:insertAttribute name="footer" />
        
    </body>
     <script>
  window.onload = function(){
      
     calculateFunctionForPost()
     calculateFunctionNotify()
      calculateFunction()
  }
</script> 
 
</html>
