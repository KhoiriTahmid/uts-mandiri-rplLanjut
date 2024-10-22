package org.khoiri.utsproject;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.io.IOException;
import java.util.List;

@WebServlet("/employees")
public class EmployeeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            List<Employee> employees = session.createQuery("FROM Employee", Employee.class).list(); // Use the entity class name
            request.setAttribute("employees", employees);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }


        request.getRequestDispatcher("/employees.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            createEmployee(request);
        } else if ("update".equals(action)) {
            updateEmployee(request);
        } else if ("delete".equals(action)) {
            deleteEmployee(request);
        }

        response.sendRedirect("employees");
    }

    private void createEmployee(HttpServletRequest request) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Employee employee = new Employee();
            employee.setEmployeeId(Long.parseLong(request.getParameter("employee_id")));
            employee.setFirstName(request.getParameter("first_name"));
            employee.setLastName(request.getParameter("last_name"));
            employee.setEmail(request.getParameter("email"));
            employee.setPhoneNumber(request.getParameter("phone_number"));
            employee.setHireDate(java.sql.Date.valueOf(request.getParameter("hire_date")));
            employee.setSalary(Double.parseDouble(request.getParameter("salary")));
            employee.setCommissionPct(Double.parseDouble(request.getParameter("commission_pct")));
            employee.setBonus(request.getParameter("bonus"));
            // Set Job if necessary...

            session.save(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private void updateEmployee(HttpServletRequest request) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Long employeeId = Long.parseLong(request.getParameter("employee_id"));
            Employee employee = session.get(Employee.class, employeeId);
            if (employee != null) {
                employee.setFirstName(request.getParameter("first_name"));
                employee.setLastName(request.getParameter("last_name"));
                employee.setEmail(request.getParameter("email"));
                employee.setPhoneNumber(request.getParameter("phone_number"));
                employee.setHireDate(java.sql.Date.valueOf(request.getParameter("hire_date")));
                employee.setSalary(Double.parseDouble(request.getParameter("salary")));
                employee.setCommissionPct(Double.parseDouble(request.getParameter("commission_pct")));
                employee.setBonus(request.getParameter("bonus"));
                // Update Job if necessary...

                session.update(employee);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private void deleteEmployee(HttpServletRequest request) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Long employeeId = Long.parseLong(request.getParameter("employee_id"));
            Employee employee = session.get(Employee.class, employeeId);
            if (employee != null) {
                session.delete(employee);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}

