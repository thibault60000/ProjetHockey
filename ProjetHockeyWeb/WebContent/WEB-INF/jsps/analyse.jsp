<%@page import="match.MatchHockey"%>
<%@ page import="java.util.*" %>
<%@ page import="statistique.Statistique" %>
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
    <!-- Custom Fonts -->
    <link rel="stylesheet" type="text/css" href="resources/bower_components/font-awesome/css/font-awesome.min.css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.css">
    
    <script type="text/javascript" src="resources/bower_components/metisMenu/dist/metisMenu.min.js"></script>
    <script type="text/javascript" src="resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="resources/bower_components/jquery/dist/jquery.min.js"></script>
	<script type="text/javascript" src="resources/bower_components/morrisjs/morris.min.js"></script>
	<script type="text/javascript" src="resources/bower_components/raphael/raphael-min.js"></script>
    <script type="text/javascript" src="resources/dist/js/sb-admin-2.js"></script>
    
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
            <div class="rows">
	            <%
	            	int taille = ((Collection<Statistique>)request.getAttribute("listStatistique")).size();
	            	Gardien gardien = (Gardien)request.getAttribute("monGardien");
	            	ArrayList<MatchHockey> list1 = new ArrayList(((Collection<MatchHockey>)request.getAttribute("listMatchHockeys")));
	            	ArrayList<Statistique> list2 = new ArrayList(((Collection<Statistique>)request.getAttribute("listStatistique")));
	            %>
				<h2><%=gardien.getNomGardien() %> - <%=gardien.getPrenomGardien() %></h2>	
            	<div class="col-lg-6">
	            	<div class="panel panel-default">
					    <div class="panel-heading">
					        <i class="fa fa-bar-chart-o fa-fw"></i>Nombre de tirs reçus au cours de la saison
					    </div>
					    <div class="panel-body">
					        <div id="morris-all-nb-arret"></div>
					    </div>
				   	</div>
				</div>
				<div class="col-lg-6">
					<div class="panel panel-default">
						<div class="panel-heading">
					        <i class="fa fa-bar-chart-o fa-fw"></i>Nombre d'arret au cours de le saison
					    </div>
					    <div class="panel-body">
					        <div id="morris-all-nb-but"></div>
					    </div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="panel panel-default">
						<div class="panel-heading">
					        <i class="fa fa-bar-chart-o fa-fw"></i>Nombre de tirs reçus par zone d'arrêt
					    </div>
					    <div class="panel-body">
					        <div id="morris-lancer-par-zone-arret"></div>
					    </div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="panel panel-default">
						<div class="panel-heading">
					        <i class="fa fa-bar-chart-o fa-fw"></i>Nombre d'arrêts par zone d'arrêt
					    </div>
					    <div class="panel-body">
					        <div id="morris-arret-par-zone-arret"></div>
					    </div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="panel panel-default">
						<div class="panel-heading">
					        <i class="fa fa-bar-chart-o fa-fw"></i>Nombre de tirs reçus par zone de tir
					    </div>
					    <div class="panel-body">
					        <div id="morris-lancer-par-zone-de-tir"></div>
					    </div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="panel panel-default">
						<div class="panel-heading">
					        <i class="fa fa-bar-chart-o fa-fw"></i>Nombre d'arrêts par zone de tir
					    </div>
					    <div class="panel-body">
					        <div id="morris-arret-par-zone-de-tir"></div>
					    </div>
					</div>
				</div>
				
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
					        <i class="fa fa-bar-chart-o fa-fw"></i>Nombre d'arrets au cours de le saison par zone d'arrêt
					    </div>
					    <div class="panel-body">
					        <div class="panel-body">
						        <div id="morris-evolution-arret-zone-arret"></div>
						    </div>
					    </div>
					</div>
				</div>
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
					        <i class="fa fa-bar-chart-o fa-fw"></i>Nombre de lancers au cours de la saison par zone d'arrêt
					    </div>
					    <div class="panel-body">
					        <div class="panel-body">
						        <div id="morris-evolution-lancer-zone-arret"></div>
						    </div>
					    </div>
					</div>
				</div>
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
					        <i class="fa fa-bar-chart-o fa-fw"></i>Nombre d'arrets au cours de le saison par zone de tirs
					    </div>
					    <div class="panel-body">
					        <div class="panel-body">
						        <div id="morris-evolution-arret-zone-de-tir"></div>
						    </div>
					    </div>
					</div>
				</div>
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
					        <i class="fa fa-bar-chart-o fa-fw"></i>Nombre de lancers au cours de le saison par zone
					    </div>
					    <div class="panel-body">
					        <div class="panel-body">
						        <div id="morris-evolution-lancer-zone-de-tir"></div>
						    </div>
					    </div>
					</div>
				</div>
            </div>
        </div>
        <!-- /#page-wrapper -->
    </div>
    <!-- /#wrapper -->
  
	<script type="text/javascript">
	//Données pour le nombre total de tir reçus au cours de la saison
		new Morris.Area({
			element: 'morris-all-nb-arret',
			data:[
				<%
					for(int i=0;i<taille;i++){
						int nbarret = list2.get(i).getLancerZoneDeTir1() + list2.get(i).getLancerZoneDeTir2() + list2.get(i).getLancerZoneDeTir3() + list2.get(i).getLancerZoneDeTir4() + list2.get(i).getLancerZoneDeTir5() + list2.get(i).getLancerZoneDeTir6();
				%>
				{Date : <%="'"+list1.get(i).getDateDuMatchHockey()+"'"%>, Arrêt :<%="'"+Integer.toString(nbarret)+"'"%> },
				<%
					}
				%>
				],
				xkey: 'Date',
				ykeys: ['Arrêt'],
				labels: ["Nombre d'arrêts"],
				lineColors:['#F0AD4E']
			});

		//Données pour le nombre de buts pris au cours de la saison 
		new Morris.Area({
			element: 'morris-all-nb-but',
			data:[
				<%
					for(int i=0;i<taille;i++){
						int nbbut =(list2.get(i).getLancerZoneDeTir1()+list2.get(i).getLancerZoneDeTir2()+list2.get(i).getLancerZoneDeTir3()+list2.get(i).getLancerZoneDeTir4()+list2.get(i).getLancerZoneDeTir5()+list2.get(i).getLancerZoneDeTir6())-(list2.get(i).getArretZoneDeTir1() + list2.get(i).getArretZoneDeTir2() + list2.get(i).getArretZoneDeTir3() + list2.get(i).getArretZoneDeTir4() + list2.get(i).getArretZoneDeTir5() + list2.get(i).getArretZoneDeTir6());
				%>
				{Date : <%="'"+list1.get(i).getDateDuMatchHockey()+"'" %>, But :<%="'"+Integer.toString(nbbut)+"'"%> },
				<%
					}
				%>
				],
				xkey: 'Date',
				ykeys: ['But'],
				labels: ["Nombre de buts encaissés"],
				lineColors:['#F0AD4E']
			});

		//Données pour le donut tir reçus/zone arret au cours de la saison
		<%
    	int TirZoneArretsommeZoneA = 0;
		int TirZoneArretsommeZoneB = 0;
		int TirZoneArretsommeZoneC = 0;
		int TirZoneArretsommeZoneD = 0;
		int TirZoneArretsommeZoneE = 0;
		int TirZoneArretsommeZoneF = 0;
		int TirZoneArretsommeZoneG = 0;
		int TirZoneArretsommeZoneH = 0;
		int TirZoneArretsommeZoneI = 0;
		
	    	for(int i=0;i<taille;i++){
	    		TirZoneArretsommeZoneA = list2.get(i).getLancerZoneArret1() + TirZoneArretsommeZoneA;
	    		TirZoneArretsommeZoneB = list2.get(i).getLancerZoneArret2() + TirZoneArretsommeZoneB;
	    		TirZoneArretsommeZoneC = list2.get(i).getLancerZoneArret3() + TirZoneArretsommeZoneC;
	    		TirZoneArretsommeZoneD = list2.get(i).getLancerZoneArret4() + TirZoneArretsommeZoneD;
	    		TirZoneArretsommeZoneE = list2.get(i).getLancerZoneArret5() + TirZoneArretsommeZoneE;
	    		TirZoneArretsommeZoneF = list2.get(i).getLancerZoneArret6() + TirZoneArretsommeZoneF;
	    		TirZoneArretsommeZoneG = list2.get(i).getLancerZoneArret7() + TirZoneArretsommeZoneG;
	    		TirZoneArretsommeZoneH = list2.get(i).getLancerZoneArret8() + TirZoneArretsommeZoneH;
	    		TirZoneArretsommeZoneI = list2.get(i).getLancerZoneArret9() + TirZoneArretsommeZoneI;
	    	}
	    %>
		
		Morris.Donut({
	        element: 'morris-lancer-par-zone-arret',
	        data: [{
	            label: "Zone A",
	            value: <%=TirZoneArretsommeZoneA %>
	        }, {
	            label: "Zone B",
	            value: <%=TirZoneArretsommeZoneB %>
  	        }, {
	            label: "Zone C",
	            value: <%=TirZoneArretsommeZoneC %>
  	        },{
	            label: "Zone D",
	            value: <%=TirZoneArretsommeZoneD %>
  	        },{
	            label: "Zone E",
	            value: <%=TirZoneArretsommeZoneE %>
  	        },{
	            label: "Zone F",
	            value: <%=TirZoneArretsommeZoneF %>	        
	        },{
	            label: "Zone G",
	            value: <%=TirZoneArretsommeZoneG %>	        
	        },{
	            label: "Zone H",
	            value: <%=TirZoneArretsommeZoneH %>	        
	        },{
	            label: "Zone I",
	            value: <%=TirZoneArretsommeZoneI %>	        
	        }],
	        resize: true,
            colors:['#ca7d11','#dd8812','#eb9318','#ec9c2b','#eea43d','#f0ad4f','#f1b562','#f3be74','#f4c787','#f6cf99']
	    });

		//Données pour le donut arret/zone arret au cours de la saison
		<%
    	int ArretZoneArretsommeZoneA = 0;
		int ArretZoneArretsommeZoneB = 0;
		int ArretZoneArretsommeZoneC = 0;
		int ArretZoneArretsommeZoneD = 0;
		int ArretZoneArretsommeZoneE = 0;
		int ArretZoneArretsommeZoneF = 0;
		int ArretZoneArretsommeZoneG = 0;
		int ArretZoneArretsommeZoneH = 0;
		int ArretZoneArretsommeZoneI = 0;
		
	    	for(int i=0;i<taille;i++){
	    		ArretZoneArretsommeZoneA = list2.get(i).getArretZoneArret1() + ArretZoneArretsommeZoneA;
	    		ArretZoneArretsommeZoneB = list2.get(i).getArretZoneArret2() + ArretZoneArretsommeZoneB;
	    		ArretZoneArretsommeZoneC = list2.get(i).getArretZoneArret3() + ArretZoneArretsommeZoneC;
	    		ArretZoneArretsommeZoneD = list2.get(i).getArretZoneArret4() + ArretZoneArretsommeZoneD;
	    		ArretZoneArretsommeZoneE = list2.get(i).getArretZoneArret5() + ArretZoneArretsommeZoneE;
	    		ArretZoneArretsommeZoneF = list2.get(i).getArretZoneArret6() + ArretZoneArretsommeZoneF;
	    		ArretZoneArretsommeZoneG = list2.get(i).getArretZoneArret7() + ArretZoneArretsommeZoneG;
	    		ArretZoneArretsommeZoneH = list2.get(i).getArretZoneArret8() + ArretZoneArretsommeZoneH;
	    		ArretZoneArretsommeZoneI = list2.get(i).getArretZoneArret9() + ArretZoneArretsommeZoneI;
	    	}
	    %>
		
		Morris.Donut({
	        element: 'morris-arret-par-zone-arret',
	        data: [{
	            label: "Zone A",
	            value: <%=ArretZoneArretsommeZoneA %>
	        }, {
	            label: "Zone B",
	            value: <%=ArretZoneArretsommeZoneB %>
  	        }, {
	            label: "Zone C",
	            value: <%=ArretZoneArretsommeZoneC %>
  	        },{
	            label: "Zone D",
	            value: <%=ArretZoneArretsommeZoneD %>
  	        },{
	            label: "Zone E",
	            value: <%=ArretZoneArretsommeZoneE %>
  	        },{
	            label: "Zone F",
	            value: <%=ArretZoneArretsommeZoneF %>	        
	        },{
	            label: "Zone G",
	            value: <%=ArretZoneArretsommeZoneG %>	        
	        },{
	            label: "Zone H",
	            value: <%=ArretZoneArretsommeZoneH %>	        
	        },{
	            label: "Zone I",
	            value: <%=ArretZoneArretsommeZoneI %>	        
	        }],
	        resize: true,
            colors:['#ca7d11','#dd8812','#eb9318','#ec9c2b','#eea43d','#f0ad4f','#f1b562','#f3be74','#f4c787','#f6cf99']
	    });

		//données pour le donut tir reçcu/zone de tir au cours de la saison
		<%
    	int TirZoneTirsommeZoneA = 0;
		int TirZoneTirsommeZoneB = 0;
		int TirZoneTirsommeZoneC = 0;
		int TirZoneTirsommeZoneD = 0;
		int TirZoneTirsommeZoneE = 0;
		int TirZoneTirsommeZoneF = 0;
		
	    	for(int i=0;i<taille;i++){
	    		TirZoneTirsommeZoneA = list2.get(i).getLancerZoneDeTir1() + TirZoneTirsommeZoneA;
	    		TirZoneTirsommeZoneB = list2.get(i).getLancerZoneDeTir2() + TirZoneTirsommeZoneB;
	    		TirZoneTirsommeZoneC = list2.get(i).getLancerZoneDeTir3() + TirZoneTirsommeZoneC;
	    		TirZoneTirsommeZoneD = list2.get(i).getLancerZoneDeTir4() + TirZoneTirsommeZoneD;
	    		TirZoneTirsommeZoneE = list2.get(i).getLancerZoneDeTir5() + TirZoneTirsommeZoneE;
	    		TirZoneTirsommeZoneF = list2.get(i).getLancerZoneDeTir6() + TirZoneTirsommeZoneF;
	    	}
	    %>
		
		Morris.Donut({
	        element: 'morris-lancer-par-zone-de-tir',
	        data: [{
	            label: "Zone 1",
	            value: <%=TirZoneTirsommeZoneA %>
	        }, {
	            label: "Zone 2",
	            value: <%=TirZoneTirsommeZoneB %>
  	        }, {
	            label: "Zone 3",
	            value: <%=TirZoneTirsommeZoneC %>
  	        },{
	            label: "Zone 4",
	            value: <%=TirZoneTirsommeZoneD %>
  	        },{
	            label: "Zone 5",
	            value: <%=TirZoneTirsommeZoneE %>
  	        },{
	            label: "Zone 6",
	            value: <%=TirZoneTirsommeZoneF %>	        
	        }],
	        resize: true,
            colors:['#ca7d11','#dd8812','#eb9318','#ec9c2b','#eea43d','#f0ad4f','#f1b562','#f3be74','#f4c787','#f6cf99']
	    });

		//données pour le donut arret/zone de tir au cours de la saison
		<%
    	int ArretZoneDeTirsommeZoneA = 0;
		int ArretZoneDeTirsommeZoneB = 0;
		int ArretZoneDeTirsommeZoneC = 0;
		int ArretZoneDeTirsommeZoneD = 0;
		int ArretZoneDeTirsommeZoneE = 0;
		int ArretZoneDeTirsommeZoneF = 0;
		
	    	for(int i=0;i<taille;i++){
	    		ArretZoneDeTirsommeZoneA = list2.get(i).getArretZoneDeTir1() + ArretZoneDeTirsommeZoneA;
	    		ArretZoneDeTirsommeZoneB = list2.get(i).getArretZoneDeTir2() + ArretZoneDeTirsommeZoneB;
	    		ArretZoneDeTirsommeZoneC = list2.get(i).getArretZoneDeTir3() + ArretZoneDeTirsommeZoneC;
	    		ArretZoneDeTirsommeZoneD = list2.get(i).getArretZoneDeTir4() + ArretZoneDeTirsommeZoneD;
	    		ArretZoneDeTirsommeZoneE = list2.get(i).getArretZoneDeTir5() + ArretZoneDeTirsommeZoneE;
	    		ArretZoneDeTirsommeZoneF = list2.get(i).getArretZoneDeTir6() + ArretZoneDeTirsommeZoneF;
	    	}
	    %>
		
		Morris.Donut({
	        element: 'morris-arret-par-zone-de-tir',
	        data: [{
	            label: "Zone 1",
	            value: <%=ArretZoneDeTirsommeZoneA %>
	        }, {
	            label: "Zone 2",
	            value: <%=ArretZoneDeTirsommeZoneB %>
  	        }, {
	            label: "Zone 3",
	            value: <%=ArretZoneDeTirsommeZoneC %>
  	        },{
	            label: "Zone 4",
	            value: <%=ArretZoneDeTirsommeZoneD %>
  	        },{
	            label: "Zone 5",
	            value: <%=ArretZoneDeTirsommeZoneE %>
  	        },{
	            label: "Zone 6",
	            value: <%=ArretZoneDeTirsommeZoneF %>	        
	        }],
	        resize: true,
	        colors:['#ca7d11','#dd8812','#eb9318','#ec9c2b','#eea43d','#f0ad4f','#f1b562','#f3be74','#f4c787','#f6cf99']
	    });

		Morris.Line({
			element: 'morris-evolution-lancer-zone-arret',
			data: [
				<%for(int i=0;i<taille;i++){%>
				{Date : <%="'"+list1.get(i).getDateDuMatchHockey()+"'"%>, a:<%=list2.get(i).getLancerZoneArret1()%> ,b:<%=list2.get(i).getLancerZoneArret2()%> ,c:<%=list2.get(i).getLancerZoneArret3()%> ,d:<%=list2.get(i).getLancerZoneArret4()%> ,e:<%=list2.get(i).getLancerZoneArret5()%> ,f:<%=list2.get(i).getLancerZoneArret6()%> ,g:<%=list2.get(i).getLancerZoneArret7()%> ,h:<%=list2.get(i).getLancerZoneArret8()%> ,i:<%=list2.get(i).getLancerZoneArret9()%>},
				<%}%>
			],
			xkey: 'Date',
			ykeys: ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'],
			labels: ['Zone A', 'Zone B', 'Zone C', 'Zone D', 'Zone E', 'Zone F', 'Zone G', 'Zone H', 'Zone I']
		});

		Morris.Line({
			element: 'morris-evolution-lancer-zone-de-tir',
			data: [
				<%for(int i=0;i<taille;i++){%>
				{Date : <%="'"+list1.get(i).getDateDuMatchHockey()+"'"%>, a:<%=list2.get(i).getLancerZoneDeTir1()%> ,b:<%=list2.get(i).getLancerZoneDeTir2()%> ,c:<%=list2.get(i).getLancerZoneDeTir3()%> ,d:<%=list2.get(i).getLancerZoneDeTir4()%> ,e:<%=list2.get(i).getLancerZoneDeTir5()%> ,f:<%=list2.get(i).getLancerZoneDeTir6()%>},
				<%}%>
			],
			xkey: 'Date',
			ykeys: ['a', 'b', 'c', 'd', 'e', 'f'],
			labels: ['Zone 1', 'Zone 2', 'Zone 3', 'Zone 4', 'Zone 5', 'Zone 6']
		});
		
		Morris.Line({
			element: 'morris-evolution-arret-zone-arret',
			data: [
				<%for(int i=0;i<taille;i++){%>
				{Date : <%="'"+list1.get(i).getDateDuMatchHockey()+"'"%>, a:<%=list2.get(i).getArretZoneArret1()%> ,b:<%=list2.get(i).getArretZoneArret2()%> ,c:<%=list2.get(i).getArretZoneArret3()%> ,d:<%=list2.get(i).getArretZoneArret4()%> ,e:<%=list2.get(i).getArretZoneArret5()%> ,f:<%=list2.get(i).getArretZoneArret6()%> ,g:<%=list2.get(i).getArretZoneArret7()%> ,h:<%=list2.get(i).getArretZoneArret8()%> ,i:<%=list2.get(i).getArretZoneArret9()%>},
				<%}%>
			],
			xkey: 'Date',
			ykeys: ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'],
			labels: ['Zone A', 'Zone B', 'Zone C', 'Zone D', 'Zone E', 'Zone F', 'Zone G', 'Zone H', 'Zone I']
		});

		Morris.Line({
			element: 'morris-evolution-arret-zone-de-tir',
			data: [
				<%for(int i=0;i<taille;i++){%>
				{Date : <%="'"+list1.get(i).getDateDuMatchHockey()+"'"%>, a:<%=list2.get(i).getArretZoneDeTir1()%> ,b:<%=list2.get(i).getArretZoneDeTir2()%> ,c:<%=list2.get(i).getArretZoneDeTir3()%> ,d:<%=list2.get(i).getArretZoneDeTir4()%> ,e:<%=list2.get(i).getArretZoneDeTir5()%> ,f:<%=list2.get(i).getArretZoneDeTir6()%>},
				<%}%>
			],
			xkey: 'Date',
			ykeys: ['a', 'b', 'c', 'd', 'e', 'f'],
			labels: ['Zone 1', 'Zone 2', 'Zone 3', 'Zone 4', 'Zone 5', 'Zone 6']
		});
		
		</script>
</body>

</html>
