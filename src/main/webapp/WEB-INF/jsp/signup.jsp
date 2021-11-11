<%-- 
    Document   : siginup
    Created on : Aug 10, 2021, 8:47:54 PM
    Author     : Windows 10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="/signin" var="signInPage" />
<c:url value="/signup" var="addUser" /> 

<c:if test="${errMsG != null}">
    <div class="alert alert-danger">${errMsG}</div>
</c:if>

 <div class="login-clean">
     <form:form method="post" action="${addUser}" modelAttribute="user" enctype="multipart/form-data">
         <form:errors path="*" cssClass="alert alert-danger" element="div" />
         
         <h2 class="sr-only"><spring:message code="label.createAccount" /></h2>
            <div class="illustration"><i class="icon ion-ios-navigate"></i></div>
            
            <div class="form-group">
                <form:input cssClass="form-control" id="name" type="text"  path="name" placeholder="Nhập Họ Và Tên" />
                 <form:errors path="name" cssClass="text-danger" element="div" />
            </div>
            
            <div class="form-group">
                <form:input class="form-control" id="email" type="email" path="email" placeholder="Nhập Email"/>
                <form:errors path="email" cssClass="text-danger" element="div" />
            </div>
            
            <div clas="form-group">
                <label for="file"> <spring:message code="label.Avatar" /> <label>
                <form:input type="file" id="file" path="file" cssClass="form-control"  />
                <form:errors path="file" cssClass="text-danger" element="div" />
                <br/>
            </div>
  
             <div clas="form-group"> 
                 <form:select id="gender" path="gender" cssClass="form-control">
                     <option value="male"  ><spring:message code="label.male" /></option>
                     <option value="female"><spring:message code="label.female" /></option>
                 </form:select>
                <br/>
                
            </div>
            <div class="form-group">
                <form:input type="date" id="birthday" path="birthday" cssClass="form-control"   />
                <form:errors path="birthday" cssClass="text-danger" element="div" />
                <br/>
            </div> 
            <div class="form-group">
                <form:input type="text" id="phoneNumber" path="phoneNumber" cssClass="form-control" placeholder="Nhập Số Điện Thoại"   />
                <form:errors path="phoneNumber" cssClass="text-danger" element="div" />
                <br/>
                 </div>   
            
            <div class="form-group">
                <form:input type="text" id="username" path="username" cssClass="form-control" placeholder="Nhập UserName" />
                <form:errors path="username" cssClass="text-danger" element="div" />  
                <c:if test="${errUserNameDuplicate != null}">
                    <div class="alert alert-danger">${errUserNameDuplicate}</div>
               </c:if>
            </div>
           
            <div class="form-group">
                <form:input class="form-control" id="password" type="password" path="password" placeholder="Password"/>
                 <form:errors path="password" cssClass="text-danger" element="div" />  
            </div>
            
            <div class="form-group">
                <form:input class="form-control" id="confirmPassword" type="password" path="conFirmPassWord" placeholder="ConfirmPassword" />
            </div>
            
            <div class="form-group">
                <button class="btn btn-primary btn-block" id="ca" type="submit" data-bs-hover-animate="shake"><spring:message code="label.register" /></button>
                 
            </div>
                <a href="${signInPage}" class="forgot"><spring:message code="label.alreadyHaveAccount" /></a>
     </form:form>
    </div>
