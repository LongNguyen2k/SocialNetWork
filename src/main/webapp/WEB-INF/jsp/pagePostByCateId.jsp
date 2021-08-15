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
        <h1>Timeline </h1>
        <div class="timelineposts">
            
            <div class="col-md-6">
                <ul class="list-group">
                    <div class="post-component"> 
                    <c:forEach var="post" items="${listPostFromCategory}"> 
                    <li class="list-group-item"> 
                        
                       <blockquote>
                       <div class="d-flex flex-row align-items-center"> <img src="https://i.imgur.com/UXdKE3o.jpg" width="50" >
                            <span class="font-weight-bold">${post[0]}</span> 
                            <small class="text-primary">HashTag</small>  
                        </div>
                        <img src="https://i.imgur.com/xhzhaGA.jpg" class="img-fluid" />                          
                        <div class="row ">
                            <br/>
                                <p style="word-break: break-word;">
                                     ${post[1]}
                                </p>
                          </div>
                        <footer>Posted by ${post[0]} on  ${post[2]} in  ${post[3]} 
                            <button class="btn btn-default like" type="button" > <i class="glyphicon glyphicon-heart" data-aos="flip-right"></i><span>${post[4]} Likes</span></button>                           
                        </footer>
                        <div class="comments">
                                <div class="d-flex flex-row mb-2"> <img src="https://i.imgur.com/9AZ2QX1.jpg" width="40">
                                    <div class="d-flex flex-column ml-2"> 
                                        <span class="name">Daniel Frozer</span> <small style="word-break: break-word;" class="comment-text">I like this alot! thanks alot</small>  
                                    </div>
                                </div>
                                <div class="d-flex flex-row mb-2"> <img src="https://i.imgur.com/1YrCKa1.jpg" width="40">
                                    <div class="d-flex flex-column ml-2"> 
                                        <span class="name">Elizabeth goodmen</span> <small style="word-break: break-word;"  class="comment-text">Thanks for sharing!</small>
                                    </div>
                                </div>
                                <div class="comment-input"> <input type="text" class="form-control">
                                   <button class="btn btn-default comment" type="submit">
                                        <i class="glyphicon glyphicon-flash"></i><span> 3 Comments</span>
                                   </button>
                                </div>
                        </div>
                    </blockquote>
                        
                    </li>
                    </c:forEach>
                  
                    </div>
             
                </ul>
            </div>
        </div>
</div>
