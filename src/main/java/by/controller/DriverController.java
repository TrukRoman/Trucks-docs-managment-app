package by.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.model.Driver;
import by.service.IDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DriverController {

    private IDriverService driverServiceImp;

    @Autowired
    public void setDriverService(IDriverService driverServiceImp) {
        this.driverServiceImp = driverServiceImp;
    }

    @GetMapping(value = "driver")
    void listDriver(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List <Driver> listDriver = driverServiceImp.selectAll();
        request.setAttribute("listDriver", listDriver);
        RequestDispatcher dispatcher = request.getRequestDispatcher("driver/driver-list.jsp");
        dispatcher.forward(request, response);
    }

    @GetMapping(value="/newdriver")
    void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("driver/driver-form.jsp");
        dispatcher.forward(request, response);
    }

    @GetMapping(value="/editDriver")
    void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Driver existingDriver = driverServiceImp.selectById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("driver/driver-form.jsp");
        request.setAttribute("driver", existingDriver);
        dispatcher.forward(request, response);
    }

    @PostMapping(value="/insertDriver")
    void insertDriver(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String name = request.getParameter("name");
        String driverLicenceNum = request.getParameter("driverLicenceNum");
        String category = request.getParameter("category");
        Date dlvalidity = Date.valueOf(request.getParameter("dlvalidity"));
        String medicalCertificateNumber = request.getParameter("medicalCertificateNumber");
        Date mcvalidaty = Date.valueOf(request.getParameter("mcvalidaty"));

        Driver newDriver = new Driver(name, driverLicenceNum, category, dlvalidity, medicalCertificateNumber, mcvalidaty);
        driverServiceImp.insert(newDriver);
        response.sendRedirect("driver");
    }

    @PostMapping(value="/updateDriver")
    void updateDriver(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String driverLicenceNum = request.getParameter("driverLicenceNum");
        String category = request.getParameter("category");
        Date dlvalidity = Date.valueOf(request.getParameter("dlvalidity"));
        String medicalCertificateNumber = request.getParameter("medicalCertificateNumber");
        Date mcvalidaty = Date.valueOf(request.getParameter("mcvalidaty"));

        Driver driver = new Driver(id, name, driverLicenceNum, category, dlvalidity, medicalCertificateNumber, mcvalidaty);
        driverServiceImp.update(driver);
        response.sendRedirect("driver");
    }

    @GetMapping(value="/deleteDriver")
    void deleteDriver(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        driverServiceImp.delete(id);
        response.sendRedirect("driver");
    }

    @GetMapping(value="/validatyDriver")
    void showAllDriverWhoseValidatyIsEnds(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<Driver> listDriver = driverServiceImp.selectAllWhoseValidatyIsEnds();
        request.setAttribute("listDriver", listDriver);
        RequestDispatcher dispatcher = request.getRequestDispatcher("driver/driver-list-validaty.jsp");
        dispatcher.forward(request, response);
    }
}

