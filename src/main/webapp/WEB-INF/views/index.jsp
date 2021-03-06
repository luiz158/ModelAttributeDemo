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
	<form:form action="add" method="post" modelAttribute="vendor">
		<!-- <form:errors path="*" /> -->
		<table>
			<tr>
				<td>Title :</td>
				<td><form:input path="vendorCode" /></td>
			</tr>
			<tr>
				<td>Content :</td>
				<td><form:input path="vendorName" /></td>
			</tr>
			<tr>
				<td>Status :</td>
				<td><form:input path="vendorCity" /></td>
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
					<td>${vendor.vendorCode}</td>
					<td>${vendor.vendorName}</td>
					<td>${vendor.vendorCity}</td>

				</tr>

			


		</tbody>
	</table>
</body>

</html>