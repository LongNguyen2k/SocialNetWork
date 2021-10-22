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
        <div class="timelineposts">
               <!-- Mỗi Li là 1 bài đăng mới -->
              <div class="col-md-6">
                  <ul class="list-group">
                      <div class="post-component"> 
                    
                      <li class="list-group-item"> 
                           <blockquote>                     
                  <div class="row" style="margin-bottom: 5px;"> 
                      <div class="col-md-6">
                          <div class="media">
                              <div class="media-left">
                                   <a href="<c:url value="/user/profile/${postDetail[0].username}" />">
                                  <img src="<c:url value="${postDetail[0].avatar}" />" alt="" width="50" class="img-circle" >
                                   </a>
                              </div>
                              <div class="media-body">
                                  <span class="font-weight-bold">${postDetail[0].name}</span>                  
                                   <div class="my-datePost"><i class="text-primary">${postDetail[3]}</i></div>
                               </div>

                          </div>
                      </div> 
                      <c:if test="${postDetail[0].username != pageContext.request.userPrincipal.name}">
                      <div class="pull-right">
                          <ul class="nav navbar-nav">
                              <li class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#"><i class="fa fa-ellipsis-h"></i>
                                <ul class="dropdown-menu">
                                    <li><a href="<c:url value="/user/SendReportPage/?postID=${postDetail[5]}&username=${pageContext.request.userPrincipal.name}" />"style="color:red;"><spring:message code="button.ReportPost" /> </a></li>
                                </ul>
                              </li>
                       </ul> 
                     </div> 
                      </c:if>
                  </div>      
                          <img src="<c:url value="${postDetail[1]}" />" alt="" width="100%"  />     
                          <div class="row ">
                              <br/>
                                  <p style="word-break: break-word;">
                                      ${postDetail[2]}
                                  </p>
                            </div>
                                      <footer class="text-primary"><spring:message code="footer.label" /> ${postDetail[0].name}  #${postDetail[6].name}
                               <button type="submit" onclick="addLikeOrUnLike('${postDetail[5]}','${pageContext.request.userPrincipal.name}')"  class="btn btn-default like"  > <i class="glyphicon glyphicon-heart" data-aos="flip-right"></i><span> ${postDetail[4]} <spring:message code="span.like" /></span></button>
                              <a href="<c:url value="/user/auctionpage/?username=${pageContext.request.userPrincipal.name}&post_id=${postDetail[5]}" />"  class="btn btn-default like"  > <i class="glyphicon glyphicon-usd" data-aos="flip-right"></i><span> <spring:message code="label.auction" /></span></a>                            
                          </footer>
                          </blockquote> 
                      </li>
                      

                      </div>

                  </ul>


              </div>



          </div>    
        
    	<div class="row">
            <div class="col-sm-8">
                 <c:forEach items="${userCurrentComment}" var="userCmt">
                <form> <!-- Form Dung để thực hiện phương thức get -->
                    <h3 class="pull-left"><spring:message code="label.newCmt" /></h3>
                    
                    <button onclick="addComment(${post_id},'${pageContext.request.userPrincipal.name}')" class="btn btn-default comment  pull-right" type="submit">
                                <i class="glyphicon glyphicon-flash"></i><span><spring:message code="label.addCmt" /></span>
                           </button>
                           
                    <fieldset>
                        <div class="row">
                            <div class="col-sm-3 col-lg-2 hidden-xs">
                                <img class="img-responsive img-circle" src="<c:url value="${userCmt.avatar}" />" alt="">
                            </div>
                            <div class="form-group col-xs-12 col-sm-9 col-lg-10">
                                <textarea  rows="3" id="commentID" class="form-control" placeholder="Write Comment"></textarea>
                            </div>

                        </div>  	
                    </fieldset>  
                </form>
                <h3>${countComment} <span><spring:message code="label.CMT" /></h3>
                <!-- COMMENT 1 - START -->
            <div id="commentArea"> 
                 <c:forEach items="${commentOfPost}" var="cmt"> 
                    <c:if test="${cmt[7] == false}">
                            <div class="media">
                                <a class="pull-left" href="<c:url value="/user/profile/${cmt[6]}" />"><img class="media-object img-circle" src="<c:url value="${cmt[3]}" />" alt=""></a>
                                <div class="media-body">
                                    <h4 class="media-heading">${cmt[0]}</h4>
                                    <p>${cmt[2]}</p>
                                    <ul class="list-unstyled list-inline media-detail pull-left">

                                        <li><div class="my-date"><i class="">${cmt[1]}</i></div></li>
                                        <li><i class="fa fa-thumbs-up"></i>${cmt[4]}</li>

                                    </ul>
                                    <ul class="list-unstyled list-inline media-detail pull-right">
                                        <c:if test="${cmt[6] != pageContext.request.userPrincipal.name}">
                                         <li class=""><a href="<c:url value="/user/SendReportCommentPage/?commentID=${cmt[5]}&username=${pageContext.request.userPrincipal.name}" />"><spring:message code="label.reportComment" /></a></li>
                                        </c:if>
                                        <li class=""><a href="<c:url value="/user/likesComment?username=${pageContext.request.userPrincipal.name}&comment_id=${cmt[5]}&post_id=${cmt[8]}"/> "><spring:message code="label.like" /></a></li>
                                    </ul>
                                </div>
                            </div>
                    </c:if>       
                    <c:if test="${cmt[7] == true}">
                            <div class="media">
                           <a class="pull-left" href="#"></a>
                           <div class="media-body">
                               <h4 class="media-heading"></h4>
                               <p><spring:message code="label.commentBeingReport" /></p>
                               <ul class="list-unstyled list-inline media-detail pull-left">
                                   <li><i class="fa fa-calendar"></i><spring:message code="label.timePost" /></li>
                                   <li><i class="fa fa-thumbs-up"></i></li>
                               </ul>

                           </div>
                       </div>            
                    </c:if>
                  </c:forEach>
            </div>   
                <!-- COMMENT 1 - END -->
                </c:forEach>              
            </div>
        </div>
    </div>
</section>
                          
<script>
  window.onload = function(){
      
      calculateFunction()
       calculateFunctionForPost()
  }
</script>                         
~