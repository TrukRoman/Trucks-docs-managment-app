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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DriverController {
    private static final DriverDAO driverDAO = new DriverDAO();

    @RequestMapping(value="/driver", method= RequestMethod.GET)
    void listDriver(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List <Driver> listDriver = driverDAO.selectAllDriver();
        request.setAttribute("listDriver", listDriver);
        RequestDispatcher dispatcher = request.getRequestDispatcher("driver/driver-list.jsp");
        dispatcher.forward(request, response);
    }

    @RequestMapping(value="/newdriver", method= RequestMethod.GET)
    void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("driver/driver-form.jsp");
        dispatcher.forward(request, response);
    }

    @RequestMapping(value="/editDriver", method= RequestMethod.GET)
    void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Driver existingDriver = driverDAO.selectDriver(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("driver/driver-form.jsp");
        request.setAttribute("driver", existingDriver);
        dispatcher.forward(request, response);
    }

    @RequestMapping(value="/insertDriver", method= RequestMethod.POST)
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

    @RequestMapping(value="/updateDriver", method= RequestMethod.POST)
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

    @RequestMapping(value="/deleteDriver", method= RequestMethod.GET)
    void deleteDriver(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        driverDAO.deleteDriver(id);
        response.sendRedirect("driver");
    }

    @RequestMapping(value="/validatyDriver", method= RequestMethod.GET)
    void showAllDriverWhoseValidatyIsEnds(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<Driver> listDriver = driverDAO.selectAllDriverWhoseValidatyIsEnds();
        request.setAttribute("listDriver", listDriver);
        RequestDispatcher dispatcher = request.getRequestDispatcher("driver/driver-list-validaty.jsp");
        dispatcher.forward(request, response);
    }
}

