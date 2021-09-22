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
<c:url value="/user/editProfiles" var="editProfile" /> 
<div class="container">
<div class="row gutters">

<div class="col-xl-3 col-lg-3 col-md-12 col-sm-12 col-12">
<div class="card h-100">
	<div class="card-body">
		<div class="account-settings">
			<div class="user-profileEditProfile">
				<div class="user-avatar">
                                    <img src="<c:url value="${userProfile.avatar}" />" alt="Maxwell Admin">
				</div>
				<h5 class="user-name">${userProfile.name}</h5>
				<h6 class="user-email">${userProfile.email}</h6>
			</div>
			<div class="about">
				<h5>About</h5>
				<p>I'm Yuki. Full Stack Designer I enjoy creating user-centric, delightful and human experiences.</p>
			</div>
		</div>
	</div>
</div>
</div>

<div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12">
<div class="card h-100">
	<div class="card-body">
            <form:form  method="post" action="${editProfile}" modelAttribute="userProfile" enctype="multipart/form-data"> 
		<div class="row gutters">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				<h6 class="mb-2 text-primary">Personal Details</h6>
			</div>
			<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                            <form:hidden path="id" cssClass="form-control"></form:hidden>
				<div class="form-group">
					<label for="fullName">Full Name</label>
                                        <form:input cssClass="form-control" id="name" type="text"  path="name" placeholder="Enter full name" />
                                        <form:errors path="name" cssClass="text-danger" element="div" />
				</div>
			</div>
			<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
				<div class="form-group">
					<label for="eMail">Email</label>
                                        <form:input class="form-control" id="email" type="email" path="email" placeholder="Enter email ID"/>
                                        <form:errors path="email" cssClass="text-danger" element="div" />
				</div>
			</div>
			<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
				<div class="form-group">
					<label for="phone">Gender</label>
					<form:select id="gender" path="gender" cssClass="form-control">
                                            <option value="male"  >Male</option>
                                            <option value="female"  >FeMale</option>
                                        </form:select>
				</div>
			</div>
			<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
				<div class="form-group">
					<label for="website">Avatar</label>
                                        <form:input type="file" id="file" path="file" cssClass="form-control"  />
                                        <form:errors path="file" cssClass="text-danger" element="div" />
				</div>
			</div>
		</div>
		<div class="row gutters">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				<label for="eMail">Birthday</label>
                               <form:input type="date" id="birthday" path="birthday" cssClass="form-control"   />
                               <form:errors path="birthday" cssClass="text-danger" element="div" /> 
			</div>
			<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
				<div class="form-group">
					<label for="Street">About You </label>
					<input type="name" class="form-control" id="Street" placeholder="Enter Street">
				</div>
			</div>
			
		</div>
		<div class="row gutters">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				  <div class="form-group">
                                    <input type="submit" class="btn btn-danger" value="Update" />
                                    <a class="btn btn-default"  href="<c:url value="/user/" />">
                                        Cancel
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
