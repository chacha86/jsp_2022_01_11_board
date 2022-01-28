<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>로그인</h1>
    <div>
        <form action="/member/login.do" method="post">       
            아이디 : <input type="text" name="loginId" /><br/>
            비밀번호 : <input type="password" name="loginPw" /><br/>
            <input type="submit" value="로그인">
        </form>
    </div>    
</body>
</html>