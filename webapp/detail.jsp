<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>게시물 상세</h1>
    <div>
    	<div>
            번호 : ${ article.idx }
        </div>
        <div>
            제목 : ${ article.title }
        </div>        
        <div>
            내용 : ${ article.body }
        </div>
        <div>
            작성자 : ${ article.nickname }
        </div>
        <div>
            작성일 : ${ article.regDate }
        </div>
    </div>

</body>
</html>