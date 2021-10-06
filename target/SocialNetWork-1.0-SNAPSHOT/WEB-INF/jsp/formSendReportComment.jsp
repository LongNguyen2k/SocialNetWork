<%-- 
    Document   : formSendReportComment
    Created on : Oct 1, 2021, 3:15:49 PM
    Author     : Windows 10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="/user/sendReportComment" var="SendReportComment" />
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
   
            <c:if test="${errMsG != null}">
                 <div class="alert alert-danger">${errMsG}</div>
            </c:if>
            
                 <form:form method="post" modelAttribute="ReportComment" action="${SendReportComment}" cssClass="form-horizontal" cssStyle="background-color: white;" enctype="multipart/form-data"  >
            <fieldset>
                <legend class="text-center" style="font-weight: bold"><spring:message code="label.ReportLabelMainComment" /></legend>
            
              <form:errors path="*" element="div" cssClass="alert alert-danger" />
            <div class="form-group">
                <label class="col-md-3 control-label" for="adminReceiveReport"><spring:message code="label.ReportToAdmin" /></label>
                
                <div class="col-md-9">
                     <form:select id="adminReceiveReport" path="adminReceiveReport" class="form-control">
                            <c:forEach items="${adminReceiveReport}" var="u">
                                <option value="${u.id}">${u.name}</option>
                            </c:forEach>
                        </form:select>
                        <form:errors path="adminReceiveReport" cssClass="text-danger"  />         
                </div>
            </div>
              
            <div class="form-group">
                <form:input type="hidden" path="commentsReport" value="${commentSelectedReport}" />
                  <form:errors path="commentsReport" cssClass="text-danger"  />     
            </div>
            
             <div class="form-group">
                 <form:input type="hidden" path="userSendReport" value="${userSendReport.id}" />
                   <form:errors path="userSendReport" cssClass="text-danger"  />     
            </div>
              
              
            
            <div class="form-group">
              <label class="col-md-3 control-label" for="reasonReport"><spring:message code="label.Reason" /></label>
              <div class="col-md-9">
                  <form:textarea class="form-control"  path="reasonReport" rows="5"></form:textarea>
                  <form:errors path="reasonReport" cssClass="text-danger"  />
              </div>
            </div>
            
    
          
            <div class="form-group">
              <div class="col-md-12 text-right">
                <button type="submit" class="btn btn-danger btn-lg"><spring:message code="button.SendReport" /></button>
                <a class="btn btn-default btn-lg"  href="<c:url value="/user/" />">
    		             <spring:message code="button.CancelReport" />
                </a>
              </div>
                
            </div>
          </fieldset>
    </form:form>
      
      </div>
	</div>
</div>