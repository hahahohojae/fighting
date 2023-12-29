<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.springbook.biz.board.BoardVO" %>    
<%@ page import="com.springbook.biz.user.UserVO" %>
<%@ page import="java.util.List" %>
<%@ taglib uri = "http://java.sun.com/jstl/core_rt" prefix = "c" %>
<%
List<BoardVO> myList = (List) session.getAttribute("myList");
UserVO user = (UserVO)session.getAttribute("user");
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>미니 프로젝트</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <style>
    /* Remove the navbar's default margin-bottom and rounded borders */ 
    .navbar {
      margin-bottom: 0;
      border-radius: 0;
    }
    
    /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
    .row.content {height: 450px}
    
    /* Set gray background color and 100% height */
    .sidenav {
      padding-top: 20px;
      background-color: #f1f1f1;
      height: 100%;
    }
    
    /* Set black background color, white text and some padding */
    footer {
      background-color: #555;
      color: white;
      padding: 15px;
    }
    
    /* On small screens, set height to 'auto' for sidenav and grid */
    @media screen and (max-width: 767px) {
      .sidenav {
        height: auto;
        padding: 15px;
      }
      .row.content {height:auto;} 
    }
  </style>
</head>
<body>



<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="getBoardList.do">castlejun</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="logout.do"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav>
  
<div class="container-fluid text-center">    
  <div class="row content">
    <div class="col-sm-2 sidenav">
      <p><a href="getMyList.do">나의 활동</a></p>
      <p><a href="infoUser.do">개인 정보</a></p>
    </div>
    <div class="col-sm-8 text-left"> 
    <h3>${user.getNickName()}님의 게시판</h3>
    <form action="searchBoard.do" method = "post">
     <table border="2" cellpadding="0" cellspacing="0" width="1250">
     <tr>
     <td colspan="6" align="right">
     <select name = "searchType">
     <option value="TITLE">제목</option>
     <option value="CONTENT">내용</option> 
     </select>
     <input type = "text" name = "searchKeyword">
     <input type = "submit" value="검색">
</td>
</tr>  
</table>
</form>   
     
     
     
  <table border="2" cellpadding="0" cellspacing="0" width="1250">   
<tr>
<th bgcolor="WhiteSmoke" width="150">번호</th>
<th bgcolor="WhiteSmoke" width="300">제목</th>
<th bgcolor="WhiteSmoke" width="250">작성자</th>
<th bgcolor="WhiteSmoke" width="250">날짜</th>
<th bgcolor="WhiteSmoke" width="200">조회수</th>
<th bgcolor="WhiteSmoke" width="200">추천수</th>
</tr>


<c:forEach items="${myList}" var="board">


<tr>
<td>${board.getSeq()}</td>
<td><a href = "cntBoard.do?seq=${board.getSeq()}"/>${board.getTitle()}</a></td>
<td>${board.getWriter()}</td>
<td>${board.getRegDate()}</td>
<td>${board.getCnt()}</td>
<td>${board.getB_like()}</td>
</tr>

</c:forEach>

    
      
      </table>
      <a href="insertBoard.jsp?user = ${user}"/>새글 등록</a>
    </div>
    <div class="col-sm-2 sidenav">
      <div class="well">
        <p>ADS</p>
      </div>
      <div class="well">
        <p>ADS</p>
      </div>
    </div>
  </div>
</div>

<footer class="container-fluid text-center">
  <p>Footer Text</p>
</footer>

</body>
</html>