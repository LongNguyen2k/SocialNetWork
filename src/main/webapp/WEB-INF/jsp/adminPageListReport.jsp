<%-- 
    Document   : adminPageListReport
    Created on : Oct 1, 2021, 9:04:12 PM
    Author     : Windows 10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link rel="stylesheet" href="<c:url value="/css/StyleListReport.css" />" />
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"/>
<div class="container">
    <div class="table-wrap">
        <table class="table table-responsive table-hover">
            <thead>
                <th>Thời điểm gửi báo cáo</th>
                <th>Ảnh bài viết</th>
                <th>Nội dung</th>
                <th>Người đăng bài viết</th>
                <th>Người gửi báo cáo</th>
                <th>Lý do vi phạm</th>
                <th>Trạng thái kiểm duyệt</th>
                <th>Phê duyệt báo cáo</th>
                <th>&nbsp;</th>
            </thead>
            <tbody>
                 <c:forEach items="${listReportPost}" var="r">
                <tr class="align-middle alert border-bottom" role="alert">
                   
                    <td> ${r.sendReportAt} </td>
                    <td class="text-center"> <img class="pic" src="<c:url value="${r.postReport.image}" />" alt=""> </td>
                    <td>
                        <div>
                            <p style="word-break: break-word;"> ${r.postReport.content}</p>
                        </div>
                    </td>
                    <td>
                        <div class="fw-600">
                            <p>${r.postReport.user.name}</p>
                        </div>
                    </td>
                    <td><p> ${r.userSendReport.name} </p> </td>
                    <td>  <p> ${r.reasonReport} </p></td>
                    <td> 
                            <c:if test="${r.checkVerified == true}">
                                 <p class="text-success"> Đã duyệt báo cáo </p>
                            </c:if>
                             <c:if test="${r.checkVerified == false}">
                                 <p class="text-danger"> Chưa duyệt báo cáo </p>
                            </c:if>
                    </td>
                    <td>
                        <c:if test="${r.checkVerified == false}">
                            <a href="<c:url value="/admin/removePost?postID=${r.postReport.id}&reportID=${r.id}" />"  style="margin:5px;" class="btn btn-danger">Gỡ bài viết khỏi dòng trạng thái</a>
                             <a href="<c:url value="/admin/deniedRemovePost?postID=${r.postReport.id}&reportID=${r.id}" />"  style="margin:5px;" class="btn btn-success"> Bỏ qua báo cáo </a>
                        </c:if>
                    </td>
                    
                </tr>
                </c:forEach> 
            </tbody>
        </table>
    </div>
</div>