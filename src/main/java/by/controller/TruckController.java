package by.controller;

import by.dao.TruckDAO;
import by.model.Truck;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@Controller
public class TruckController {
    private final TruckDAO truckDAO = new TruckDAO();

    @RequestMapping(value="/truck", method= RequestMethod.GET)
    void listTrucks(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<Truck> listTrucks = truckDAO.selectAllTrucks();
        request.setAttribute("listTrucks", listTrucks);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/truck/truck-list.jsp");
        dispatcher.forward(request, response);
    }

    @RequestMapping(value="/newtruck", method= RequestMethod.GET)
    void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/truck/truck-form.jsp");
        dispatcher.forward(request, response);
    }

    @RequestMapping(value="/editTruck", method= RequestMethod.GET)
    void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Truck existingTruck = truckDAO.selectTruck(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/truck/truck-form.jsp");
        request.setAttribute("truck", existingTruck);
        dispatcher.forward(request, response);
    }

    @RequestMapping(value="/insertTruck", method= RequestMethod.POST)
    void insertTruck(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String model = request.getParameter("model");
        String type = request.getParameter("type");
        Date yearOfProduction = Date.valueOf(request.getParameter("yearOfProduction"));
        String registerSign = request.getParameter("registerSign");
        String insuranceNumber = request.getParameter("insuranceNumber");
        Date insuranceValidity = Date.valueOf(request.getParameter("insuranceValidity"));
        Date technicalInspectionValidity = Date.valueOf(request.getParameter("technicalInspectionValidity"));

        Truck newTruck = new Truck(model, type, yearOfProduction, registerSign, insuranceNumber, insuranceValidity, technicalInspectionValidity);
        truckDAO.insertTruck(newTruck);
        response.sendRedirect("truck");
    }

    @RequestMapping(value="/updateTruck", method= RequestMethod.POST)
    void updateTruck(HttpServletRequest request, HttpServletResponse response)
            throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String model = request.getParameter("model");
        String type = request.getParameter("type");
        Date yearOfProduction = Date.valueOf(request.getParameter("yearOfProduction"));
        String registerSign = request.getParameter("registerSign");
        String insuranceNumber = request.getParameter("insuranceNumber");
        Date insuranceValidity = Date.valueOf(request.getParameter("insuranceValidity"));
        Date technicalInspectionValidity = Date.valueOf(request.getParameter("technicalInspectionValidity"));

        Truck newTruck = new Truck(id, model, type, yearOfProduction, registerSign, insuranceNumber, insuranceValidity, technicalInspectionValidity);
        truckDAO.updateTruck(newTruck);
        response.sendRedirect("truck");
    }

    @RequestMapping(value="/deleteTruck", method= RequestMethod.GET)
    void deleteTruck(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        truckDAO.deleteTruck(id);
        response.sendRedirect("truck");
    }

    @RequestMapping(value="/validatyTruck", method= RequestMethod.GET)
    void showAllTruckWhoseValidatyIsEnds(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<Truck> listTruck = truckDAO.selectAllTrucksWhoseValidatyIsEnds();
        request.setAttribute("listTruck", listTruck);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/truck/truck-list-validaty.jsp");
        dispatcher.forward(request, response);
    }
}
