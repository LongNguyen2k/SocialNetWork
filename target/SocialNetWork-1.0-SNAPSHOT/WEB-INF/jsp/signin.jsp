<%-- 
    Document   : signin
    Created on : Aug 10, 2021, 8:46:07 PM
    Author     : Windows 10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="/signup" var="signUpPage" />
<c:url value="/signin" var="action" />

<c:if test="${param.error != null}">
    <div class="alert alert-danger">
        <p>
            <spring:message code="label.errorlogin" />
        </p>
    </div>
</c:if>
<c:if test="${param.accessDenied != null}">
    <div class="alert alert-danger">
        <spring:message code="label.accessDenied" />
    </div>
</c:if>


<div class="login-clean">
    <form method="post" action ="${action}">
        <h2 class="sr-only"><spring:message code="label.loginForm" /></h2>
            <div class="illustration"><i class="icon ion-ios-navigate"></i></div>
            <div class="form-group">
                <input class="form-control" type="text" id="username" name="username" placeholder="Username" />
            </div>
            <div class="form-group">
                <input class="form-control" type="password" id="password" name="password" placeholder="Password"/>
            </div>
            <div class="form-group">
                <button class="btn btn-primary btn-block" id="login" type="submit" data-bs-hover-animate="shake"><spring:message code="label.login" /></button>
            </div>
            <a href="${signUpPage}" class="forgot"><spring:message code="labe.createAccount" /></a>      
    </form>
    </div>