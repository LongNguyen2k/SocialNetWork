<%-- 
    Document   : adminPostStats
    Created on : Oct 12, 2021, 7:31:06 PM
    Author     : Windows 10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    <h1 class="text-center text-danger"> THỐNG KÊ SỐ LƯỢNG BÀI VIẾT THEO TỪ KHÓA DANH MỤC THEO THOI GIAN</h1>
    
    <form action="">
          <div class="form-group"> 
            <label><spring:message code="label.keyWordCategory" /></label>
            <input type="text" name="kw" class="form-control" />
        </div>   
        <div class="form-group"> 
            <label><spring:message code="label.startTime" /></label>
            <input type="date" name="fromDate" class="form-control" />
        </div>
         <div class="form-group"> 
            <label><spring:message code="label.endTime" /></label>
            <input type="date" name="toDate" class="form-control" />
        </div>   
            <input type="submit" value="Tìm Kiếm" class="btn btn-success" />
    </form>
    
    
    <div>
      <canvas id="myPostFromCategoryChart"></canvas>
    </div>
    
    <table class="table">
        <tr>
            <th><spring:message code="label.MonthYEAR" /></th>
            <th><spring:message code="label.quantity" /></th>
        </tr>
        <c:forEach items="${postStats}" var="POST" >
        <tr>
            <td> ${POST[0]}/${POST[1]} </td>
            <td> ${POST[2]}</td>
        </tr>
        </c:forEach>
    </table>
        
<script>
    let postLabels=[], postInfo=[]
        <c:forEach items="${postStats}" var="POST" >
            postLabels.push('${POST[0]}/${POST[1]}')
            postInfo.push('${POST[2]}')
        </c:forEach>
            
            window.onload = function(){
                postChartFromCategory("myPostFromCategoryChart",postLabels ,postInfo  )
                
            }
</script>