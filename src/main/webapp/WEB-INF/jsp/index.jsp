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
    
    <h1><spring:message code="h1.timeline"/> </h1>
       
            <div class="timelineposts">
                  <!-- Left bar-->
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
                                    <a href="<c:url value="/user/profile/${post[7]}" />">
                                    <img src="<c:url value="${post[0]}" />" alt="" width="50" class="img-circle" >
                                    </a>
                                </div>
                                <div class="media-body">
                                    <span class="font-weight-bold">${post[1]}</span>                  
                                   <div class="my-datePost"><i class="text-primary">${post[4]}</i></div>
                                 </div>

                            </div>
                        </div> 
                        <c:if test="${post[7] != pageContext.request.userPrincipal.name}">
                        <div class="pull-right">
                            <ul class="nav navbar-nav">
                                <li class="dropdown">
                                  <a class="dropdown-toggle" data-toggle="dropdown" href="#"><i class="fa fa-ellipsis-h"></i>
                                  <ul class="dropdown-menu">
                                      <li><a href="<c:url value="/user/SendReportPage/?postID=${post[6]}&username=${pageContext.request.userPrincipal.name}" />"style="color:red;"><spring:message code="button.ReportPost" /> </a></li>
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
                                        <footer class="text-primary"><spring:message code="footer.label" /> ${post[1]}  #${post[8].name}
                                            <button type="submit" onclick="addLikeOrUnLike('${post[6]}','${pageContext.request.userPrincipal.name}', ${post[5]})"  class="btn btn-default like"  > <i class="glyphicon glyphicon-heart" data-aos="flip-right"></i><span class="likeCount"> ${post[5]}</span><spring:message code="span.like" /></button>
                                <a href="<c:url value="/user/auctionpage/?username=${pageContext.request.userPrincipal.name}&post_id=${post[6]}" />"  class="btn btn-default like"  > <i class="glyphicon glyphicon-usd" data-aos="flip-right"></i><span> <spring:message code="label.auction" /></span></a>                            
                            </footer>
                            <div class="comments">
                                <a  class="btn btn-default comment" href="<c:url value="/user/comment/${post[6]}"/>">
                                    <i class="glyphicon glyphicon-flash"></i><span><spring:message code="label.comment" /></span>
                                    </a>

                             </div>
                            </blockquote> 
                        </li>
                        </c:forEach>

                        </div>

                    </ul>

                 <div>
                     <ul class="pagination"> 
                        <c:forEach begin="1" end="${Math.ceil(counter/counterMaxPage)}" var="i">
                        <li class="page-item"><a class="page-link" href="<c:url value="/user/" />?page=${i}">${i}</a></li>
                        </c:forEach>   
                      </ul>
                 </div>

                </div>
                 <!-- Right bar-->
                 <div class="col-md-4 col-xs-12" style="">
                     <section class="mt-3">
                         <div class="row" style="margin-top: 20px;margin-bottom: 20px">
                             <c:if test="${currentLoginUser != null}">
                                 ${currentLoginUser.email} ${currentLoginUser.name}
                             </c:if>
                             <c:forEach items="${userInfo}" var="u">
                             <div class="col-md-3">
                                  <a href="<c:url value="/user/profile/${u.username}" />">
                                 <img src="<c:url value="${u.avatar}" />"
                                      class="img-circle" height="60" alt="Avatar"/>
                                  </a>
                             </div>
                             <div class="col-md-9">
                                 <ul class="ml-0 pl-1 mt-1 list-unstyled">
                                     <li>
                                         <p class="ml-3 text-dark mb-0 mt-1">
                                             <strong>${u.username}</strong>
                                         </p>
                                     </li>
                                     <li>
                                         <span class="ml-3 text-dark">${u.name}</span> 
                                     </li>
                                 </ul>
                             </div>
                             </c:forEach>
                         </div>
                         <div>
                             <small style="color:grey">Gợi ý cho bạn</small>
                             <span class="pull-right">Xem tất cả</span>
                         </div>
                         <div style="margin-top:20px">
                             <small style="color:grey; "> Người dùng tương tác nhiều nhất</small>
                         <!-- Suggestion User-->
                         <c:forEach items="${userLikeMost}" var="u">
                         <div class="row" style="margin-top: 20px;margin-bottom: 20px">
                             <div class="col-md-2">
                                 <a href="<c:url value="/user/profile/${u[2]}" />">
                                  <img src="<c:url value="${u[4]}" />"
                                       class="img-circle" style="margin-top: 2px" height="40" alt="Avatar"/>
                                  </a>
                             </div>
                             <div class="col-md-8">
                                 <ul class="ml-0 pl-1 mt-1 list-unstyled">
                                     <li>
                                         <small><strong>${u[2]}</strong></small>
                                     </li>
                                     <li>
                                         <span class="">${u[3]}</span> 
                                     </li>
                                 </ul>
                             </div>
                             <div class="col-md-2" style="">
                                 <p class="text-primary" style="margin-top:20px;">Follow</p>
                             </div>
                         </div>
                         </c:forEach>
                           </div>
                           <div style="margin-top:20px">
                             <small style="color:grey; "> Người dùng bình luận nhiều nhất</small>
                         <!-- Suggestion User-->
                         <c:forEach items="${userCommentMost}" var="u">
                         <div class="row" style="margin-top: 20px;margin-bottom: 20px">
                             <div class="col-md-2">
                                 <a href="<c:url value="/user/profile/${u[2]}" />">
                                  <img src="<c:url value="${u[4]}" />"
                                       class="img-circle" style="margin-top: 2px" height="40" alt="Avatar"/>
                                  </a>
                             </div>
                             <div class="col-md-8">
                                 <ul class="ml-0 pl-1 mt-1 list-unstyled">
                                     <li>
                                         <small><strong>${u[2]}</strong></small>
                                     </li>
                                     <li>
                                         <span class="">${u[3]}</span> 
                                     </li>
                                 </ul>
                             </div>
                             <div class="col-md-2" style="">
                                 <p class="text-primary" style="margin-top:20px;">Follow</p>
                             </div>
                         </div>
                         </c:forEach>
                           </div>
                        
                          <div style="margin-top:20px">
                             <small style="color:grey; "> Bài viết được nhiều người thảo luận nhất</small>
                         <!-- Suggestion User-->
                         <c:forEach items="${listPostMostInteract}" var="l">
                         <div class="row" style="margin-top: 20px;margin-bottom: 20px">
                             <div class="col-md-2">
                                 <a href="<c:url value="/user/comment/${l[1]}" />">
                                  <img src="<c:url value="${l[4]}" />"
                                       class="" style="margin-top: 2px" height="40" alt="Image"/>
                                  </a>
                             </div>
                             <div class="col-md-8">
                                 <ul class="ml-0 pl-1 mt-1 list-unstyled">
                                     <li>
                                         <small><strong>${l[3]}</strong></small>
                                     </li>
                                     <li>
                                         <span class="">${l[2]}</span> 
                                     </li>
                                 </ul>
                             </div>
                             <div class="col-md-2" style="">
                                 <span> <i class="fa fa-thumbs-up"></i>${l[5]}</span>
                                 <span><i class="fas fa-comments"></i>${l[0]}</span>  
                             </div>
                         </div>
                         </c:forEach>
                           </div>
                         <div style="margin-top:20px">
                             <small style="color:grey; "> Bài viết được tham gia đấu giá nhiều nhất</small>
                         <!-- Suggestion User-->
                         <c:forEach items="${postHaveHotAuctions}" var="l">
                         <div class="row" style="margin-top: 20px;margin-bottom: 20px">
                             <div class="col-md-2">
                                 <a href="<c:url value="/user/auctionpage/?username=${pageContext.request.userPrincipal.name}&post_id=${l[1]}" />">
                                  <img src="<c:url value="${l[4]}" />"
                                       class="" style="margin-top: 2px" height="40" alt="Image"/>
                                  </a>
                             </div>
                             <div class="col-md-8">
                                 <ul class="ml-0 pl-1 mt-1 list-unstyled">
                                     <li>
                                         <small><strong>${l[3]}</strong></small>
                                     </li>
                                     <li>
                                         <span class="">${l[2]}</span> 
                                     </li>
                                 </ul>
                             </div>
                             <div class="col-md-2" style="">
                                 <h5>Start Price</h5><span class="text-primary">$${l[5]}</span>
                                 <h5>Bid Price</h5> <span class="text-danger">$${l[6]}</span>  
                             </div>
                         </div>
                         </c:forEach>
                           </div>
                         
                     </section>
                     
                    
                 </div>


                 </div>
       
   
</div>
<script>
  window.onload = function(){
      
     calculateFunctionForPost()
     calculateFunctionNotify()
  }
</script> 