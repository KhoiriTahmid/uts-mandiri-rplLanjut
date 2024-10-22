<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee List</title>
</head>
<body>
<h2>Employee List</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <th>Phone Number</th>
        <th>Hire Date</th>
        <th>Salary</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="employee" items="${employees}">
        <tr>
            <td>${employee.employeeId}</td>
            <td>${employee.firstName}</td>
            <td>${employee.lastName}</td>
            <td>${employee.email}</td>
            <td>${employee.phoneNumber}</td>
            <td>${employee.hireDate}</td>
            <td>${employee.salary}</td>
            <td>
                <form action="employees" method="post">
                    <input type="hidden" name="employee_id" value="${employee.employeeId}">
                    <input type="hidden" name="action" value="delete">
                    <input type="submit" value="Delete">
                </form>
                <a href="create_employee.jsp?employee_id=${employee.employeeId}">Edit</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="create_employee.jsp">Add Employee</a>
</body>
</html>
