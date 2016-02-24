<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Person Page</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>

<h2>
	Add a Person
</h2>

<c:url var="addAction" value="/person/add" ></c:url>
<c:url var="nextAction" value="/person/next" ></c:url>
<c:url var="prevAction" value="/person/prev" ></c:url>

<form:form action="${addAction}" commandName="person">
<table>
	<c:if test="${!empty person.name}">
	<tr>
		<td>
			<form:label path="id">
				<spring:message text="ID"/>
			</form:label>
		</td>
		<td>
			<form:input path="id" readonly="true" size="8"  disabled="true" />
			<form:hidden path="id" />
		</td> 
	</tr>
	</c:if>
	<tr>
		<td>
			<form:label path="name">
				<spring:message text="Name"/>
			</form:label>
		</td>
		<td>
			<form:input path="name" />
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="age">
				<spring:message text="Age"/>
			</form:label>
		</td>
		<td>
			<form:input path="age" />
		</td>
	</tr>
	<tr>
		<td>
			<form:label path="isAdmin">
				<spring:message text="isAdmin"/>
			</form:label>
		</td>
		<td>
			<form:input path="isAdmin"/>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<c:if test="${!empty person.name}">
				<input type="submit"
					value="<spring:message text="Edit Person"/>" />
			</c:if>
			<c:if test="${empty person.name}">
				<input type="submit"
					value="<spring:message text="Add Person"/>" />
			</c:if>
		</td>
	</tr>
</table>	
</form:form>
<br>
<h3>Persons List</h3>
<c:if test="${!empty listPersons}">
	<table class="tg">
	<tr>
		<th width="80">Person ID</th>
		<th width="120">Person Name</th>
		<th width="120">Person Age</th>
        <th width="120">Person isAdmin</th>
        <th width="120">Created date</th>
		<th width="60">Edit</th>
		<th width="60">Delete</th>
	</tr>
	<c:forEach items="${listPersons}" var="person">
		<tr>
			<td>${person.id}</td>
			<td>${person.name}</td>
			<td>${person.age}</td>
			<td>${person.isAdmin == 1 ? "yes" : "no"}</td>
			<td><fmt:formatDate value="${person.createdDate}" pattern="dd-MM-yyyy HH:mm:ss"/></td>
			<td><a href="<c:url value='/edit/${person.id}' />" >Edit</a></td>
			<td><a href="<c:url value='/remove/${person.id}' />" >Delete</a></td>
		</tr>
	</c:forEach>
	</table>

    <br>
    <c:choose>
        <c:when test="${page > 0}">
        <form:form action="${prevAction}" commandName="prevCommand">
            <input type="submit" value="<spring:message text="Prev"/>" />
        </form:form>
        </c:when>
        <c:otherwise>
            <input type="submit" value="<spring:message text="Prev"/>" disabled/>
        </c:otherwise>
    </c:choose>
    <c:choose>
        <c:when test="${page + 1 < pageCount}">
            <form:form action="${nextAction}" commandName="nextCommand">
                <input type="submit" value="<spring:message text="Next"/>" />
            </form:form>
        </c:when>
        <c:otherwise>
            <input type="submit" value="<spring:message text="Next"/>" disabled/>
        </c:otherwise>
    </c:choose>
    <h2>  Page - ${page + 1} / ${pageCount} </h2>
</c:if>

</body>
</html>
