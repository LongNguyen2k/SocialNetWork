/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function addComment(post_id,username) {
    fetch("/SocialNetWork/user/api/add-comment", {
        method: 'post',
        body: JSON.stringify({
            "content":document.getElementById("commentID").value,
            "post_id":post_id,
            "username":username 
        }),
        headers:{
            "Content-type":"application/json"
        }
    }).then(function(res) {
        console.info(res)
        return res.json();
    }).then(function(data){
        console.info(data);
        let area = document.getElementById("commentArea");
        area.innerHTML =`
         <div class="media">
            <a class="pull-left" href="#"><img class="media-object img-circle" src="<c:url value="${data.user.avatar}" />" alt=""></a>
            <div class="media-body">
                <h4 class="media-heading">${data.user.name}</h4>
                <p>${data.comment}</p>
                <ul class="list-unstyled list-inline media-detail pull-left">

                    <li><div class="my-date"><i class="">${data.postAt}</i></div></li>
                    <li><i class="fa fa-thumbs-up"></i>${moment(data.likes).fromNow()}</li>

                </ul>
                <ul class="list-unstyled list-inline media-detail pull-right">
                    <c:if test="${cmt[6] != pageContext.request.userPrincipal.name}">
                     <li class=""><a href="<c:url value="/user/SendReportCommentPage/?commentID=${cmt[5]}&username=${pageContext.request.userPrincipal.name}" />"><spring:message code="label.reportComment" /></a></li>
                    </c:if>
                    <li class=""><a href="<c:url value="/user/likesComment?username=${pageContext.request.userPrincipal.name}&comment_id=${cmt[5]}&post_id=${post_id}"/> "><spring:message code="label.like" /></a></li>
                </ul>
            </div>
        </div>
        ` + area.innerHTML
    })
}

function addLikeOrUnLike(post_id,username, countLike)
{
    fetch("/SocialNetWork/user/api/addLikeOrUnLike",{
        method: 'post',
        body: JSON.stringify({
            "post_id":post_id,
            "username":username
        }),
        headers:{
            "Content-Type":"application/json"
        }
    }).then(function(res){
        return res.json();
    }).then(function(data) {
        console.info(data);
        location.reload()
//        let currentCounterLike = document.getElementsByClassName("likeCount");
////        currentCounterLike.innerText = countLike;
//        if(data.code == 200 )
//        {
//            countLike-1;
//            currentCounterLike.innerText = countLike;
//        }
//        else
//            if (data.code == 201)
//                {
//                    countLike+1;
//                    currentCounterLike.innerText = countLike;
//                }
           
    })
}



