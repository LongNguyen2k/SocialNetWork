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
                        <!-- <c:forEach var="post" items="${postFromUser}"> --> 
                    <li class="list-group-item">                    
                        <blockquote>
                            
                            <!--<p>${post.name}</p>-->
                            <img src="https://i.imgur.com/xhzhaGA.jpg" class="img-fluid" />                          
                            <div class="row ">
                                <br/>
                                    <p style="word-break: break-word;">
                                      ${post.content}
                                    </p>
                              </div>
                             <!-- <footer>Posted by howCode on ${post.postAt} in ${post.addressPost} -->
                                <button class="btn btn-default like" type="button" > <i class="glyphicon glyphicon-heart" data-aos="flip-right"></i><span><!-- ${post.likes} Likes --></span></button>
                                <button class="btn btn-default comment" type="button">
                                <i class="glyphicon glyphicon-flash"></i><span>  Comments</span></button>
                            </footer>
                        </blockquote>
                    </li>
                    </c:forEach>
                    </div>
             
                </ul>
            </div>



        </div>





    </div>