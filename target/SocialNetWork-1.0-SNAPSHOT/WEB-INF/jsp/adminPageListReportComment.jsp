<%-- 
    Document   : adminPageListReportComment
    Created on : Oct 4, 2021, 4:17:32 PM
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
                <th>Nội dung Bình luận</th>
                <th>Người viết bình luận</th>
                <th>Người gửi báo cáo</th>
                <th>Lý do vi phạm</th>
                <th>Trạng thái kiểm duyệt</th>
                <th>Phê duyệt báo cáo</th>
                <th>&nbsp;</th>
            </thead>
            <tbody>
                 <c:forEach items="${listReportComment}" var="r">
                <tr class="align-middle alert border-bottom" role="alert">
                   
                    <td> ${r.sendReportAt} </td>
                    <td>
                        <div>
                            <p style="word-break: break-word;"> ${r.commentsReport.comment}</p>
                        </div>
                    </td>
                    <td>
                        <div class="fw-600">
                            <p>${r.commentsReport.user.name}</p>
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
                            <a href="<c:url value="/admin/removeComment?comment_ID=${r.commentsReport.id}&reportID=${r.id}" />"  style="margin:5px;" class="btn btn-danger">Gỡ bình luận khỏi bài viết</a>
                             <a href="<c:url value="/admin/deniedRemoveComment?comment_ID=${r.commentsReport.id}&reportID=${r.id}" />"  style="margin:5px;" class="btn btn-success"> Bỏ qua báo cáo </a>
                        </c:if>
                    </td>
                    
                </tr>
                </c:forEach> 
            </tbody>
        </table>
    </div>
</div>
