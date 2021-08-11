<%-- 
    Document   : profile
    Created on : Aug 10, 2021, 7:50:16 PM
    Author     : Windows 10
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container">
        <h1>Username's Profile 
        <i class="glyphicon glyphicon-ok-sign verified username_Profile" data-toggle="tooltip"title="Verified User"></i></h1>
</div>
<div class="container">

<!-- About Me Section -->
<div class="row text_Profile" >
    <div class="col-md-3">
        <ul class="list-group">
            <c:forEach var="u" items="${userProfile}">
            <li class="list-group-item">
                <img src="https://images.unsplash.com/photo-1522075469751-3a6694fb2f61?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=80" alt="..." width="130" class="rounded mb-2 img-thumbnail">
            </li>
            <li class="list-group-item">  
               <div class="row">
                   <div class="col-sm-6">
                       <p>Name</p>
                       <h6>${u.name}</h6>
                   </div>
                   <div class="col-sm-6">
                    <p>Email</p>
                    <h6>${u.email}</h6>
                 </div>
               </div>
               <div class="row">
                    <div class="col-sm-6">
                        <p>Gender</p>
                        <h6>${u.gender}</h6>
               </div>
               <div class="row">
                    <div class="col-sm-6">
                        <p>BirthDay</p>
                        <h6>2000-01-01</h6>
                    </div>
                </div>
            </li>
            </c:forEach>
        </ul>
        <button class="btn btn-default buttonProfile " type="button">Cập nhật Profile</button>
    </div>


    <!-- Posting Section-->

    <!-- Mỗi li là 1 post trong những post -->
    <div class="col-md-6">
    <ul class="list-group">
        <div class="post-component">
        <li class="list-group-item">

            <blockquote>
                <p>@Francis Hello World</p>
                <img src="https://i.imgur.com/xhzhaGA.jpg" class="img-fluid" />                          
                <div class="row ">
                    <br/>
                        <p style="word-break: break-word;">
                            Chủ tịch Quốc hội nhấn mạnh, các cán bộ, thầy thuốc, y bác sĩ và nhân viên ngành y tế không chỉ là những người cứu chữa người bệnh mà còn là những chiến sĩ, những người anh hùng không quản ngại nguy hiểm, vất vả trong nhiệm vụ truy vết, lấy mẫu, xét nghiệm, điều trị, cách ly, ngăn chặn dịch bệnh lây lan, bùng phát.

                            "Chúng ta vô cùng xúc động khi các hình ảnh y bác sĩ chiến sĩ, tình nguyện viên sẵn sàng đi vào tâm dịch, đối mặt với các nguy cơ rủi ro, quên đi sức khỏe của bản thân để gác lại việc riêng của gia đình. Thậm chí có trường hợp có cha mẹ già yếu, con nhỏ xung phong đi vào điểm nóng của đại dịch. Trong tâm dịch đã ngày đêm miệt mài làm nhiệm vụ trong bộ đồ bảo hộ ướt đẫm mồ hôi, dốc hết sức lực, thậm chí kiệt sức. Đó là những điển hình của lương y như từ mẫu, hết lòng vì sức khỏe, tính mạng của nhân dân , vì nhân dân phục vụ, luôn đặt lợi ích quốc gia, dân tộc lên trên hết", Chủ tịch Quốc hội chia sẻ.

                            Chủ tịch Quốc hội trân trọng ghi nhận, cảm ơn và nhiệt liệt biểu dương những hy sinh, cống hiến vô cùng thầm lặng, nhưng vô cùng to lớn của toàn thể cán bộ, thầy thuốc, y bác sĩ, tình nguyện viên, người lao động ngành y tế.

                            “Điều này thể hiện trách nhiệm của các đồng chí với Tổ quốc, với nhân dân bởi đây cũng là nhiệm vụ rất khó khăn, rất nặng nề và nhiều rủi ro, thậm chí có thể ảnh hưởng đến cả sức khỏe và an toàn vì dịch bệnh không trừ một ai. Thực tế đã có những bác sĩ, nhân viên y tế, lực lượng tuyến đầu phơi nhiễm dịch Covid-19. Đây cũng là nhiệm vụ hết sức cao cả và vinh quang, tự hào, thể hiện nghĩa cử, sự chia sẻ, tinh thần trách nhiệm cao với TP.HCM và các tỉnh phía Nam", Chủ tịch Quốc hội động viên.
                        </p>
                  </div>
                <footer>Posted by howCode on 12/11/17
                    <button class="btn btn-default like" type="button" > <i class="glyphicon glyphicon-heart" data-aos="flip-right"></i><span> 5 Likes</span></button>
                    <button class="btn btn-default comment" type="button">
                    <i class="glyphicon glyphicon-flash"></i><span> 3 Comments</span></button>
                </footer>
            </blockquote>
        </li>

        </div>


    </ul>
</div>

    <!-- Adding New Post Section-->

    <div class="col-md-3">
        <button class="btn btn-default buttonProfile" type="button">Đăng Bài Viết Mới </button>
    </div>
</div>
</div> 
 
 
 
 
 
 
    