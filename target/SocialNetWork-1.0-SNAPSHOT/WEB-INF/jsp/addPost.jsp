<%-- 
    Document   : addPost
    Created on : Aug 26, 2021, 8:53:15 AM
    Author     : Windows 10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="/user/addPost" var="addNewPost" />
<div class="container">
    <c:if test="${errMsG != null }">
        <div class="alert alert-danger">${errMsG}</div>
    </c:if>  
	<div class="row">
	    
	    <div class="col-md-8 col-md-offset-2">
	        
                <h1><spring:message code="label.createPost"/></h1>
    		
                <form:form  action="${addNewPost}" method="post" modelAttribute="post" enctype="multipart/form-data" >  
                    
                    <div class="form-group">
                        <label for="cate"><spring:message code="label.userCreatePost" />  </label>
                        <form:select id="user" path="user" class="form-control">
                            <c:forEach items="${userIdLoggedIn}" var="u">
                                <option value="${u.id}">${u.name}</option>
                            </c:forEach>
                        </form:select>
                        
                         <form:errors path="categoryPost" cssClass="text-danger" element="div" />
    		    </div>
                    
                    
                     <form:errors path="*" cssClass="alert alert-danger" element="div" />
    		    <div class="form-group">
                        <label for="content"><spring:message code="label.content" /></label>
                        <form:textarea  rows="5" path="content" class="form-control" />
                         <form:errors path="content" cssClass="text-danger" element="div" />
    		    </div>
                    
                    <div class="form-group">
                        <label for="startprice"><spring:message code="label.startprice" /></label>
                        <form:input cssClass="form-control" id="startprice" path="startprice" type="number"  />
                        <form:errors path="startprice" cssClass="text-danger" element="div" />
                    </div>
                   
    		      <div clas="form-group">
                         <label for="file"> <spring:message code="label.imagepost" /> <label>
                        <form:input type="file"  id="file" path="file" class="form-control"  />
                    </div>
                    <div class="form-group">
    		        <label for="cate"><spring:message code="label.category" /> </label>
                        <form:select id="cate" path="categoryPost" class="form-control">
                            <c:forEach items="${categories}" var="cat">
                                <option value="${cat.id}">${cat.name}</option>
                            </c:forEach>
                        </form:select>
                         <form:errors path="categoryPost" cssClass="text-danger" element="div" />
    		    </div>
                    
    		    <div class="form-group">
                        <input type="submit" class="btn btn-danger" value="Tạo bài viết mới" />
                        <a class="btn btn-default"  href="<c:url value="/user/" />">
    		            Hủy bỏ
                        </a>
    		        
    		    </div>
    		    
    		</form:form>
		</div>
		
	</div>
</div> 