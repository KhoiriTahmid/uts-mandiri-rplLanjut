<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Create/Edit Employee</title>
</head>
<body>
<h2>Create/Edit Employee</h2>
<form action="employees" method="post">
  <input type="hidden" name="action" value="${param.employee_id != null ? 'update' : 'create'}">
  <input type="hidden" name="employee_id" value="${param.employee_id}">

  First Name: <input type="text" name="first_name" value="${employee != null ? employee.firstName : ''}" required><br>
  Last Name: <input type="text" name="last_name" value="${employee != null ? employee.lastName : ''}" required><br>
  Email: <input type="email" name="email" value="${employee != null ? employee.email : ''}"><br>
  Phone Number: <input type="text" name="phone_number" value="${employee != null ? employee.phoneNumber : ''}"><br>
  Hire Date: <input type="date" name="hire_date" value="${employee != null ? employee.hireDate : ''}" required><br>
  Salary: <input type="number" name="salary" value="${employee != null ? employee.salary : ''}"><br>
  Commission Pct: <input type="number" name="commission_pct" value="${employee != null ? employee.commissionPct : ''}"><br>
  Bonus: <input type="text" name="bonus" value="${employee != null ? employee.bonus : ''}"><br>

  <input type="submit" value="${param.employee_id != null ? 'Update' : 'Create'}">
</form>
<a href="employees">Back to Employee List</a>
</body>
</html>
