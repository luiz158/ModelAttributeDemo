<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Keep-Board</title>
</head>

<body>
	<div>
		<c:if test="${not empty errorMessage}">${errorMessage}</c:if><br/>
		<c:if test="${not empty errorMessage1}">${errorMessage1}</c:if><br/>
	</div>
	<!-- Create a form which will have text boxes for Note title, content and status along with a Add 
		 button. Handle errors like empty fields.  (Use dropdown-list for NoteStatus) -->
		<c:choose>
		<c:when test="${editNote eq null}">
			<h1>Add mode</h1>
			<form:form action="add" method="post" modelAttribute="note">
			<!-- <form:errors path="*" /> -->
				<table>
					<tr>
						<td>Title :</td>
						<td><form:input path="noteTitle"/></td>
						<!-- <td><form:errors path="noteTitle"/></td> --> 
					</tr>
					<tr>
						<td>Content :</td>
						<td><form:textarea rows="2" cols="15" path="noteContent"></form:textarea></td>
						<!-- <td><form:errors path="noteContent"/></td> --> 
					</tr>
					<tr>
						<td>Status :</td>
						<td>
							<form:select path="noteStatus">
								<form:option value="-">select from options</form:option>
							  <form:option value="started">Started</form:option>
							  <form:option value="inprogress">In Progress</form:option>
							  <form:option value="completed">Completed</form:option>
							</form:select>
						</td>
					</tr>
					<tr>						
						<td><input type="submit" value="Add"></td>
						<td><input type="reset" value="Clear" /></td>
					</tr>					
				</table>
			</form:form>
		</c:when>
		<c:otherwise>
			<h1>Edit mode</h1>
			<form:form action="update" method="post" modelAttribute="editNote">
			<!-- <form:errors path="*" /> -->
				<table>
					<form:input type="hidden" path="noteId" value="${editNote.noteId}"/>
					<tr>
						<td>Title :</td>
						<td><form:input path="noteTitle" value="${editNote.noteTitle}"/></td>
						<!-- <td><form:errors path="noteTitle"/></td> --> 
					</tr>
					<tr>
						<td>Content :</td>
						<td><form:textarea rows="2" cols="15" path="noteContent" value="${editNote.noteContent}"></form:textarea></td>
						<!-- <td><form:errors path="noteContent"/></td> --> 
					</tr>
					<tr>
						<td>Status :</td>
						<td>
							<form:select path="noteStatus">
							  <form:option value="-" selected="${editNote.noteStatus eq \"-\" ? \"selected\" : \"\" }">select from options</form:option>
							  <form:option value="started" selected="${editNote.noteStatus eq \"started\" ? \"selected\" : \"\" }">Started</form:option>
							  <form:option value="inprogress" selected="${editNote.noteStatus eq \"inprogress\" ? \"selected\" : \"\" }">In Progress</form:option>
							  <form:option value="completed" selected="${editNote.noteStatus eq \"completed\" ? \"selected\" : \"\" }">Completed</form:option>
							</form:select>
						</td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="update"></td>
					</tr>					
				</table>
			</form:form>
		</c:otherwise>
	</c:choose>

	<!-- display all existing notes in a tabular structure with Title,Content,Status, Created Date and Action -->
	<table border="1">
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Content</th>
                    <th>Status</th>
                    <th>Created Date</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="note" items="${notes}">
                <c:url value="delete" var="deleteUrl">
				  <c:param name="noteId" value="${note.noteId}" />
				</c:url>
				<c:url value="update" var="updateUrl">
				  <c:param name="noteId" value="${note.noteId}" />
				</c:url>
                <tr>
                    <td><c:out value="${note.noteTitle}" /></td>
                    <td><c:out value="${note.noteContent}" /></td>
                    <td><c:out value="${note.noteStatus}" /></td>
                    <td><c:out value="${note.createdAt}" /></td>
                    <td><a href="${updateUrl}">Update Note</a> / <a href="${deleteUrl}">Delete Note</a></td>
                </tr>
                 </c:forEach>
            </tbody>
        </table>	
</body>

</html>