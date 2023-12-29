<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.springbook.biz.user.UserVO" %>
<%@ page import="com.springbook.biz.userDetail.UserDetailVO" %>
<%
	UserVO user = (UserVO)session.getAttribute("u");
	UserDetailVO user2 = (UserDetailVO)session.getAttribute("u2");
	System.out.println(user2.getPhoneNumber1());
%>    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="EUC-KR">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Cute Login</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <!-- Custom styles for this template -->
    <style>
        body {
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            background-color: #f8f9fa;
        }

        .form-signin {
            width: 100%;
            max-width: 330px;
            padding: 15px;
            margin: auto;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }

        .form-signin .mb-4 {
            font-size: 2rem;
            color: #495057;
        }

        .form-signin img {
            width: 60px;
            height: 60px;
            margin-bottom: 20px;
        }

        .form-signin .form-floating input {
            height: auto;
            margin-bottom: 10px;
            border: none;
        }

        .form-signin .checkbox {
            font-weight: 400;
        }

        .form-signin .btn-primary {
            background-color: #6c757d;
            border-color: #6c757d;
        }

        .form-signin .btn-primary:hover {
            background-color: #5a6268;
            border-color: #545b62;
        }

        .mt-5 {
            margin-top: 3rem !important;
        }
    </style>
</head>
<body class="text-center">

<main class="form-signin">
    <form action="login.do" method="post">
        <img class="mb-4" src="https://cdn-icons-png.flaticon.com/512/608/608502.png" alt="" width="60" height="60">
        <h1 class="mb-4">상세 정보</h1>

        <div class="form-floating">
            <h3>아이디 = ${user.getId()}</h3>
        </div>
			
        <div class="form-floating">
          	<h3>비밀번호 = ${user.getPassword()}</h3>
        </div>
        
           <div class="form-floating">
          	<h3>이름 = ${user.getName()}</h3>
        </div>
        
           <div class="form-floating">
          	<h3>역할 = ${user.getRole()}</h3>
        </div>
        
           <div class="form-floating">
          	<h3>닉네임 = ${user.getNickName()}</h3>
        </div>
        
           <div class="form-floating">
          	<h3>번호1 = <%=user2.getPhoneNumber1() %></h3>
        </div>
        
           <div class="form-floating">
          	<h3>번호2 = <%=user2.getPhoneNumber2() %></h3>
        </div>
        
           <div class="form-floating">
          	<h3>주소 = <%=user2.getAddress() %></h3>
        </div>
        
         <div class="form-floating">
          	<h3>이메일 = <%=user2.getEmail() %></h3>
        </div>

      


      	<p class="mt-3">글 목록으로 →<a href="getBoardList.do">★</a></p>
        <p class="mt-5 mb-3 text-muted">&copy; 2023</p>
    </form>
</main>

</body>
</html>