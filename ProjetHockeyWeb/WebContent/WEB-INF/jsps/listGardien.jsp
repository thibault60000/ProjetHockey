<%@ page import="java.util.*" %>
<%@ page import="gardien.Gardien" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>J2EE - Projet hockey 2016</title>
    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" type="text/css" href="resources/bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- MetisMenu CSS -->
    <link rel="stylesheet" type="text/css" href="resources/bower_components/metisMenu/dist/metisMenu.min.css">
    <!-- Timeline CSS -->
    <link rel="stylesheet" type="text/css" href="resources/dist/css/timeline.css">
    <!-- Custom CSS -->
    <link rel="stylesheet" type="text/css" href="resources/dist/css/sb-admin-2.css">
    <!-- Morris Charts CSS -->
    <link rel="stylesheet" type="text/css" href="resources/bower_components/morrisjs/morris.css">
    <!-- Custom Fonts -->
    <link rel="stylesheet" type="text/css" href="resources/bower_components/font-awesome/css/font-awesome.min.css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
	
	<!-- jQuery -->
    <link rel="stylesheet" type="text/css" href="resources/bower_components/jquery/dist/jquery.min.js">

    <!-- Bootstrap Core JavaScript -->
    <link rel="stylesheet" type="text/css" href="resources/bower_components/bootstrap/dist/js/bootstrap.min.js">

    <!-- Metis Menu Plugin JavaScript -->
    <link rel="stylesheet" type="text/css" href="resources/bower_components/metisMenu/dist/metisMenu.min.js">

    <!-- Morris Charts JavaScript -->
    <link rel="stylesheet" type="text/css" href="resources/bower_components/raphael/raphael-min.js">
    <link rel="stylesheet" type="text/css" href="resources/bower_components/morrisjs/morris.min.js">

    <!-- Custom Theme JavaScript -->
    <link rel="stylesheet" type="text/css" href="resources/dist/js/sb-admin-2.js">
	
</head>

<body>

    <div id="wrapper">

        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
         	<div class="navbar-header">
          	<a class="navbar-brand brand-warning" href="Accueil">Dragon's Goalies Analysis</a>
          </div>
            <!-- /.navbar-header -->
            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li>
                            <a href="listGardienServlet" class="dragon-lien"><i class="fa fa-bar-chart-o fa-fw"></i>Statistiques</a>
                        </li>
                        <li>
                            <a href="#"  class="dragon-lien"><i class="fa fa-user fa-fw"></i>Ajouter un gardien</a>
                        </li>
                        <li>
                            <a href="newSaisie"  class="dragon-lien"><i class="fa fa-edit fa-fw"></i>Nouvelle saisie</a>
                        </li>
                        <li>    
                            <a href="deconnexion"  class="dragon-lien"><i class="fa fa-sign-out fa-fw"></i>Se déconnecter</a>
                        </li>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>

        <div class="page-wrapper-home" id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
					<table class="table table-striped">
		                <%
		                	Iterator it = ((Collection)request.getAttribute("listGardiens")).iterator();
		                				while(it.hasNext()) {
		                				Gardien gardien = (Gardien)it.next();
		                %>		
						<tr>
							<td><%= gardien.getNomGardien() %></td>
							<td><%= gardien.getPrenomGardien() %></td>
							<td>
								<form method="post" action="StatistiqueGardienServlet">
		                        	<input type="hidden" name="idGardien" id="idGardien" value=<%="'"+gardien.getIdGardien()+"'"%>/>
		                			<button type="submit" class="btn btn-warning form-control">en savoir plus</button>
		                        </form>
							</td>
						</tr>
				<%
				}
				%>
             </table>
           </div>   
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

</body>

</html>
