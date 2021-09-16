<%-- 
    Document   : comment
    Created on : Sep 4, 2021, 11:19:38 AM
    Author     : Windows 10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:if test="${errMsG != null}">
    <div class="alert alert-danger">${errMsG}</div>
</c:if>              
<section class="content-item" id="comments">
    <div class="container">   
    	<div class="row">
            <div class="col-sm-8">
                 <c:forEach items="${userCurrentComment}" var="userCmt">
                <form action="/SocialNetWork/user/addComment"> <!-- Form Dung để thực hiện phương thức get -->
                   
                	<h3 class="pull-left">New Comment</h3>
                        <button  class="btn btn-default comment  pull-right" type="submit">
                                <i class="glyphicon glyphicon-flash"></i><span>Comments</span>
                           </button>
                    <fieldset>
                        <div class="row">
                            <div class="col-sm-3 col-lg-2 hidden-xs">
                                <img class="img-responsive" src="<c:url value="${userCmt.avatar}" />" alt="">
                            </div>
                            <div class="form-group col-xs-12 col-sm-9 col-lg-10">
                                <textarea  rows="3" name="commentText" class="form-control" placeholder="Write Comment"></textarea>
                            </div>
                            <input type="hidden" name="username" value="${pageContext.request.userPrincipal.name}" />
                            <input type="hidden" name="post_id" value="${post_id}" />
                            
                        </div>  	
                    </fieldset>
                       
                </form>
                <h3>${countComment} Comments</h3>
                <!-- COMMENT 1 - START -->
                 <c:forEach items="${commentOfPost}" var="cmt">
                <div class="media">
                    <a class="pull-left" href="#"><img class="media-object" src="<c:url value="${cmt[3]}" />" alt=""></a>
                    <div class="media-body">
                        <h4 class="media-heading">${cmt[0]}</h4>
                        <p>${cmt[2]}</p>
                        <ul class="list-unstyled list-inline media-detail pull-left">
                            <li><i class="fa fa-calendar"></i>On ${cmt[1]}</li>
                            <li><i class="fa fa-thumbs-up"></i>${cmt[4]}</li>
                        </ul>
                        <ul class="list-unstyled list-inline media-detail pull-right">
                            <li class=""><a href="<c:url value="/user/likesComment?username=${pageContext.request.userPrincipal.name}&comment_id=${cmt[5]}&post_id=${post_id}"/> ">Like</a></li>
                        </ul>
                    </div>
                </div>
                  </c:forEach>
                <!-- COMMENT 1 - END -->
                </c:forEach>              
            </div>
        </div>
    </div>
</section>
