<%-- 
    Document   : profile
    Created on : Aug 10, 2021, 7:50:16 PM
    Author     : Windows 10
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container">
        <h1>Username's Profile 
        <i class="glyphicon glyphicon-ok-sign verified username_Profile" data-toggle="tooltip"title="Verified User"></i></h1>
</div>
<div class="container">

<!-- About Me Section -->
<div class="row text_Profile" >
    <div class="col-md-3">
        <ul class="list-group">
            <c:forEach var="u" items="${userProfile}">
            <li class="list-group-item">
                <img src="https://images.unsplash.com/photo-1522075469751-3a6694fb2f61?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=80" alt="..." width="130" class="rounded mb-2 img-thumbnail">
            </li>
            <li class="list-group-item">  
               <div class="row">
                   <div class="col-sm-6">
                       <p>Name</p>
                       <h6>${u.name}</h6>
                   </div>
                   <div class="col-sm-6">
                    <p>Email</p>
                    <h6>${u.email}</h6>
                 </div>
               </div>
               <div class="row">
                    <div class="col-sm-6">
                        <p>Gender</p>
                        <h6>${u.gender}</h6>
               </div>
               <div class="row">
                    <div class="col-sm-6">
                        <p>BirthDay</p>
                        <h6>2000-01-01</h6>
                    </div>
                </div>
            </li>
            </c:forEach>
        </ul>
        <button class="btn btn-default buttonProfile " type="button">Cập nhật Profile</button>
    </div>


    <!-- Posting Section-->

    <!-- Mỗi li là 1 post trong những post -->
    <div class="col-md-6">
    <ul class="list-group">
        <div class="post-component">
            <c:forEach var="post" items="${postUserProfile}">
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
                <footer>Posted by ${post[0]} on ${post[2]} in ${post[3]} 
                    <button class="btn btn-default like" type="button" > <i class="glyphicon glyphicon-heart" data-aos="flip-right"></i><span> ${post[4]} Likes</span></button>
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

    <!-- Adding New Post Section-->

    <div class="col-md-3">
        <button class="btn btn-default buttonProfile" type="button">Đăng Bài Viết Mới </button>
    </div>
</div>
</div> 
 
 
 
 
 
 
    