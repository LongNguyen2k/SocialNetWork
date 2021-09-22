<%-- 
    Document   : index
    Created on : Aug 9, 2021, 4:53:18 PM
    Author     : Windows 10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <div class="container">
        <h1>Timeline </h1>
        <div class="timelineposts">
             <!-- Mỗi Li là 1 bài đăng mới -->
            <div class="col-md-6">
                <ul class="list-group">
                    <div class="post-component"> 
                    <c:forEach var="post" items="${postNewFeed}"> 
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
                                <small class="text-primary">On ${post[4]}</small>  
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
                        <footer>Posted by ${post[1]} 
                            <a href="<c:url value="/user/likesPost?username=${pageContext.request.userPrincipal.name}&post_id=${post[6]}"/>"  class="btn btn-default like"  > <i class="glyphicon glyphicon-heart" data-aos="flip-right"></i><span> ${post[5]} Likes</span></a>                            
                        </footer>
                        <div class="comments">
                            <a  class="btn btn-default comment" href="<c:url value="/user/comment?username=${pageContext.request.userPrincipal.name}&post_id=${post[6]}"/>">
                                            <i class="glyphicon glyphicon-flash"></i><span>Comments</span>
                                </a>
                         </div>
                    </blockquote>
                        
                    </li>
                    </c:forEach>
                  
                    </div>
             
                </ul>
                
             <div>
                 <ul class="pagination"> 
                    <c:forEach begin="1" end="${Math.ceil(counter/3)}" var="i">
                    <li class="page-item"><a class="page-link" href="<c:url value="/user/" />?page=${i}">${i}</a></li>
                    </c:forEach>   
                  </ul>
             </div>
                
            </div>
             
            
             
        </div>
</div>