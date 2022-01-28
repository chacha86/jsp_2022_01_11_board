<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>회원 가입</h1>
    <form action="http://localhost:9000/member/add.do" method="POST">
        <div>
            아이디 : <input type="text" name="loginId"><br />
            비밀번호 : <input type="text" name="loginPw"><br />
            이름 : <input type="text" name="nickname"><br />
            <input type="submit" value="회원가입" />
        </div>
    </form>
</body>
</html>