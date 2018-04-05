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
</head>
<body class="grey lighten-4">
    <nav>
        <div class="nav-wrapper">
            <a href="<c:url value = "index"/>" class="brand-logo center">Snake RPG</a>
        </div>
    </nav>
    
    <div class="row">
    <!-- Register -->
    <div class="card col offset-m2 m8 offset-l3 l6 s12">           
            <div class="card-content">
                <span class="card-title">Connexion</span>
				<form method="post" action="<c:url value = "login"/>">
					<div class="row">
						<div class="input-field col s12">
							<i class="material-icons prefix">account_circle</i>
							<input
								type="text"
								class="validate <c:out value="${ erreurs['username'] != NULL ? 'invalid' : '' }"/>"
								name="username"
								id="un"
								value="<c:out value="${ user.username }" />"
								>
							<label
								for="un"
								data-error="<c:out value="${ erreurs['username'] }"/>"
								class=""
								>Username</label>
						</div>
						
						<div class="input-field col s12">
							<i class="material-icons prefix">lock</i>
							<input
								type="password"
								class="validate <c:out value="${ erreurs['password'] != NULL ? 'invalid' : '' }"/>"
								name="password"
								id="pwd"
								>
							<label
								for="pwd"
								data-error="<c:out value="${ erreurs['password'] }"/>"
								>Password</label>
							<label for="pwd">Password</label>
						</div>
					<button type="submit" class="btn col offset-s2 s8">Login</button>
					</div>
				</form>
            </div>                
        </div>
    
    </div>
    
    
    <script>
        $( document ).ready(function(){
            $(".button-collapse").sideNav();
        })
    </script>
</body>
</html>