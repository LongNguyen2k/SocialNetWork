<%-- 
    Document   : header
    Created on : Aug 10, 2021, 3:24:53 PM
    Author     : Windows 10
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<header>
        <div>
            <nav class="navbar navbar-default hidden-xs navigation-clean">
                <div class="container">
                    <div class="navbar-header">
                        <a class="navbar-brand navbar-link" href="#"><i class="icon ion-ios-navigate"></i></a>
                        
                    </div>
                    <div class="collapse navbar-collapse" id="navcol-1">
                        <form class="navbar-form navbar-left" >
                            <div class="input-group" style="display: inline;">
                                <input type="text" class="form-control" placeholder="Search this blog">
                                <div class="input-group-append">
                                <button class="btn btn-secondary" type="submit">
                                    <i class="fa fa-search"></i>
                                  </button>
                                </div>
                                
                            </div>
                        </form>
                        <ul class="nav navbar-nav hidden-xs hidden-sm navbar-right">
                            <li  role="presentation"><a href="#">Timeline</a></li>
                            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false" href="#">Category<span class="caret"></span></a>
                                <ul class="dropdown-menu dropdown-menu-right" role="menu">
                                    <c:forEach var="cat" items="${categories}">
                                    <li role="presentation"><a href="#">${cat.name}</a></li>
                                    </c:forEach>
                                </ul>
                            </li>
                            <li role="presentation"><a href="#">Notifications</a></li>
                            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false" href="#">User <span class="caret"></span></a>
                                <ul class="dropdown-menu dropdown-menu-right" role="menu">
                                    <li role="presentation"><a href="#">My Profile</a></li>
                                    <li class="divider" role="presentation"></li>
                                    <li role="presentation"><a href="#">Logout </a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>    
</header>