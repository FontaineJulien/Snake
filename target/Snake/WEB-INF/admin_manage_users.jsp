<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manage users</title>
<link type="text/css" rel="stylesheet" href="bootstrap.min.css" />
</head>
<body>
<c:choose>
	<c:when test="${ !empty userList }" >
		<table class="table">
			<thead class="thead-dark">
				<tr>
				    <th>#</th>
				    <th>Username</th>
				    <th>Email</th>
				    <th>Credit</th>
				    <th>Admin</th>
				    <th>Remove</th>
				    <th>Update</th>
			  	</tr>
			</thead>
			<tbody>
				
				  	<c:forEach items="${userList}" var="user" >
					  	<tr>
					  		<td><c:out value="${ user.id }" /></td>
					  		<td><c:out value="${ user.username }" /></td>
					  		<td><c:out value="${ user.email }" /></td>
					  		<td><c:out value="${ user.credit }" /></td>
					  		<td>
						  		<c:choose>
								    <c:when test="${user.isAdmin == 1}">Yes</c:when>
								    <c:otherwise>No</c:otherwise>
								</c:choose>
					  		</td>
					  		<td>
					  			<form method="post" action="<c:url value="removeuser"/>">
					  				<input name="idPlayer" type="hidden" value="<c:out value="${ user.id }" />">
					  				<button type="submit" class="btn btn-danger">x</button>
					  			</form>
					  		</td>
					  		<td>
					  			<form method="post" action="<c:url value="formupdateuser"/>">
					  				<input name="idPlayer" type="hidden" value="<c:out value="${ user.id }" />">
					  				<button type="submit" class="btn btn-primary">o</button>
					  			</form>					  		
					  		</td>
					  	</tr>
				  	</c:forEach>
			  </tbody>
		</table>
	</c:when>
	<c:otherwise>There is no user to display</c:otherwise>
</c:choose>

</body>
</html>