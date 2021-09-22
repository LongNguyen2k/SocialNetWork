<%-- 
    Document   : notificationPage
    Created on : Sep 14, 2021, 3:18:40 PM
    Author     : Windows 10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<div class="container">
            <div class="row">
                
                <div class="col-md-6">
                    <ul class="list-group">
                        <li class="list-group-item">
                        <c:forEach items="${notificationList}" var="nor">
                            <blockquote>
                                 <div class="media">
                                            <div class="media-left">
                                                <img src="<c:url value="${nor[0]}" />" class="media-object" style="width:60px"/>
                                            </div>
                                            <div class="media-body">
                                              <h4 class="media-heading" style="color: black;">${nor[1]}</h4>
                                              <p style="color: #669999;">
                                                  ${nor[1]} has 
                                                  <c:if test="${nor[2] == 1}">
                                                      Like your post
                                                  </c:if> 
                                                   <c:if test="${nor[2] == 2}">
                                                      Comment Your Post
                                                  </c:if>  
                                                    <c:if test="${nor[2] == 3}">
                                                      Like Your Comment
                                                  </c:if>    
                                              </p>
                                            </div>
                                </div>  
                            </blockquote>
                        </c:forEach>
                        </li>
                    </ul>
                </div>
              
            </div>
</div>
