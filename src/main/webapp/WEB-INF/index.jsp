<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	<title>SnakeRPG</title>
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
	<link type="text/css" rel="stylesheet" href="style.css" />
</head>
<body class="grey lighten-4">
<a href="<c:url value = "usermanagement"/>">Gestion des utilisateurs</a>
    <nav>
        <div class="nav-wrapper">
            <a href="<c:url value = "index"/>" class="brand-logo center">Snake RPG</a>
            <a href="#" data-activates="mobile-demo" class="button-collapse"><i class="material-icons">menu</i></a>
            <ul class="right hide-on-med-and-down">
            	<c:choose>
				    <c:when test="${ empty sessionScope.user }">
				    	<li><a href="<c:url value = "login"/>">Connexion</a></li>
				    	<li><a href="<c:url value = "register"/>">Inscription</a></li>
				    </c:when>
				    <c:otherwise>
	                    <li><a href="<c:url value = "profil"/>"><c:out value="${ user.username }" /></a></li>
	                    <li><a href="<c:url value = "formcreatearticle"/>">Mes Articles</a></li>
                		<li><a href="<c:url value = "snakes"/>">Snakes</a></li>
	                    <li><a href="<c:url value = "logout"/>">Déconnexion</a></li>
				    </c:otherwise>
            	</c:choose>
            </ul>
            <ul class="side-nav" id="mobile-demo">
            	<c:choose>
				    <c:when test="${ empty sessionScope.user }">
				    	<li><a href="<c:url value = "login"/>">Connexion</a></li>
				    	<li><a href="<c:url value = "register"/>">Inscription</a></li>
				    </c:when>
				    <c:otherwise>
						<div class="user-view">
		                    <div class="background teal"></div>
		                    <a href="<c:url value = "profil"/>"><img class="circle" src="snake-icon.png"></a>
		                    <a href="<c:url value = "profil"/>"><span class="white-text name"><c:out value="${ user.username }" /></span></a>
		                    <a href="<c:url value = "logout"/>"><span class="white-text email"><c:out value="${ user.email }" /></span></a>
	                	</div>
                		<li><a href="<c:url value = "snakes"/>">Snakes</a></li>
	                    <li><a href="<c:url value = "logout"/>">Déconnexion</a></li>
				    </c:otherwise>
            	</c:choose>
            </ul>
        </div>
    </nav>
    
    <div class="row">
    <!-- Mes snakes -->
    <div class="col s12 offset-m1 m5 offset-l2 l4">
        <div class="card">                
            <div class="card-content">
                <a class="card-title">Mes snakes</a>
                <p>
                    Vous pouvez consulter ici l'avancé de vos personnages et améliorer leurs compétences.
                </p>
            </div>                
        </div>
    </div>        
    
    <!-- Profil -->
    <div class="col s12 m5 l4">
        <div class="card">                
            <div class="card-content">
                <a class="card-title">Mon profil</a>
                <p>
                    Vous pouvez consulter ici votre niveau et vos statistiques.
                </p>
            </div>                
        </div>
    </div>
    <c:forEach items="${listArticles}" var="article" >
    <div class="col s8 offset-s2">
        <div class="card">                
            <div class="card-content">
                <a class="card-title"><c:out value="${ article.title } by ${ article.author.username } (${ article.date })" /></a>
                <p>
                    <c:out value="${ article.content }" />
                </p>
            </div>                
        </div>
    </div>
    </c:forEach>
    
    </div>
    
    
    <script>
        $( document ).ready(function(){
            $(".button-collapse").sideNav();
        })
    </script>
</body>
</html>