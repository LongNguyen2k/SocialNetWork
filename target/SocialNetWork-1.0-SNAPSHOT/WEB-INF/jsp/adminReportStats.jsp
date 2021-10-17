<%-- 
    Document   : adminReportStats
    Created on : Oct 13, 2021, 3:56:43 PM
    Author     : Windows 10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    <h1 class="text-center text-danger"> Thống KÊ REPORT BÀI VIẾT,BÌNH LUẬN THEO DANH MỤC</h1>
    
    <div>
      <canvas id="reportPostCommentChart"></canvas>
    </div>
    
    <table class="table">
        <tr>
            <th><spring:message code="label.id" /></th>
            <th> <spring:message code="label.categoryName" /> </th>
            <th><spring:message code="label.reportPostQuantity" /></th>
        </tr>
        <c:forEach items="${reportPostChart}" var="rP" >
        <tr>
            <td> ${rP[0]} </td>
            <td>  ${rP[1]} </td>
            <td> ${rP[2]}</td>
        </tr>
        </c:forEach>
    </table>
    <table class="table">
        <tr>
            <th><spring:message code="label.id" /></th>
            <th> <spring:message code="label.categoryName" /> </th>
            <th><spring:message code="label.reportCommentQuantity" /></th>
        </tr>
        <c:forEach items="${reportCommentChart}" var="rC" >
        <tr>
            <td> ${rC[0]} </td>
            <td>  ${rC[1]} </td>
            <td> ${rC[2]}</td>
        </tr>
        </c:forEach>
    </table>
    
        
<script>
    
    let cateLabels=[],postInfo=[],commentInfo=[];
    
    <c:forEach items="${reportPostChart}" var="rP">
        cateLabels.push('${rP[1]}')
        postInfo.push(${rP[2]})
    </c:forEach>
      <c:forEach items="${reportCommentChart}" var="rC">
        commentInfo.push(${rC[2]})
    </c:forEach>   
    
    
    window.onload = function (){
        reportChart("reportPostCommentChart",cateLabels,postInfo,commentInfo) 

    }
</script>