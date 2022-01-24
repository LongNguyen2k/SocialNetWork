<%-- 
   Document   : header
   Created on : Aug 10, 2021, 3:24:53 PM
   Author     : Windows 10
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"  %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
<header>
    <c:url value="/user/" var="homePage" />
    <!--hidden-sm hidden-md hidden-lg -->
    <header class="hidden-sm hidden-md hidden-lg">
        <div class="">
            <div class="searchbox">
                <form>
                    <h1 class="text-left"><spring:message code="label.mainTitle" /></h1>
                    <div class="searchbox"><i class="glyphicon glyphicon-search"></i>
                        <input class="form-control" type="text" name="kw">
                    </div>
                    <div class="dropdown">
                        <button class="btn btn-link dropdown-toggle" data-toggle="dropdown" aria-expanded="false" type="button"><spring:message code="label.menu" /><span class="caret"></span></button>

                        <ul class="dropdown-menu dropdown-menu-right" role="menu">
                            <c:if test="${currentLoginUser != null }">
                                <li role="presentation"><a href="<c:url value="/user/profile/${pageContext.request.userPrincipal.name}" />"><spring:message code="label.myProfile" /></a></li>
                                <li class="divider" role="presentation"></li>
                                <li role="presentation"><a href="${homePage}"><spring:message code="h1.timeline" /></a></li>
                                <li role="presentation"><a href="<c:url value="/user/notification" />"><spring:message code="label.notifications" /></a></li> 

                                <c:forEach var="cat" items="${categories}">
                                    <li  role="presentation"><a  href="<c:url value="/user/?catPostId=${cat.id}" />">${cat.name}</a></li>
                                    </c:forEach>

                                <li role="presentation"><a href="<c:url value="/logout" />"><spring:message code="lable.logout" /> </a></li>
                                </c:if>
                                <c:if test="${currentLoginUser == null }">
                                <li role="presentation"><a href="<c:url value="/signin" />"><spring:message code="label.login" /></a></li>
                                <li class="divider" role="presentation"></li>
                                <li role="presentation"><a href="<c:url value="/signup" />"><spring:message code="label.signUp"/></a></li>
                                </c:if>
                        </ul>
                    </div>
                </form>
            </div>
            <hr>
        </div> 
    </header>


    <div>
        <nav class="navbar navbar-default hidden-xs navigation-clean">
            <div class="container">
                <h1 class="text-left" style="color: #cc0000;"><spring:message code="label.mainTitle" /></h1>
                <div class="navbar-header">
                    <a class="navbar-brand navbar-link" href="${homePage}"><i class="icon ion-ios-navigate"></i></a>
                </div>
                <div class="collapse navbar-collapse" id="navcol-1">
                    <form class="navbar-form navbar-left">
                        <div class="searchbox"><i class="glyphicon glyphicon-search"></i>
                            <input class="form-control" type="text" name="kw">
                        </div>
                    </form>
                    </form>
                    <!-- hidden hidden-md hidden-lg -->
                    <ul class="nav navbar-nav hidden-md hidden-lg navbar-right">
                        <!--Notification -->
                        <li id="noti_Container2" role="presentation" >
                            <div id="noti_Counter2"></div>   <!--SHOW NOTIFICATIONS COUNT.-->
                            <!--A CIRCLE LIKE BUTTON TO DISPLAY NOTIFICATION DROPDOWN.-->
                            <a href=""id="noti_Button2"><spring:message code="label.notification" /></a>  
                            <!--THE NOTIFICAIONS DROPDOWN BOX.-->
                            <div id="notifications2">
                                <h3  class="notifcationH3"><spring:message code="label.notifications" /></h3>
                                <c:forEach items="${notificationList}" var="nor">
                                    <div  style="width: 100%">
                                        <div class="media"style="border-bottom: 1px solid black">
                                            <div class="media-left">
                                                <img src="<c:url value="${nor[0]}" />" class="media-object" style="width:60px"/>
                                            </div>
                                            <div class="media-body">
                                                <h4 class="media-heading">${nor[1]}</h4>
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
                                    </div>
                                </c:forEach>
                                <div  class="seeAll"></div>
                            </div>
                        </li>
                        <!--                            <li  role="presentation" ><a href="#">Notifications</a></li>-->
                        <!-- Notification -->

                        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false" href="#">Category<span class="caret"></span></a>
                            <ul class="dropdown-menu dropdown-menu-right" role="menu">
                                <c:forEach var="cat" items="${categories}">
                                    <li  role="presentation"><a  href="<c:url value="/user/?catPostId=${cat.id}" />">${cat.name}</a></li>
                                    </c:forEach>
                            </ul>
                        </li>

                        <c:if test="${currentLoginUser == null }">
                            <li class="dropdown open"><a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true" href="#">User <span class="caret"></span></a>
                                <ul class="dropdown-menu dropdown-menu-right" role="menu">
                                    <li  role="presentation"><a href="${homePage}"><spring:message code="h1.timeline" /></a></li>
                                    <li class="divider" role="presentation"></li>
                                    <li role="presentation"><a href="<c:url value="/signin" />"><spring:message code="label.login" /></a></li>
                                    <li class="divider" role="presentation"></li>
                                    <li role="presentation"><a href="<c:url value="/signup" />"><spring:message code="label.signUp"/></a></li>
                                </ul>
                            </li>
                        </c:if>
                        <c:if test="${currentLoginUser != null }">
                            <li class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false" href="#">
                                    <c:if test="${currentLoginUser != null}">
                                        <img src="<c:url value="${currentLoginUser.avatar}" />"  class="img-circle" height="30" alt="Avatar"/>
                                    </c:if>

                                </a>
                                <ul class="dropdown-menu dropdown-menu-right" role="menu">
                                    <li  role="presentation"><a href="${homePage}">My Timeline</a></li>
                                    <li  role="presentation"><a  href = "<c:url  value="/user/profile/${pageContext.request.userPrincipal.name}" />"><spring:message code="label.myProfile"  /></a></li>
                                    <li class="divider" role="presentation"></li>
                                    <li role="presentation"><a href="<c:url value="/logout" />"><spring:message code="lable.logout" /> </a></li>
                                </ul>
                            </li> 
                        </c:if>
                    </ul>
                    <!-- hidden hidden-xs hidden-sm  -->
                    <ul class="nav navbar-nav hidden-xs hidden-sm  navbar-right">
                        <li  role="presentation"><a href="${homePage}"><spring:message code="h1.timeline" /></a></li>
                        <!--Notification -->
                        <li id="noti_Container" role="presentation" >
                            <div id="noti_Counter"></div>   <!--SHOW NOTIFICATIONS COUNT.-->
                            <!--A CIRCLE LIKE BUTTON TO DISPLAY NOTIFICATION DROPDOWN.-->
                            <a href=""id="noti_Button"><spring:message code="label.notification" /></a>  
                            <!--THE NOTIFICAIONS DROPDOWN BOX.-->
                            <div id="notifications">
                                <h3  class="notifcationH3"><spring:message code="label.notifications" /></h3>
                                <c:forEach items="${notificationList}" var="nor">
                                    <div  style="width: 100%">
                                        <div class="media" style="border-bottom: 1px solid black">
                                            <div class="media-left">
                                                <img src="<c:url value="${nor[0]}" />" class="media-object" style="height:100%; width: 60px"/>
                                            </div>
                                            <div class="media-body">
                                                <h4 class="media-heading">${nor[1]}</h4>
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
                                    </div>
                                </c:forEach>
                                <div  class="seeAll"></div>
                            </div>
                        </li>
                        <!--                            <li  role="presentation" ><a href="#">Notifications</a></li>-->
                        <!-- Notification -->

                        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false" href="#">Category<span class="caret"></span></a>
                            <ul class="dropdown-menu dropdown-menu-right" role="menu">
                                <c:forEach var="cat" items="${categories}">
                                    <li  role="presentation"><a  href="<c:url value="/user/?catPostId=${cat.id}" />">${cat.name}</a></li>
                                    </c:forEach>
                            </ul>
                        </li>

                        <c:if test="${currentLoginUser == null }">
                            <li class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false" href="#">
                                    User <span class="caret"></span></a>
                                <ul class="dropdown-menu dropdown-menu-right" role="menu">
                                    <li role="presentation"><a href="<c:url value="/signin" />"><spring:message code="label.login" /></a></li>
                                    <li class="divider" role="presentation"></li>
                                    <li role="presentation"><a href="<c:url value="/signup" />"><spring:message code="label.signUp"/></a></li>
                                </ul>
                            </li>
                        </c:if>
                        <c:if test="${currentLoginUser != null }">
                            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false" href="#">
                                    <c:if test="${currentLoginUser != null}">
                                        <img src="<c:url value="${currentLoginUser.avatar}" />"  class="img-circle" height="30" alt="Avatar"/>
                                    </c:if>

                                </a>
                                <ul class="dropdown-menu dropdown-menu-right" role="menu">
                                    <li role="presentation"><a href = "<c:url value="/user/profile/${pageContext.request.userPrincipal.name}" />"><spring:message code="label.myProfile" /></a></li>
                                    <li class="divider" role="presentation"></li>
                                    <li role="presentation"><a href="<c:url value="/logout" />"><spring:message code="lable.logout" /> </a></li>
                                </ul>
                            </li> 
                        </c:if>

                    </ul>
                </div>
            </div>
        </nav>
    </div>    
</header>

<script>
    $(document).ready(function () {
        // thêm biến kiểm tra nếu chưa đọc thì show count những bài viết chưa đọc khi mở form lên thì những bài viết chuyển thành 
        // đọc rồi và ko còn count nữa 
        // ANIMATEDLY DISPLAY THE NOTIFICATION COUNTER.
        $('#noti_Counter')
                .css({opacity: 0})
                .text('')      // ADD DYNAMIC VALUE (YOU CAN EXTRACT DATA FROM DATABASE OR XML).
                .css({top: '-10px'})
                .animate({top: '-2px', opacity: 1}, 500);

        $('#noti_Button').click(function () {

            // TOGGLE (SHOW OR HIDE) NOTIFICATION WINDOW.
            $('#notifications').fadeToggle('fast', 'linear', function () {
                if ($('#notifications').is(':hidden')) {
                    $('#noti_Button').css('background-color', '#FFF');
                }
                // CHANGE BACKGROUND COLOR OF THE BUTTON.
                else
                    $('#noti_Button').css('background-color', '#FFF');
            });

            $('#noti_Counter').fadeOut('slow');     // HIDE THE COUNTER.

            return false;
        });

        // HIDE NOTIFICATIONS WHEN CLICKED ANYWHERE ON THE PAGE.
        $(document).click(function () {
            $('#notifications').hide();

            // CHECK IF NOTIFICATION COUNTER IS HIDDEN.
            if ($('#noti_Counter').is(':hidden')) {
                // CHANGE BACKGROUND COLOR OF THE BUTTON.
                $('#noti_Button').css('background-color', '#FFF');
            }
        });

        $('#notifications').click(function () {
            return false;       // DO NOTHING WHEN CONTAINER IS CLICKED.
        });


        // ANIMATEDLY DISPLAY THE NOTIFICATION COUNTER.
        $('#noti_Counter2')
                .css({opacity: 0})
                .text('')      // ADD DYNAMIC VALUE (YOU CAN EXTRACT DATA FROM DATABASE OR XML).
                .css({top: '-10px'})
                .animate({top: '-2px', opacity: 1}, 500);

        $('#noti_Button2').click(function () {

            // TOGGLE (SHOW OR HIDE) NOTIFICATION WINDOW.
            $('#notifications2').fadeToggle('fast', 'linear', function () {
                if ($('#notifications2').is(':hidden')) {
                    $('#noti_Button2').css('background-color', '#FFF');
                }
                // CHANGE BACKGROUND COLOR OF THE BUTTON.
                else
                    $('#noti_Button2').css('background-color', '#FFF');
            });

            $('#noti_Counter2').fadeOut('slow');     // HIDE THE COUNTER.

            return false;
        });

        // HIDE NOTIFICATIONS WHEN CLICKED ANYWHERE ON THE PAGE.
        $(document).click(function () {
            $('#notifications2').hide();

            // CHECK IF NOTIFICATION COUNTER IS HIDDEN.
            if ($('#noti_Counter2').is(':hidden')) {
                // CHANGE BACKGROUND COLOR OF THE BUTTON.
                $('#noti_Button2').css('background-color', '#FFF');
            }
        });

        $('#notifications2').click(function () {
            return false;       // DO NOTHING WHEN CONTAINER IS CLICKED.
        });


    });

</script>                           



<!--    <c:if test="${NameError != null && userReceiveNotify == null }">
                                       <div class="alert alert-danger">${NameError}</div>
</c:if>
<c:if test="${userReceiveNotify != null }">
</c:if> 
-->
