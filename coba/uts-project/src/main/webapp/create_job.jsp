<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create/Edit Job</title>
</head>
<body>
<h2>Create/Edit Job</h2>
<form action="jobs" method="post">
    <input type="hidden" name="action" value="${param.job_id != null ? 'update' : 'create'}">
    <input type="hidden" name="job_id" value="${param.job_id}">

    Job Title: <input type="text" name="job_title" value="${job != null ? job.jobTitle : ''}" required><br>
    Min Salary: <input type="number" name="min_salary" value="${job != null ? job.minSalary : ''}" required><br>
    Max Salary: <input type="number" name="max_salary" value="${job != null ? job.maxSalary : ''}" required><br>

    <input type="submit" value="${param.job_id != null ? 'Update' : 'Create'}">
</form>
<a href="jobs">Back to Job List</a>
</body>
</html>
