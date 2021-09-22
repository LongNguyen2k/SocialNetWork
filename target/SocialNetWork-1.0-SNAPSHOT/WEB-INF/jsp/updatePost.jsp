<%-- 
    Document   : updatePost
    Created on : Aug 28, 2021, 7:06:39 AM
    Author     : Windows 10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="/user/updatePost" var="updatePost" />
<div class="container">
    <c:if test="${errMsG != null }">
        <div class="alert alert-danger">${errMsG}</div>
    </c:if>  
	<div class="row">
	    
	    <div class="col-md-8 col-md-offset-2">
	        
    		<h1>Update post</h1>
    		
                <form:form  method="post" action="${updatePost}" modelAttribute="postUpdate" enctype="multipart/form-data">  
                    <form:errors path="*" cssClass="alert alert-danger" element="div" />
                    
                    <div class="form-group">
                        <form:hidden path="id" class="form-control"  />
                    </div>
                    <div class="form-group">
                        <form:hidden path="user" class="form-control"   />
                    </div>
                    <div class="form-group">
                        <form:hidden path="image" class="form-control"  />
                    </div>
                    
                    <div class="form-group">
                        <form:hidden path="startprice" class="form-control"  />
                    </div>
                    
                     
    		    <div class="form-group">
    		        <label for="content">Content</label>
                        <form:textarea  rows="5" path="content" class="form-control" />
                         <form:errors path="content" cssClass="text-danger" element="div" />
    		    </div>
                    
                    <div clas="form-group">
                         <label for="file"> Image Post <label>
                        <form:input type="file"  id="file" path="file" class="form-control"  />
                    </div>
                    <div class="form-group">
    		        <label for="cate">Danh Mục Bài Viết </label>
                        <form:select id="cate" path="categoryPost" class="form-control">
                            <c:forEach items="${categories}" var="cat">
                                <option value="${cat.id}">${cat.name}</option>
                            </c:forEach>
                        </form:select>
                         <form:errors path="categoryPost" cssClass="text-danger" element="div" />
    		    </div>
                    
    		    <div class="form-group">
                        <input type="submit" class="btn btn-danger" value="Update" />
                        <a class="btn btn-default"  href="<c:url value="/user/" />">
    		            Cancel
                        </a>
    		        
    		    </div>
    		    
    		</form:form>
		</div>
		
	</div>
</div> 