<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Keep-Board</title>
</head>

<body>
	<!-- Create a form which will have text boxes for Note title, content and status along with a Add 
		 button. Handle errors like empty fields.  (Use dropdown-list for NoteStatus) -->
	<h1>Add Note</h1>
	<form:form action="add" method="post" modelAttribute="note">
		<!-- <form:errors path="*" /> -->
		<table>
			<tr>
				<td>Title :</td>
				<td><form:input name="noteTitle" path="noteTitle" /></td>
			</tr>
			<tr>
				<td>Content :</td>
				<td><form:input path="noteContent" /></td>
			</tr>
			<tr>
				<td>Status :</td>
				<td><form:input path="noteStatus" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Add"></td>
			</tr>
		</table>
	</form:form>

	<!-- display all existing notes in a tabular structure with Title,Content,Status, Created Date and Action -->
	<table border="1">
		<thead>
			<tr>
				<th>Title</th>
				<th>Content</th>
				<th>Status</th>
			</tr>
		</thead>
		<tbody>
				<tr>
					<td>${note.noteTitle}</td>
					<td>${note.noteContent}</td>
					<td>"${note.noteStatus}</td>

				</tr>

			


		</tbody>
	</table>
</body>

</html>