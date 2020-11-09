package by.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.dao.DriverDAO;
import by.model.Driver;

@WebServlet(urlPatterns = "/driver")
public class DriverServlet extends HttpServlet {
    private final DriverDAO driverDAO = new DriverDAO();

    public void init(ServletConfig servletConfig) {
        try {
            super.init(servletConfig);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/newdriver":
                    showNewForm(request, response);
                    break;
                case "/insertDriver":
                    insertDriver(request, response);
                    break;
                case "/deleteDriver":
                    deleteDriver(request, response);
                    break;
                case "/editDriver":
                    showEditForm(request, response);
                    break;
                case "/updateDriver":
                    updateDriver(request, response);
                    break;
                case "/validatyDriver":
                    showAllDriverWhoseValidatyIsEnds(request, response);
                    break;
                default:
                    listDriver(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listDriver(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List <Driver> listDriver = driverDAO.selectAllDriver();
        request.setAttribute("listDriver", listDriver);
        RequestDispatcher dispatcher = request.getRequestDispatcher("driver/driver-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("driver/driver-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Driver existingDriver = driverDAO.selectDriver(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("driver/driver-form.jsp");
        request.setAttribute("driver", existingDriver);
        dispatcher.forward(request, response);
    }

    private void insertDriver(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String name = request.getParameter("name");
        String driverLicenceNum = request.getParameter("driverLicenceNum");
        String category = request.getParameter("category");
        Date dlvalidity = Date.valueOf(request.getParameter("dlvalidity"));
        String medicalCertificateNumber = request.getParameter("medicalCertificateNumber");
        Date mcvalidaty = Date.valueOf(request.getParameter("mcvalidaty"));

        Driver newDriver = new Driver(name, driverLicenceNum, category, dlvalidity, medicalCertificateNumber, mcvalidaty);
        driverDAO.insertDriver(newDriver);
        response.sendRedirect("list");
    }

    private void updateDriver(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String driverLicenceNum = request.getParameter("driverLicenceNum");
        String category = request.getParameter("category");
        Date dlvalidity = Date.valueOf(request.getParameter("dlvalidity"));
        String medicalCertificateNumber = request.getParameter("medicalCertificateNumber");
        Date mcvalidaty = Date.valueOf(request.getParameter("mcvalidaty"));

        Driver book = new Driver(id, name, driverLicenceNum, category, dlvalidity, medicalCertificateNumber, mcvalidaty);
        driverDAO.updateDriver(book);
        response.sendRedirect("list");
    }

    private void deleteDriver(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        driverDAO.deleteDriver(id);
        response.sendRedirect("list");
    }

    private void showAllDriverWhoseValidatyIsEnds(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<Driver> listDriver = driverDAO.selectAllDriverWhoseValidatyIsEnds();
        request.setAttribute("listDriver", listDriver);
        RequestDispatcher dispatcher = request.getRequestDispatcher("driver/driver-list-validaty.jsp");
        dispatcher.forward(request, response);
    }
}

