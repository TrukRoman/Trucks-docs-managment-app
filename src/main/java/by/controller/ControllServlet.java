package by.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/")
public class ControllServlet extends HttpServlet {
    TruckOperations truckOperations = new TruckOperations();
    DriverOperations driverOperations =new DriverOperations();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getRequestURI();

        try {
            switch (action) {
                case "/newtruck":
                    truckOperations.showNewForm(request, response);
                    break;
                case "/insertTruck":
                    truckOperations.insertTruck(request, response);
                    break;
                case "/deleteTruck":
                    truckOperations.deleteTruck(request, response);
                    break;
                case "/editTruck":
                    truckOperations.showEditForm(request, response);
                    break;
                case "/updateTruck":
                    truckOperations.updateTruck(request, response);
                    break;
                case "/truck":
                    truckOperations.listTrucks(request, response);
                    break;
                case "/validatyTruck":
                    truckOperations.showAllTruckWhoseValidatyIsEnds(request, response);
                    break;
                case "/newdriver":
                    driverOperations.showNewForm(request, response);
                    break;
                case "/insertDriver":
                    driverOperations.insertDriver(request, response);
                    break;
                case "/deleteDriver":
                    driverOperations.deleteDriver(request, response);
                    break;
                case "/editDriver":
                    driverOperations.showEditForm(request, response);
                    break;
                case "/updateDriver":
                    driverOperations.updateDriver(request, response);
                    break;
                case "/validatyDriver":
                    driverOperations.showAllDriverWhoseValidatyIsEnds(request, response);
                    break;
                default:
                    driverOperations.listDriver(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
}
