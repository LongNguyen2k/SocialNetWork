<%-- 
    Document   : formReportPost
    Created on : Sep 27, 2021, 4:47:03 PM
    Author     : Windows 10
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
               
          <form class="form-horizontal" action="" method="post"style="background-color: white">
          <fieldset>
              <legend class="text-center" style="font-weight: bold">Báo Cáo Bài Viết Vi Phạm Tiêu Chuẩn Cộng Đồng</legend>

            <!-- Message body -->
            <div class="form-group">
              <label class="col-md-3 control-label" for="message">Lý do:</label>
              <div class="col-md-9">
                  <textarea class="form-control" id="message" name="message" placeholder="Nhập lý do bài viết vi phạm tiêu chuẩn cộng động..." rows="5" required=""></textarea>
              </div>
            </div>
    
            <!-- Form actions -->
            <div class="form-group">
              <div class="col-md-12 text-right">
                <button type="submit" class="btn btn-danger btn-lg">Gửi Báo Cáo</button>
                <a class="btn btn-default btn-lg"  href="<c:url value="/user/" />">
    		            Hủy bỏ
                </a>
              </div>
                
            </div>
          </fieldset>
          </form>
      
      </div>
	</div>
</div>
                            
                            <!-- Mai thiết kế database insert thông tin-->