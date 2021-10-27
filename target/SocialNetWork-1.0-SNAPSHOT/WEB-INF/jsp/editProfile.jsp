<%-- 
    Document   : editProfile
    Created on : Sep 14, 2021, 4:57:54 PM
    Author     : Windows 10
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"  %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!-- About Me Section -->
<c:url value="/user/editProfilePost" var="editProfile" /> 
 <form:errors path="*" cssClass="alert alert-danger" element="div" />
<div class="container">
<div class="row gutters">

<div class="col-xl-3 col-lg-3 col-md-12 col-sm-12 col-12">
<div class="card h-100">
	<div class="card-body">
		<div class="account-settings">
			<div class="user-profileEditProfile">
				<div class="user-avatar">
                                    <img src="<c:url value="${user.avatar}" />" alt="Maxwell Admin">
				</div>
				<h5 class="user-name">${user.name}</h5>
				<h6 class="user-email">${user.email}</h6>
			</div>
			<div class="about">
				<h5>About</h5>
                                <c:if test="${user.aboutMe != null}">
                                    <p>${user.aboutMe}</p>
                                </c:if>
				
			</div>
		</div>
	</div>
</div>
</div>

<div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12">
<div class="card h-100">
	<div class="card-body">
            <form:form  method="post" action="${editProfile}" modelAttribute="user" enctype="multipart/form-data"> 
                 
		<div class="row gutters">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <h6 class="mb-2 text-primary"><spring:message code="label.personalDetail"/></h6>
			</div>
			<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                            <form:hidden path="id" cssClass="form-control"></form:hidden>
				<div class="form-group">
					<label for="fullName"><spring:message code="label.fullName"/></label>
                                        <form:input cssClass="form-control" id="name" type="text"  path="name" placeholder="Enter full name" />
                                        <form:errors path="name" cssClass="text-danger" element="div" />
				</div>
			</div>
			<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
				<div class="form-group">
					<label for="eMail"><spring:message code="label.Email"/></label>
                                        <form:input class="form-control" id="email" type="email" path="email" placeholder="Enter email ID"/>
                                        <form:errors path="email" cssClass="text-danger" element="div" />
				</div>
			</div>
			<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
				<div class="form-group">
					<label for="phone"><spring:message code="label.gender"/></label>
					<form:select id="gender" path="gender" cssClass="form-control">
                                            <option value="male"  >Male</option>
                                            <option value="female"  >FeMale</option>
                                        </form:select>
				</div>
			</div>
                        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                            <label for="phone"><spring:message code="label.phone"/></label>
                            <form:input type="text" id="phoneNumber" path="phoneNumber" cssClass="form-control" placeholder="Nhập Số Điện Thoại"   />
                            <form:errors path="phoneNumber" cssClass="text-danger" element="div" />
                            <br/>
                        </div>  
			<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
				<div class="form-group">
					<label for="website"><spring:message code="label.avatar"/></label>
                                        <form:input type="file" id="file" path="file" cssClass="form-control"  />
                                        <form:errors path="file" cssClass="text-danger" element="div" />
				</div>
			</div>
		</div>
		<div class="row gutters">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <label for="eMail"><spring:message code="label.birthday"/></label>
                               <form:input type="date" id="birthday" path="birthday" cssClass="form-control"   />
                               <form:errors path="birthday" cssClass="text-danger" element="div" /> 
			</div>
			<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
				<div class="form-group">
                                    <label for="Street"><spring:message code="label.about" /> </label>
                                        <form:input type="text" path="aboutMe" cssClass="form-control" placeholder="Enter your personal Joy" />
				</div>
			</div>
			
		</div>
               
                    <form:input type="hidden" path="username"   /> 
                    <form:input type="hidden" path="password" />
                    
             
                                
		<div class="row gutters">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				  <div class="form-group">
                                    <input type="submit" class="btn btn-danger" value="Cập nhật thông tin" />
                                    <a class="btn btn-default"  href="<c:url value="/user/" />">
                                        <spring:message code="label.cancel"/>
                                    </a>
                                </div>
			</div>
		</div>
                                    
               
                                    
                </form:form>                         
	</div>
</div>
</div>
</div>
</div>
