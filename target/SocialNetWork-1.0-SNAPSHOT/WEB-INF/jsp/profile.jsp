<%-- 
    Document   : profile
    Created on : Aug 10, 2021, 7:50:16 PM
    Author     : Windows 10
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"  %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${haveCurrentAuction != null}">
    <div class="alert alert-danger">${haveCurrentAuction}</div>
</c:if>
<div class="container">
     <c:forEach var="u" items="${userProfile}">
         <h1> <spring:message code="user.profile" /> ${u.name}
       </c:forEach>       
        <i class="glyphicon glyphicon-ok-sign verified username_Profile" data-toggle="tooltip"title="Verified User"></i></h1>
       
</div>
<div class="container">

<!-- About Me Section -->
<div class="row text_Profile" >
    <div class="col-md-3">
         <c:forEach var="u" items="${userProfile}">
        <ul class="list-group">
           
                
            <c:if test="${ u.avatar  != null && u.avatar.startsWith('https') == true }" >
            <li class="list-group-item">
                <img src="<c:url value="${u.avatar}" />" alt="..." width="100%" class="rounded mb-2 img-thumbnail">
            </li>
            </c:if>    
             <c:if test="${ u.avatar  == null || u.avatar.startsWith('https') == false }" >
            <li class="list-group-item">
                <img src="https://images.unsplash.com/photo-1522075469751-3a6694fb2f61?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=80" alt="..." width="130" class="rounded mb-2 img-thumbnail">
            </li>
            </c:if>     
            <li class="list-group-item">  
               <div class="row">
                   <div class="col-sm-6">
                       <p><spring:message code="label.fullName" /></p>
                       <h6>${u.name}</h6>
                   </div>
                   <div class="col-sm-6">
                       <p><spring:message code="label.Email" /></p>
                    <h6>${u.email}</h6>
                 </div>
               </div>
               <div class="row">
                    <div class="col-sm-6">
                        <p><spring:message code="label.gender" /></p>
                        <h6>${u.gender}</h6>
               </div>
               <div class="row">
                    <div class="col-sm-6">
                        <p><spring:message code="label.birthday" /></p>
                        <h6>${u.birthday}</h6>
                        
                    </div>
                </div>
            </li>
            
           
        </ul>
    <c:if test="${u.username == pageContext.request.userPrincipal.name}">                  
                        <a href="<c:url value="/user/editProfileUser" />" class="btn btn-default buttonProfile " type="button"><spring:message code="button.UpdateProfile" /></a>
    
         <sec:authorize access="hasRole('ROLE_ADMIN')">
        <div>
            <a href="<c:url value="/admin/statistical" />" class="btn btn-default buttonProfile"> <spring:message code="button.adminStaticstical" />  </a>
        </div>
        <div>
            <a href="<c:url value="/admin/directionalPage" />" class="btn btn-default buttonProfile"> <spring:message code="button.adminReportCommentAndPost" />  </a>
        </div>
        </sec:authorize>
    </c:if>
         </c:forEach>
    </div>


    <!-- Posting Section-->

    <!-- Mỗi li là 1 post trong những post -->
    
    <div class="col-md-6">
    <ul class="list-group">
        <div class="post-component border border-primary">
        <c:forEach var="post" items="${postUserProfile}">
        <c:if test="${!postUserProfile.isEmpty()}">
            <c:if test="${post[7] == false}">  
        <li class="list-group-item">
            <blockquote>    
                <div class="row" style="margin-bottom: 5px;"> 
                    <div class="col-md-6">
                    <div class="media">
                        <div class="media-left">
                             <a href="<c:url value="/user/profile/${post[8]}" />">
                            <img src="<c:url value="${post[0]}" />" alt="" width="50" class="img-circle" >
                             </a>
                        </div>
                        <div class="media-body">
                            <span class="font-weight-bold">${post[1]}</span>                  
                            <div class="my-datePost"><i class="text-primary">${post[4]}</i></div>
                         </div>
                     
                    </div>
                    </div> 
            <c:if test="${post[8] == pageContext.request.userPrincipal.name}">
                    <div class="pull-right">
                        <ul class="nav navbar-nav">
                            <li class="dropdown">
                              <a class="dropdown-toggle" data-toggle="dropdown" href="#"><i class="fa fa-ellipsis-h"></i>
                              <ul class="dropdown-menu">
                                  <li><a href="<c:url value="/user/updatePostPage?postId=${post[6]}" />" style="color:lightblue;"><spring:message code="button.updatePost" /> </a></li>
                                  <li><a href="<c:url value="/user/delete/${post[6]}/${pageContext.request.userPrincipal.name}" />"style="color:red;"><spring:message code="button.deletePost" /> </a></li>
                                  
                              </ul>
                            </li>
                     </ul> 
                   </div> 
            </c:if>       
                </div>          
                     <img src="<c:url value="${post[2]}" />" alt="" width="100%"  />    
                <div class="row ">
                    <br/>
                        <p style="word-break: break-word;">
                            ${post[3]}
                        </p>
                  </div>
                <footer>Posted by ${post[1]} 
                  <button type="submit" onclick="addLikeOrUnLike('${post[6]}','${pageContext.request.userPrincipal.name}')"  class="btn btn-default like"  > <i class="glyphicon glyphicon-heart" data-aos="flip-right"></i><span> ${post[5]} <spring:message code="span.like" /></span></button>
                   <a href="<c:url value="/user/auctionpage/?username=${pageContext.request.userPrincipal.name}&post_id=${post[6]}" />"  class="btn btn-default like"  > <i class="glyphicon glyphicon-usd" data-aos="flip-right"></i><span> <spring:message code="button.auctionInfo"/> </span></a> 
                </footer>
                 <div class="comments">
                            <a  class="btn btn-default comment" href="<c:url value="/user/comment/${post[6]}"/>">
                                <i class="glyphicon glyphicon-flash"></i><span><spring:message code="label.comment" /></span>
                                </a>
                           
             </div>
            </blockquote>
        </li>
             </c:if> 
             </c:if>     
             
        <c:if test="${post[7] == true}">
           <li class="list-group-item">
            <blockquote>    
                <div class="row" style="margin-bottom: 5px;"> 
                    <div class="col-md-6">
                    <div class="media">
                        <div class="media-left">
                            <img src="<c:url value="${post[0]}" />" alt="" width="50" class="img-circle" >
                        </div>
                        <div class="media-body">
                            <span class="font-weight-bold">${post[1]}</span>                  
                             <div class="my-datePost"><i class="text-primary">${post[4]}</i></div>
                         </div>
                     
                    </div>
                    </div> 
                    <div class="pull-right">
                        <ul class="nav navbar-nav">
                            <li class="dropdown">
                              <a class="dropdown-toggle" data-toggle="dropdown" href="#"><i class="fa fa-ellipsis-h"></i>
                              <ul class="dropdown-menu">
                                  <li><a href="<c:url value="/user/delete/${post[6]}/${pageContext.request.userPrincipal.name}" />"style="color:red;"><spring:message code="button.deletePost" /> </a></li>
                              </ul>
                            </li>
                     </ul> 
                   </div> 
                    
                </div>          
                     <img src="<c:url value="https://res.cloudinary.com/decmhyieh/image/upload/v1633318525/lock_o0dctw.png" />" alt="" width="100%"  />    
                <div class="row ">
                    <br/>
                        <p style="word-break: break-word;">
                            <spring:message code="label.reportedPost" />
                        </p>
                  </div>
                <footer>Posted by ${post[1]} 
                </footer>
            </blockquote>
        </li>
        </c:if>
        
             </c:forEach>   
        
       
        
        </div>
    </ul>
</div>
      

    <!-- Adding New Post Section-->
    
     
    
    <c:forEach var="u" items="${userProfile}">
       <c:if test="${u.username == pageContext.request.userPrincipal.name}">       
            <div class="col-md-3">
        <li class="list-group-item"><span><strong>About Me</strong></span>
                    <p>welcome to the Social Network Charity</p>
                   
        </li>
         <br/>
                <a class="btn btn-default buttonProfile" href="<c:url value="/user/addPostPage?userId=${u.id}" />" type="button"><spring:message code="button.createPost" /> </a>
                 <a class="btn btn-default buttonProfile" href="#" type="button">BàiViếtChiếnThắngĐấuGiá</a>
            </div>
       </c:if>
        </c:forEach> 
     <c:if test="${errMsG != null }">
        <div class="alert alert-danger">${errMsG}</div>
    </c:if>  
    
</div>
</div> 
 <script>
  window.onload = function(){
      
     calculateFunctionForPost()
     calculateFunctionNotify()
  }
</script> 
 
 
 
 
 
    