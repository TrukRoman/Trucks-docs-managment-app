package by.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.dao.DriverDAO;
import by.model.Driver;

public class DriverOperations {
    private static final DriverDAO driverDAO = new DriverDAO();

    void listDriver(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List <Driver> listDriver = driverDAO.selectAllDriver();
        request.setAttribute("listDriver", listDriver);
        RequestDispatcher dispatcher = request.getRequestDispatcher("driver/driver-list.jsp");
        dispatcher.forward(request, response);
    }

    void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("driver/driver-form.jsp");
        dispatcher.forward(request, response);
    }

    void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Driver existingDriver = driverDAO.selectDriver(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("driver/driver-form.jsp");
        request.setAttribute("driver", existingDriver);
        dispatcher.forward(request, response);
    }

    void insertDriver(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String name = request.getParameter("name");
        String driverLicenceNum = request.getParameter("driverLicenceNum");
        String category = request.getParameter("category");
        Date dlvalidity = Date.valueOf(request.getParameter("dlvalidity"));
        String medicalCertificateNumber = request.getParameter("medicalCertificateNumber");
        Date mcvalidaty = Date.valueOf(request.getParameter("mcvalidaty"));

        Driver newDriver = new Driver(name, driverLicenceNum, category, dlvalidity, medicalCertificateNumber, mcvalidaty);
        driverDAO.insertDriver(newDriver);
        response.sendRedirect("driver");
    }

    void updateDriver(HttpServletRequest request, HttpServletResponse response)
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
        response.sendRedirect("driver");
    }

    void deleteDriver(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        driverDAO.deleteDriver(id);
        response.sendRedirect("driver");
    }

    void showAllDriverWhoseValidatyIsEnds(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<Driver> listDriver = driverDAO.selectAllDriverWhoseValidatyIsEnds();
        request.setAttribute("listDriver", listDriver);
        RequestDispatcher dispatcher = request.getRequestDispatcher("driver/driver-list-validaty.jsp");
        dispatcher.forward(request, response);
    }
}

