package by.controller;

import by.dao.TruckDAO;
import by.model.Truck;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/truck")
public class TruckServlet extends HttpServlet {
    private final TruckDAO truckDAO = new TruckDAO();

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
                case "/newtruck":
                    showNewForm(request, response);
                    break;
                case "/insertTruck":
                    insertTruck(request, response);
                    break;
                case "/deleteTruck":
                    deleteTruck(request, response);
                    break;
                case "/editTruck":
                    showEditForm(request, response);
                    break;
                case "/updateTruck":
                    updateTruck(request, response);
                    break;
                case "/validatyTruck":
                    showAllTruckWhoseValidatyIsEnds(request, response);
                    break;
                default:
                    listTrucks(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listTrucks(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<Truck> listTrucks = truckDAO.selectAllTrucks();
        request.setAttribute("listTrucks", listTrucks);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/truck/truck-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/truck/truck-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Truck existingTruck = truckDAO.selectTruck(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/truck/truck-form.jsp");
        request.setAttribute("truck", existingTruck);
        dispatcher.forward(request, response);
    }

    private void insertTruck(HttpServletRequest request, HttpServletResponse response)
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
        response.sendRedirect("list");
    }

    private void updateTruck(HttpServletRequest request, HttpServletResponse response)
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
        response.sendRedirect("list");
    }

    private void deleteTruck(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        truckDAO.deleteTruck(id);
        response.sendRedirect("list");
    }

    private void showAllTruckWhoseValidatyIsEnds(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<Truck> listTruck = truckDAO.selectAllTrucksWhoseValidatyIsEnds();
        request.setAttribute("listTruck", listTruck);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/truck/truck-list-validaty.jsp");
        dispatcher.forward(request, response);
    }
}
