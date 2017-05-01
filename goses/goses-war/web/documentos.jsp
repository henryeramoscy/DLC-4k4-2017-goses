<%-- 
    Document   : documentos
    Created on : Apr 24, 2017, 3:14:36 PM
    Author     : pixelhar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RankDocumentos-Busqueda</title>
        <link rel="stylesheet" href="css/index.css">
    </head>
    <body>
        <div class="container">

<header>
    <img src="imagen/logo__large.png" alt="Goses" style="width:159px;height:40px;">
   <form action="Searche" method="post">   
  Search Goses:
  <input type="search" name="gosesearch" width="500" height="50">
  <input type="submit" value="Go">
   </form>
<form action="UploadServlet" method="post" enctype="multipart/form-data">
<input type="file" name="file" size="50" />
<input type="submit" value="Upload File" />
</form>
</header>
  
<nav>
  <ul>
    <li><a href="#">Go</a></li>
    <li><a href="#">File</a></li>
    <li><a href="#">searches</a></li>
    <li><a href="#">contact</a></li>
  </ul>
</nav>

<article>
  <table>
  <tr>
    <th>Documento</th>
    <th>Url</th>
  </tr>
  <c:forEach items="${documentos}" var="doc">
                                <tr>
                                    <td>${doc.nombre}</td>
                                    <td><a href="<c:url value="/Download?url=${doc.nombre}"/>">${doc.url}</a></td>
                                </tr>
  </c:forEach>
<!--  <tr>
    <td>gn06v10.txt</td>
    <td><a href="#">http://localhost:8080/file\cache\gn06v10.txt</a></td>
  </tr>
-->
</table>
    <div style="width:800px; margin:0 auto;">
        <div class="pagination" style="width:800px; margin:0 auto;">
            <a href="#">&laquo;</a>
            <a href="#">1</a>
            <a href="#">2</a>
            <a href="#">3</a>
            <a href="#">4</a>
            <a href="#">5</a>
                <a href="#">6</a>
            <a href="#">&raquo;</a>
        </div>    
    </div>
</article>

<footer>Copyright &copy; goses.com</footer>
    
</div>

    <audio controls autoplay>
  <source src="sound/aero.mp3" type="audio/mpeg">
</audio>
    </body>
</html>
