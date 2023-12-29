<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.springbook.biz.board.BoardVO" %>
<%@ page import="com.springbook.biz.user.UserVO" %>
<%@ page import="com.springbook.biz.comment.CommentVO" %>
<%@ page import="java.util.List" %>
<%@ taglib uri = "http://java.sun.com/jstl/core_rt" prefix = "c" %>

<%BoardVO board = (BoardVO) session.getAttribute("board");
UserVO user = (UserVO)session.getAttribute("user");
List<CommentVO> commentList = (List)session.getAttribute("commentList");%>

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
    <div class="col-sm-4 text-left"> 
       <h1>글 상세</h1>
    <form action="updateBoard.do" method = "post">
     <table border="2" cellpadding="0" cellspacing="0" width="600" >

<tr>
<td bgcolor="WhiteSmoke">번호</td>
<td><input type = "hidden" name = "seq" value="${board.getSeq()}">${board.getSeq()}</td>
</tr>
<tr>
<td bgcolor="WhiteSmoke">제목</td>
<td><input type = "text" name = "title" value="${board.getTitle()}"></td>
</tr>
<tr>
<td bgcolor="WhiteSmoke">작성자</td>
<td>${board.getWriter()}</td>
</tr>
<tr>
<td bgcolor="WhiteSmoke">내용</td>
<td><textarea rows="10" cols="40" name = "content">${board.getContent()}</textarea></td>
</tr>
<tr>
<td bgcolor="WhiteSmoke">날짜</td>
<td>${board.getRegDate()}</td>
</tr>
<tr>
<td bgcolor="WhiteSmoke">조회수</td>
<td>${board.getCnt()}</td>
</tr>
<tr>
<td colspan="6" align="center"><input type = "submit" value="수정"></td>
</tr>
      
      </table>
      </form>
      <table>
      <tr>
      <td bgcolor="WhiteSmoke">추천수&nbsp;${board.getB_like()}</td>
<td>
<form action="b_likeBoard.do" method = "post">
<input type = "hidden" name="seq" value="${board.getSeq()}">
<input type = "submit" value="좋아요">
</form>
</td>

</tr>
</table>
<a href="getBoardList.do">글 목록</a>

<c:set var="var1" value="${user.getNickName()}" />
<c:set var="var2" value="${board.getWriter()}" />


 	<c:if test="${var1 eq var2}">
      <a href="deleteBoard.do?seq=${board.getSeq()}">글 삭제</a>
     </c:if>
     
      </div>
       <div class="col-sm-4 text-left"> 
       <h1>댓글</h1>
 <form action="insertComment.do" method = "post">
 <table border="2" cellpadding="0" cellspacing="0" width="600" >
 
 <tr>
 <th colspan="3" align="center" bgcolor="WhiteSmoke">댓글 등록</th>
 </tr>

 <tr>
 <td><input type = "hidden" name = "seq" value ="${board.getSeq()}"></td>
 <td><input type = "hidden" name = "co_nickName" value = "${user.getNickName()}">${user.getNickName()}</td>
 <td><textarea rows="5" cols="50" name = "co_content"></textarea></td>
 <td><input type = "submit" value="등록"></td>
 </tr>
 </table>
    </form>
  
   
   
   
   
   
   
  <form action="deleteComment.do" method = "post">
     <table border="2" cellpadding="0" cellspacing="0" width="600" >
 
 <tr>
 <th colspan="4" align="center" bgcolor="WhiteSmoke">댓글</th>
 </tr>
 
 <c:forEach items="${commentList }" var="comment">
 <tr>
 <td>${comment.getCo_seq()}</td>
 <td>${comment.getCo_nickName()}</td>
 <td>${comment.getCo_content()}</td>
 <td>${comment.getCo_regDate()}</td>
 
<c:set var="var1" value="${user.getNickName()}" />
<c:set var="var2" value="${comment.getCo_nickName()}" />
<c:if test="${var1 eq var2}">
<td>
<input type = "submit" value="삭제">
<input type = "hidden" name = "co_seq" value="${comment.getCo_seq()}">
<input type = "hidden" name = "seq" value="${board.getSeq()}">
</td>
</c:if>

 </tr>
 
 </c:forEach>
 </table>
  </form>
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