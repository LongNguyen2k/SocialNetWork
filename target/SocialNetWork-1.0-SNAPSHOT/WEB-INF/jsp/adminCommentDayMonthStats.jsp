<%-- 
    Document   : adminCommentDayMonthStats
    Created on : Oct 13, 2021, 2:52:45 PM
    Author     : Windows 10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    <h1 class="text-center text-danger"> THỐNG KÊ SỐ LƯỢNG BÌNH LUẬN THEO TỪ KHÓA DANH MỤC THEO THOI GIAN</h1>
    
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
      <canvas id="myCommentFromCategoryChart"></canvas>
    </div>
    
    <table class="table">
        <tr>
            <th><spring:message code="label.DayMonth" /></th>
            <th><spring:message code="label.commentQuantity" /></th>
        </tr>
        <c:forEach items="${commentDayMonthStats}" var="c" >
        <tr>
            <td> ${c[0]}/${c[1]} </td>
            <td> ${c[2]}</td>
        </tr>
        </c:forEach>
    </table>
        
<script>
    let commentLabels=[], commentInfo=[]
        <c:forEach items="${commentDayMonthStats}" var="c" >
            commentLabels.push('${c[0]}/${c[1]}')
            commentInfo.push('${c[2]}')
        </c:forEach>
            
            window.onload = function(){
                commentDayMonthChart("myCommentFromCategoryChart",commentLabels ,commentInfo)  
            }
</script>