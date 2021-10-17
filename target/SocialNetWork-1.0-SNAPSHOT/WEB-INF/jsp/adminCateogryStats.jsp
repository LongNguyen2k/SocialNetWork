<%-- 
    Document   : adminPage
    Created on : Aug 24, 2021, 11:59:03 AM
    Author     : Windows 10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    <h1 class="text-center text-danger"> Thống KÊ BÀI VIẾT THEO DANH MỤC</h1>
    
    <div>
      <canvas id="myCategoryPostChart"></canvas>
    </div>
    
    <table class="table">
        <tr>
            <th><spring:message code="label.id" /></th>
            <th> <spring:message code="label.categoryName" /> </th>
            <th><spring:message code="label.quantity" /></th>
        </tr>
        <c:forEach items="${categoryPostStats}" var="cate" >
        <tr>
            <td> ${cate[0]} </td>
            <td>  ${cate[1]} </td>
            <td> ${cate[2]}</td>
        </tr>
        </c:forEach>
    </table>
        
<script>
    
    let cateLabels=[],cateInfo=[];
    
    <c:forEach items="${categoryPostStats}" var="cate">
        cateLabels.push('${cate[1]}')
        cateInfo.push(${cate[2]})
    </c:forEach>
    
    
    window.onload = function (){
        cateChart("myCategoryPostChart",cateLabels,cateInfo) 

    }
</script>

