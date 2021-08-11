<%-- 
    Document   : siginup
    Created on : Aug 10, 2021, 8:47:54 PM
    Author     : Windows 10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <div class="login-clean">
        <form method="post">
            <h2 class="sr-only">Create Account</h2>
            <div class="illustration"><i class="icon ion-ios-navigate"></i></div>
            <div class="form-group">
                <input class="form-control" id="name" type="text" name="name" placeholder="Họ Và Tên">
            </div>
            <div class="form-group">
                <input class="form-control" id="email" type="email" name="email" placeholder="Email">
            </div>
            <div class="form-group">
                <input class="form-control" id="username" type="text" name="username" placeholder="Username">
            </div>
           
            <div class="form-group">
                <input class="form-control" id="password" type="password" name="password" placeholder="Password">
            </div>
            <div class="form-group">
                <input class="form-control" id="confirmPassword" type="password" name="confirmPassword" placeholder="ConfirmPassword">
            </div>
            <div class="form-group">
                <button class="btn btn-primary btn-block" id="ca" type="button" data-bs-hover-animate="shake">Sign Up</button>
            </div><a href="#" class="forgot">Already got an account? Click here!</a>
        </form>
    </div>
