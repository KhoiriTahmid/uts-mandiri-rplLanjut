<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Job List</title>
</head>
<body>
<h2>Job List</h2>
<table border="1">
    <tr>
        <th>Job ID</th>
        <th>Job Title</th>
        <th>Min Salary</th>
        <th>Max Salary</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="job" items="${jobs}">
        <tr>
            <td>${job.jobId}</td>
            <td>${job.jobTitle}</td>
            <td>${job.minSalary}</td>
            <td>${job.maxSalary}</td>
            <td>
                <form action="jobs" method="post">
                    <input type="hidden" name="job_id" value="${job.jobId}">
                    <input type="hidden" name="action" value="delete">
                    <input type="submit" value="Delete">
                </form>
                <a href="create_job.jsp?job_id=${job.jobId}">Edit</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="create_job.jsp">Add Job</a>
</body>
</html>
