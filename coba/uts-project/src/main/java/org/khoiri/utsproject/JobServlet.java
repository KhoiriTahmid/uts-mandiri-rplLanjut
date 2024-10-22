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

@WebServlet("/jobs")
public class JobServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            List<Job> jobs = session.createQuery("FROM Job", Job.class).list();
            request.setAttribute("jobs", jobs);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        request.getRequestDispatcher("/jobs.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            createJob(request);
        } else if ("update".equals(action)) {
            updateJob(request);
        } else if ("delete".equals(action)) {
            deleteJob(request);
        }

        response.sendRedirect("jobs");
    }

    private void createJob(HttpServletRequest request) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Job job = new Job();
            job.setJobId(request.getParameter("job_id"));
            job.setJobTitle(request.getParameter("job_title"));
            job.setMinSalary(Double.parseDouble(request.getParameter("min_salary")));
            job.setMaxSalary(Double.parseDouble(request.getParameter("max_salary")));

            session.save(job);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private void updateJob(HttpServletRequest request) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            String jobId = request.getParameter("job_id");
            Job job = session.get(Job.class, jobId);
            if (job != null) {
                job.setJobTitle(request.getParameter("job_title"));
                job.setMinSalary(Double.parseDouble(request.getParameter("min_salary")));
                job.setMaxSalary(Double.parseDouble(request.getParameter("max_salary")));

                session.update(job);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private void deleteJob(HttpServletRequest request) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            String jobId = request.getParameter("job_id");
            Job job = session.get(Job.class, jobId);
            if (job != null) {
                session.delete(job);
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

