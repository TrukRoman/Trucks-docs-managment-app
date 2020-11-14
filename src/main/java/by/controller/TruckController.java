package by.controller;

import by.model.Truck;
import by.service.ITruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@Controller
public class TruckController {

    private ITruckService truckServiceImp;

    @Autowired
    public void setTruckService(ITruckService truckServiceImp) {
        this.truckServiceImp = truckServiceImp;
    }

    @RequestMapping(value="/truck", method= RequestMethod.GET)
    void listTrucks(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<Truck> listTrucks = truckServiceImp.selectAll();
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
        Truck existingTruck = truckServiceImp.selectById(id);
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
        truckServiceImp.insert(newTruck);
        response.sendRedirect("truck");
    }

    @RequestMapping(value="/updateTruck", method= RequestMethod.POST)
    void updateTruck(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String model = request.getParameter("model");
        String type = request.getParameter("type");
        Date yearOfProduction = Date.valueOf(request.getParameter("yearOfProduction"));
        String registerSign = request.getParameter("registerSign");
        String insuranceNumber = request.getParameter("insuranceNumber");
        Date insuranceValidity = Date.valueOf(request.getParameter("insuranceValidity"));
        Date technicalInspectionValidity = Date.valueOf(request.getParameter("technicalInspectionValidity"));

        Truck newTruck = new Truck(id, model, type, yearOfProduction, registerSign, insuranceNumber, insuranceValidity, technicalInspectionValidity);
        truckServiceImp.update(newTruck);
        response.sendRedirect("truck");
    }

    @RequestMapping(value="/deleteTruck", method= RequestMethod.GET)
    void deleteTruck(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        truckServiceImp.delete(id);
        response.sendRedirect("truck");
    }

    @RequestMapping(value="/validatyTruck", method= RequestMethod.GET)
    void showAllTruckWhoseValidatyIsEnds(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<Truck> listTruck = truckServiceImp.selectAllWhoseValidatyIsEnds();
        request.setAttribute("listTruck", listTruck);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/truck/truck-list-validaty.jsp");
        dispatcher.forward(request, response);
    }
}
