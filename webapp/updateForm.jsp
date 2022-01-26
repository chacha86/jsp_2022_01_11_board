<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>게시물 수정</h1>
    <form action="http://localhost:9000/article/update" method="POST">
        <div>
            제목 : <input type="text" name="title" value="${ article.title }"><br />
            내용 : <input type="text" name="body" value="${ article.body }"><br />
            <input type="hidden" name="idx" value="${ article.idx }" /><br />
            <input type="submit" value="게시물 수정" />
        </div>
    </form>
</body>
</html>