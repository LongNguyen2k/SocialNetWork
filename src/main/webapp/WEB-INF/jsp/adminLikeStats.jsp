<%-- 
    Document   : adminLikeStats
    Created on : Oct 13, 2021, 9:59:25 AM
    Author     : Windows 10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    <h1 class="text-center text-danger"> Thống kê SỐ LIKE THEO DANH MUC</h1>
    <div>
        <canvas id="myLikeChart"></canvas>
    </div>
  <table class="table">
        <tr>
            <th><spring:message code="label.id" /></th>
            <th> <spring:message code="label.categoryName" /> </th>
            <th><spring:message code="label.likeQuantity" /></th>
        </tr>
        <c:forEach items="${likeStats}" var="like" >
        <tr>
            <td> ${like[0]} </td>
            <td>  ${like[1]} </td>
            <td> ${like[2]}</td>
        </tr>
        </c:forEach>
</table>
        
<script>
    let cateLabels=[] , likeInfo=[];
    
    <c:forEach items="${likeStats}" var="like">
        cateLabels.push('${like[1]}')
        likeInfo.push(${like[2]})
    </c:forEach>
    window.onload = function(){
        likeChart("myLikeChart",cateLabels,likeInfo)
    }
</script>
        
        