<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	<title>Modifier un joueur</title>
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
</head>
<body>


  <div class="row">
    <form class="col s12" method="post" action="<c:url value="updateuser"/>">
      <div class="row">
		<div class="input-field col s3">
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
				>Username</label>
		</div>
		<div class="input-field col s3">
			<i class="material-icons prefix">email</i>
			<input
				type="email"
				class="validate <c:out value="${ erreurs['username'] != NULL ? 'invalid' : '' }"/>"
				name="email"
				id="em"
				value="<c:out value="${ user.email }" />"
				>
			<label
				for="em"
				data-error="<c:out value="${ erreurs['email'] }"/>"
				>Email</label>
		</div>
		
      </div>
      
      <div class="switch">
		    <label>
		      Not admin
		      <input type="checkbox" name="isAdmin">
		      <span class="lever"></span>
		      Admin
		    </label>
	 </div>
	 
	 <input name="idPlayer" type="hidden" value="<c:out value="${ user.id }" />">
	 
	  <button class="btn waves-effect waves-light" type="submit" name="action">Submit
	    <i class="material-icons right">send</i>
	  </button>
    </form>
  </div>
    
               

    <script>
        $( document ).ready(function(){
            $(".button-collapse").sideNav();
        })
    </script>
    <script>
    	$(document).ready(function() {
    	  $("input").change(function() {
    	    if($(this).is(":checked")) {
    	      console.log("Is checked");
    	    }
    	    else {
    	      console.log("Is Not checked");
    	    }
    	  })
    	});
    </script>

</body>
</html>