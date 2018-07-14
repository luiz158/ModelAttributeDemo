
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Keep-Board</title>
<style>
table {
	border-collapse: collapse;
	align: center;
}

table, td, th {
	border: 1px solid black;
	align: center;
	padding: 1em;
}

div {
	padding: 1em;
	align: center;
}

label {
	padding: 1em;
	width: 2em;
}
</style>
<script>
	function submitform() {
		document.getElementById('form').action = "add";
		document.getElementById('form').method = "POST";
		document.getElementById().submit();
	}
</script>
</head>
</head>
<body>
	<center>
		<!-- Create a form which will have text boxes for Note ID, title, content and status along with a Send 
		 button. Handle errors like empty fields -->
		<form:form id="form" name="form" action="" method="POST"
			modelAttribute="note">
			<div>
				<label>Enter Note Title</label> <input type="text" id="noteTitle"
					name="noteTitle" />
				<form:errors path="noteTitle" cssClass="error" />
			</div>
			<div>
				<label>Enter Note Content</label>
				<textarea id="noteContent" name="noteContent"></textarea>
				<form:errors path="noteContent" cssClass="error" />
			</div>
			<div>
				<label>Enter Note Status</label> <select id="noteStatus"
					name="noteStatus">
					<option value="Started">Started</option>
					<option value="Not Started">Not Started</option>
					<option value="Completed">Completed</option>
				</select>
				<form:errors path="noteStatus" cssClass="error" />
			</div>
			<div>
				<input type="submit" onclick="submitform();" value="Submit" />
			</div>

		</form:form>
	</center>
	<!-- display all existing notes in a tabular structure with Id, Title,Content,Status, Created Date and Action -->
	<div>
		<table>
			<tr>
				<th>Note Id</th>
				<th>Note Title</th>
				<th>Note Content</th>
				<th>Note Status</th>
				<th>Created At</th>
				<th>Action</th>
			</tr>
			<c:if test="${not empty noteList}">
				<c:forEach items="${noteList}" var="note">
					<tr>
						<td>${note.getNoteId()}</td>
						<td>${note.getNoteTitle()}</td>
						<td>${note.getNoteContent()}</td>
						<td>${note.getNoteStatus()}</td>
						<td>${note.getCreatedAt()}</td>
						<td><a href="delete?noteId=${note.getNoteId()}">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
	</div>
</body>
</html>