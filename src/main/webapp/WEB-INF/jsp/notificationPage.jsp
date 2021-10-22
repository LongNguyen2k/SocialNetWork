<%-- 
    Document   : notificationPage
    Created on : Sep 14, 2021, 3:18:40 PM
    Author     : Windows 10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<div class="container">
            <div class="row">
                
                <div class="col-md-6">
                    <ul class="list-group">
                        <li class="list-group-item">
                        <c:forEach items="${notificationList}" var="nor">
                            <blockquote>
                                 <div class="media" style="border-bottom: 1px solid black">
                                            <div class="media-left">
                                                <img src="<c:url value="${nor[0]}" />" class="media-object" style="width:60px"/>
                                            </div>
                                            <div class="media-body" >
                                              <h4 class="media-heading" style="color: black;">${nor[1]}</h4>
                                              <p style="color: #669999;">
                                                  
                                                  <c:if test="${nor[2] == 1}">
                                                      ${nor[1]} <spring:message code="label.likePost" />
                                                  </c:if> 
                                                   <c:if test="${nor[2] == 2}">
                                                       ${nor[1]} <spring:message code="label.commentPost" />
                                                  </c:if>  
                                                    <c:if test="${nor[2] == 3}">
                                                        ${nor[1]} <spring:message code="label.likeComment" />
                                                  </c:if> 
                                                <c:if test="${nor[2] == 4}">
                                                    ${nor[1]} <spring:message code="label.createAuction" />
                                                  </c:if>
                                              </p>
                                              <c:if test="${nor[2] == 5}">
                                                  <p> <spring:message code="label.winAuction"/>${nor[1]}<spring:message code="label.winAuction2" /></p>
                                                  </c:if> 
                                             <c:if test="${nor[2] == 6}">
                                                <p> <spring:message code="label.userSendReportApprove"/></p>
                                           </c:if> 
                                            <c:if test="${nor[2] == 7}">
                                                <p> <spring:message code="label.userCreatePostApprove"/></p>
                                           </c:if> 
                                         <c:if test="${nor[2] == 8}">
                                                <p> <spring:message code="label.userSendReportDenied"/></p>
                                           </c:if> 
                                        <c:if test="${nor[2] == 9}">
                                                <p> <spring:message code="label.userSendReportApprove2"/></p>
                                           </c:if>
                                            <c:if test="${nor[2] == 10}">
                                                <p> <spring:message code="label.userCreateCommentApprove"/></p>
                                           </c:if>    
                                            <c:if test="${nor[2] == 11}">
                                                <p> <spring:message code="label.userSendReportDenied2"/></p>
                                           </c:if> 
                                                 <div class="my-dateNotify"><i>${nor[3]}</i></div>
                                            </div>
                                </div>  
                            </blockquote>
                        </c:forEach>
                        </li>
                    </ul>
                </div>
              
            </div>
</div>
<script>
  window.onload = function(){
      
    calculateFunctionNotify()
  }
</script> 