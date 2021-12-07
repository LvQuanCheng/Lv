<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2021/11/26
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; charset=utf-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>

<form action="${pageContext.request.contextPath}/param1/upload2.do" enctype="multipart/form-data" method="post">
    上传图片1:<input type="file" name="images"><br>
    上传图片2:<input type="file" name="images"><br>
    上传图片3:<input type="file" name="images"><br>
            <input type="submit" value="上传">
</form>

</div>
<h2>点击图片完成下载</h2>
<div>
    <a href="${pageContext.request.contextPath}/user/load.do/blossom1.jpg">
        <img src="${pageContext.request.contextPath}/images/blossom1.jpg" width="300px">
    </a>
    <a href="${pageContext.request.contextPath}/user/load.do/blossom2.jpg">
        <img src="${pageContext.request.contextPath}/images/blossom2.jpg" width="300px">
    </a>
    <a href="${pageContext.request.contextPath}/user/load.do/菊花.jpg">
        <img src="${pageContext.request.contextPath}/images/菊花.jpg" width="300px">
    </a>
    <a href="${pageContext.request.contextPath}/user/load.do/Desert.jpg">
        <img src="${pageContext.request.contextPath}/images/Desert.jpg" width="300px">
    </a>
</div>

</body>
</html>
