<%-- 
    Document   : admin-left
    Created on : Oct 12, 2021, 9:49:39 AM
    Author     : Windows 10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- A vertical navbar -->
<nav class="navbar bg-light">

  <!-- Links -->
  <ul class="navbar-nav">
    <li class="nav-item">
        <a class="nav-link" href="<c:url value="/admin/admincategorystats" />"> <spring:message code="label.navbarItem1" /></a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#">Link 2</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#">Link 3</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="<c:url value="/user/" />"><spring:message code="label.adminMainPage" /></a>
    </li>
  </ul>

</nav>
