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

</head>

<body>
    <div id="wrapper">

        <!-- Navigation -->
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
                    <div class="panel panel-yellow">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-bar-chart-o fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">Les gardiens</div>
                                    <div>Etudier les statistiques d'un gardien</div>
                                </div>
                            </div>
                        </div>
                       	 <div class="panel-footer">
                            <form class="form-inline" method="post" action="InsertMatch">
            			<div class="form-group">
            				<label class="sr-only" for="annee">Quelle année ?</label>
            				<select class="form-control" id="annee" name="annee">
            					<option value="none">Choisir une année</option>
								<option value="2015">2015</option>
								<option value="2016">2016</option>
								<option value="2017">2017</option>
								<option value="2018">2018</option>
								<option value="2019">2019</option>
								<option value="2020">2020</option>
								<option value="2021">2021</option>
								<option value="2022">2022</option>
								<option value="2023">2023</option>
								<option value="2024">2024</option>
								<option value="2025">2025</option>
								<option value="2026">2026</option>
								<option value="2027">2027</option>
								<option value="2028">2028</option>
								<option value="2029">2029</option>
								<option value="2030">2030</option>
								<option value="2031">2031</option>
								<option value="2032">2032</option>
								<option value="2033">2033</option>
								<option value="2034">2034</option>
							</select>
            			</div>
            			<div class="form-group">
            				<label class="sr-only" for="mois">Quel mois ?</label>
            				<select class="form-control" name="mois" id="mois">
            					<option value="none">Choisir un mois</option>
								<option value="01">1</option>
								<option value="02">2</option>
								<option value="03">3</option>
								<option value="04">4</option>
								<option value="05">5</option>
								<option value="06">6</option>
								<option value="07">7</option>
								<option value="08">8</option>
								<option value="09">9</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>

							</select>
            			</div>
            			<div class="form-group">
            				<label class="sr-only" for="jour">Quel jour ? ?</label>
            				<select class="form-control" name="jour" id="jour">
            					<option>Choisir un jour</option>
								<option value="01">1</option>
								<option value="02">2</option>
								<option value="03">3</option>
								<option value="04">4</option>
								<option value="05">5</option>
								<option value="06">6</option>
								<option value="07">7</option>
								<option value="08">8</option>
								<option value="09">9</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>
								<option value="13">13</option>
								<option value="14">14</option>
								<option value="15">15</option>
								<option value="16">16</option>
								<option value="17">17</option>
								<option value="18">18</option>
								<option value="19">19</option>
								<option value="20">20</option>
								<option value="21">21</option>
								<option value="22">22</option>
								<option value="23">23</option>
								<option value="24">24</option>
								<option value="25">25</option>
								<option value="26">26</option>
								<option value="27">27</option>
								<option value="28">28</option>
								<option value="29">29</option>
								<option value="30">30</option>
								<option value="31">31</option>
							</select>
            			</div>
            			<button type="submit" class="btn btn-warning">Commencer la saisie</button>
            		</form>
                   </div>        	
            	</div>
            </div>                
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <link rel="stylesheet" type="text/css" href="resources/bower_components/jquery/dist/jquery.min.js">

    <!-- Bootstrap Core JavaScript -->
    <link rel="stylesheet" type="text/css" href="resources/bower_components/bootstrap/dist/js/bootstrap.min.js">

    <!-- Metis Menu Plugin JavaScript -->
    <link rel="stylesheet" type="text/css" href="resources/bower_components/metisMenu/dist/metisMenu.min.js">

    <!-- Morris Charts JavaScript -->
    <link rel="stylesheet" type="text/css" href="resources/bower_components/raphael/raphael-min.js">
    <link rel="stylesheet" type="text/css" href="resources/bower_components/morrisjs/morris.min.js">>

    <!-- Custom Theme JavaScript -->
    <link rel="stylesheet" type="text/css" href="resources/dist/js/sb-admin-2.js">

</body>

</html>
