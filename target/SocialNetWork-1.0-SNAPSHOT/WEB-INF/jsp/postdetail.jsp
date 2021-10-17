<%-- 
    Document   : postdetail
    Created on : Sep 21, 2021, 12:05:16 PM
    Author     : Windows 10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"  %>
<c:url value="/user/addBidding/" var="addBiddingForPost" />
 <c:forEach var="biddingInfo" items="${postBiddingInfo}">
<div class="container" style ="">
   
    <div class="row">
        <div class="col-md-12">
            <div class="product-title">
                <h3> <spring:message code="auction.info" /></h3>
            
                <hr>
            </div>
        </div>
    </div>
	<div class="row">
	    <div class="col-md-8">
	        <div id="myCarousel" class="carousel slide" data-interval="false">
 
                    <img src="<c:url value="${biddingInfo[8]}" />" style="width:100%" class="img-responsive">
</div>
            
	    </div>
		<div class="col-md-4">
                <div class="product-widget ">
                    <ul class="list-group">
                                    
                        <li class="list-group-item"> <span class="badge">${biddingInfo[7]} </span><spring:message code="auction.startAt" /></li>
                        <li class="list-group-item"> <span class="badge"> ${biddingInfo[10]} likes</span><spring:message code="auction.rating" /></li>
                        <li class="list-group-item"> <span class="badge">${biddingInfo[1]}</span><spring:message code="auction.publish" /></li>
                        <li class="list-group-item"> <span class="badge">${biddingInfo[5]}</span><spring:message code="auction.email" /></li>
                        <li class="list-group-item"> <span class="badge">$${biddingInfo[9]}</span> <spring:message code="auction.startPrice" /> </li>
                                <c:if test="${currentMaxBiddPrice != null }">
                                <li class="list-group-item"> <span class="badge">$${currentMaxBiddPrice}</span><spring:message code="auction.currentWin" /></li>
                               </c:if>
                                <c:if test="${currentMaxBiddPrice == null }">
                                <li class="list-group-item"> <span class="badge">${currentMaxBiddPrice}</span><spring:message code="auction.noOneOffer"/></li>
                               </c:if>
                    </ul>
                </div>
                <div class="product-auction-form">
    <c:if  test="${ pageContext.request.userPrincipal.name.toString().toLowerCase() != biddingInfo[12] }">
<form  cssClass="form-inline" action="${addBiddingForPost}">
  <div class="form-group">
      <input id="biddingPrice" name="biddingPrice"  type="number" placeholder="Nhập vào Số tiền Đấu Thầu" cssClass="form-control input-md" required=""/>
      <button type="submit" class="btn btn-danger"><spring:message code="auction.Bid" /></button>
       <a class="btn btn-primary"  href="<c:url value="/user/" />">
           <spring:message code="label.cancel" />
        </a>
  </div>
     <c:if test="${BiddingPriceAccept != null}">
        <div class="success alert-success">${BiddingPriceAccept}</div>
    </c:if>
    <c:if test="${errorBiddingPrice != null}">
                           <div class="alert alert-danger">${errorBiddingPrice}</div>
    </c:if>
    <c:if test="${BiddingPriceTooSmall != null}">
                           <div class="alert alert-danger">${BiddingPriceTooSmall}</div>
    </c:if>
    <c:if test="${AuctionExpire != null}">
        <div class="success alert-success">${AuctionExpire}</div>
    </c:if> 
   
   <input type="hidden" name="username" value="${pageContext.request.userPrincipal.name}" />
   <input type="hidden" name="post_id" value="${biddingInfo[11]}" />
 
</form>  
   </c:if>                    
</div>
</div>
	</div>
	<hr>

</div>

<div class="container product-detail-area">
<div class="row">
    <div class="col-md-8">
        <div class="panel-group" id="accordion">
  <div class="panel panel-default">
    <div class="panel-heading">
      <h4 class="panel-title">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">
            <spring:message code="auction.Description" /></a>
      </h4>
    </div>
    <div id="collapse1" class="panel-collapse collapse in">
      <div class="panel-body">
          <p style="word-break: break-word;">
           ${biddingInfo[6]}
        </p>
      </div>
    </div>
  </div>

  <div class="panel panel-default">
      <c:if test="${pageContext.request.userPrincipal.name.toString().toLowerCase() == biddingInfo[12]}">
    <div class="panel-heading">
      <h4 class="panel-title">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapse2">
            <spring:message code="auction.details" /></a>
      </h4>
    </div>
    <div id="collapse2" class="panel-collapse collapse">
      <div class="panel-body">
          <table class="table table-hover">
    <thead>
      <tr>
          <th><spring:message code="auction.Time" /></th>
          <th><spring:message code="auction.UserBid" /></th>
          <th><spring:message code="auction.BidPrice"/></th>
        <th><spring:message code="auction.status"/></th>
        <th><spring:message code="auction.chooseWinner" /> </th>
        
      </tr>
    </thead>
    <tbody>
    <c:forEach var="bidding" items="${biddingPriceList}">
      <tr>
        <td>${bidding.biddingAt}</td>
        <td>${bidding.biddingUser.name}</td>
        <td>${bidding.biddingPrice}$</td>
        
        <td>
            <c:if test="${bidding.biddingStatus == false}">
                <spring:message code="auction.Pending" />
            </c:if>
            <c:if test="${bidding.biddingStatus == true}">
                <spring:message code="auction.win" />
            </c:if>
        </td>
        <td> <a class="btn btn-success"  href="<c:url value="/user/chooseWinnerBid/?username=${bidding.biddingUser.username}&post_id=${bidding.biddingPost.id}&biddingPrice=${bidding.biddingPrice}&biddingAt=${bidding.biddingAt}" />"> <spring:message code="auction.buttonChoose" />  </a>  </td>
       
      
      </c:forEach>
         <td> 
            <c:if test="${ChooseWinnerPopup != null}">
                <div class="success alert-success">${ChooseWinnerPopup}</div>
            </c:if>
            <c:if test="${AuctionExpire != null}">
                <div class="success alert-success">${AuctionExpire}</div>
            </c:if>                  
   
        </td>
             
     </tr>
    </tbody>
  </table>
          
      </div>
    </div>
    </c:if>  
  </div>

  <div class="panel panel-default">
    <div class="panel-heading">
      <h4 class="panel-title">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapse3">
            <spring:message code="auction.user.createPost" /></a>
      </h4>
    </div>
    <div id="collapse3" class="panel-collapse collapse">
      <div class="panel-body">
        <div class="media">
            <div class="media-left">
                <img src="<c:url value="${biddingInfo[0]}" />" class="media-object" style="width:60px">
            </div>
            <div class="media-body">
                <h2 class="media-heading" style="color: #cc0000 ;"><spring:message code="auction.user.name" /> ${biddingInfo[1]}</h2>
                <h3><spring:message code="auction.user.phone" /> ${biddingInfo[2]}</h3>
                <p><spring:message code="auction.user.gender" /> ${biddingInfo[3]}</p>
              <h6> <spring:message code="auction.user.birthday" /> ${biddingInfo[4]}</h6>
            </div>
          </div>
      </div>
    </div>
  </div>
</div>
    </div>
   
</div>
</div>
</c:forEach>
<!--
 <div class="modal" id="myModel">
     <div class="modal-dialog">
         <div class="modal-content">
             <div class="modal-header">
                 <h4 class="modal-title">Warning</h4>
                 <button type="button" class="close" data-dismiss="modal">&times;</button>
             </div>
             <div class="modal-body">
                 <span> This is content</span>
             </div>
             <div class="modal-footer">
                  <button type="button" class="btn btn-success" data-dismiss="modal">Close</button>
             </div>
         </div>
     </div>
 </div>-->
