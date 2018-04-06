<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
<link type="text/css" rel="stylesheet" href="bootstrap.min.css" />
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Créer un article</title>
</head>
<body>
	<c:choose>
		<c:when test="${ !empty listArticles }" >
			<table class="table">
				<thead class="thead-dark">
					<tr>
					    <th>#</th>
					    <th>Titre</th>
					    <th>Contenu</th>
					    <th>Date</th>
					    <th>Supprimer</th>
					    <th>Modifier</th>
				  	</tr>
				</thead>
				<tbody>
					
					  	<c:forEach items="${listArticles}" var="article" >
						  	<tr>
						  		<td><c:out value="${ article.id }" /></td>
						  		<td><c:out value="${ article.title }" /></td>
						  		<td><c:out value="${ article.content }" /></td>
						  		<td><c:out value="${ article.date }" /></td>
						  		<td>
						  			<form method="post" action="<c:url value="removearticle"/>">
						  				<input name="idArticle" type="hidden" value="<c:out value="${ article.id }" />">
						  				<button type="submit" class="btn btn-danger">x</button>
						  			</form>
						  		</td>
						  		<td>
						  			<form>
						  				<input name="idArticle" type="hidden" value="<c:out value="${ article.id }" />">
						  				<button type="submit" class="btn btn-primary">o</button>
						  			</form>					  		
						  		</td>
						  	</tr>
					  	</c:forEach>
				  </tbody>
			</table>
		</c:when>
		<c:otherwise>Vous n'avez aucun article</c:otherwise>
	</c:choose>
	<form method="post" action="<c:url value="createarticle"/>">
	  <label for="titre">Titre : </label>
      <input type="text" id="titre" name="title" class="validate">	
      <textarea id="content" name="content" class="materialize-textarea"></textarea>
	  <button class="btn waves-effect waves-light" type="submit" name="action">Submit
	    <i class="material-icons right">send</i>
	  </button>
	</form>
</body>
</html>