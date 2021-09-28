<%-- 
    Document   : pagePostByCateId
    Created on : Aug 13, 2021, 9:09:49 PM
    Author     : Windows 10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <div class="container">
        <h1><spring:message code="h1.timeline"/> </h1>
        <div class="timelineposts">
        <c:if test="${!listPostFromCategory.isEmpty()}"  >
            <div class="col-md-6">
                <ul class="list-group">
                    <div class="post-component"> 
                    <c:forEach var="post" items="${listPostFromCategory}"> 
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
                                <small class="text-primary"><spring:message code="label.timePost" /> ${post[4]}</small>  
                             </div>

                        </div>
                    </div> 
                </div>      
                        <img src="<c:url value="${post[2]}" />" alt="" width="100%"  />     
                        <div class="row ">
                            <br/>
                                <p style="word-break: break-word;">
                                    ${post[3]}
                                </p>
                          </div>
                        <footer><spring:message code="footer.label" /> ${post[1]} 
                            <a href="<c:url value="/user/likesPost?username=${pageContext.request.userPrincipal.name}&post_id=${post[6]}"/>"  class="btn btn-default like"  > <i class="glyphicon glyphicon-heart" data-aos="flip-right"></i><span> ${post[5]} <spring:message code="span.like" /></span></a>                            
                            <a href="<c:url value="/user/auctionpage/?username=${pageContext.request.userPrincipal.name}&post_id=${post[6]}" />"  class="btn btn-default like"  > <i class="glyphicon glyphicon-usd" data-aos="flip-right"></i><span> <spring:message code="label.auction" /></span></a>                            
                        </footer>
                        <div class="comments">
                            <a  class="btn btn-default comment" href="<c:url value="/user/comment?username=${pageContext.request.userPrincipal.name}&post_id=${post[6]}"/>">
                                            <i class="glyphicon glyphicon-flash"></i><span><spring:message code="label.comment" /></span>
                                </a>
                         </div>
                    </blockquote>
                        
                    </li>
                    </c:forEach>
                  
                    </div>
             
                </ul>
            </div>
            </c:if>
              
                <c:if test="${listPostFromCategory.isEmpty() }">
                    <spring:message code="label.emptyCate" /> 
                </c:if>

        </div>
</div>
