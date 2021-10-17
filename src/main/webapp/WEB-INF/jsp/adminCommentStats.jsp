<%-- 
    Document   : adminCommentStats
    Created on : Oct 13, 2021, 1:39:08 PM
    Author     : Windows 10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    <h1 class="text-center text-danger"> Thống kê SỐ BÌNH LUẬN THEO DANH MỤC</h1>
    <div>
        <canvas id="myCommentChart"></canvas>
    </div>
  <table class="table">
        <tr>
            <th><spring:message code="label.id" /></th>
            <th> <spring:message code="label.categoryName" /> </th>
            <th><spring:message code="label.commentQuantity" /></th>
        </tr>
        <c:forEach items="${commentStats}" var="c" >
        <tr>
            <td> ${c[0]} </td>
            <td>  ${c[1]} </td>
            <td> ${c[2]}</td>
        </tr>
        </c:forEach>
</table>
<script>
    let cateLabels=[] , commentInfo=[];
        <c:forEach items="${commentStats}" var="c">
           cateLabels.push('${c[1]}')
           commentInfo.push(${c[2]})
        </c:forEach>
        window.onload = function(){

            commentChart("myCommentChart",cateLabels,commentInfo)
        }
</script>
