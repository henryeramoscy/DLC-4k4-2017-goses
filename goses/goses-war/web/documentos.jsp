<%-- 
    Document   : documentos
    Created on : May 9, 2017, 3:32:54 PM
    Author     : pixelhar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>Busqueda</title>

        <!-- Bootstrap -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
            <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
          <![endif]-->
        <style>
            .bg-4 {
                background-color: #2f2f2f;
                color: #ffffff;
            }

            html {
                background: url("imagen/safari.jpg") no-repeat center fixed;
                -webkit-background-size: cover;
                -moz-background-size: cover;
                -o-background-size: cover;
                background-size: cover;
            }

            .block {
                width: 500px;
                border: 5px solid #fff;
                background: rgba(255, 255, 255, 0.7);
                text-align: center;
                margin: 100px auto 0;
            }
            .pull-right {
                float: right !important;
            }
        </style>
        <link href="css/dataTables.bootstrap.min.css" rel="stylesheet" type="text/css"/>

    </head>

    <body>
        <!--<a name="arriba"></a>-->
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">Goses
                    </a>
                </div>
                <ul class="nav navbar-nav navbar-left">
                    <li><a href="http://localhost:8080/goses-war/">Home</a></li>
                </ul>
                <form class="navbar-form navbar-left" role="search" action="Searche" method="post">
                    <div class="input-group">
                        <input type="text" value="${search}" name="gosesearch" class="form-control" placeholder="Search">
                        <div class="input-group-btn">
                            <button class="btn btn-default" type="submit">
                                <i class="glyphicon glyphicon-search"></i>
                            </button>
                        </div>
                    </div>
                </form>


                <form class="navbar-form navbar-left" role="search" action="UploadServlet" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <input type="file" name="file" class="form-control-file btn btn-default" id="exampleInputFile" aria-describedby="fileHelp">
                    </div>
                    <button type="submit" class="btn btn-default">Upload</button>
                </form>


            </div>
        </nav>
        <div class="container-fluid">
            <h2 class="sub-header">Ranking de Documentos Buscados</h2>
            <div class="table-responsive">
                <table id="docs" class="table table-striped table-bordered" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th>#NroDoc</th>
                            <th>NroRank</th>
                            <th>Nombre</th>
                            <th>Url</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${documentos}" var="doc">
                            <tr>
                                <td>${doc.numero}</td>
                                <td>${doc.rank}</td>
                                <td>${doc.nombre}</td>
                                <td><a href="<c:url value="/Download?url=${doc.nombre} "/>">${doc.url}</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>   
           <!-- <div class="pull-right"><a href="#arriba" class="btn btn-default" role="button">Arriba</a></div>  -->
        </div>
        <footer class="container-fluid bg-4 text-center">
            <p>Copyright © goses.com - 2017 - <a href="http://www.pixelhar.com">www.goses.com</a></p>
        </footer>
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>-->
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery-1.12.4.js" type="text/javascript"></script>
        <script src="js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="js/dataTables.bootstrap.min.js" type="text/javascript"></script>
        <script>
            $(document).ready(function () {
                $('#docs').dataTable({
                    "bSort": false
                });
            });
        </script>
    </body>

</html>