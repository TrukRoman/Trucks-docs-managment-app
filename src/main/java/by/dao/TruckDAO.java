package by.dao;

import by.model.Truck;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TruckDAO {
    private String jdbcURL = "jdbc:postgresql://localhost:5432/demo";
    private String jdbcUsername = "postgres";
    private String jdbcPassword = "postgre";

    private static final String INSERT_TRUCKS_SQL = "INSERT INTO trucks" + "  (model, type, yearOfProduction, registerSign, insuranceNumber, insuranceValidity, technicalInspectionValidity) VALUES " +
            " (?, ?, ?, ?, ?, ?, ?);";
    private static final String SELECT_TRUCKS_BY_ID = "select id,model, type, yearOfProduction, registerSign, insuranceNumber, insuranceValidity, technicalInspectionValidity from trucks where id =?";
    private static final String SELECT_ALL_TRUCKS = "select * from trucks";
    private static final String DELETE_TRUCKS_SQL = "delete from trucks where id = ?;";
    private static final String UPDATE_TRUCKS_SQL = "update trucks set model = ?,type= ?, yearOfProduction =?, registerSign =?, insuranceNumber =?, insuranceValidity =?, technicalInspectionValidity =? where id = ?;";
    private static final String SELECT_ALL_TRUCKS_WHOSE_VALIDATY_IS_ENDS = "select * from trucks where insuranceValidity - current_date < 30 or technicalInspectionValidity - current_date < 30;";

    public TruckDAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertTruck(Truck truck) {
        System.out.println(INSERT_TRUCKS_SQL);
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TRUCKS_SQL)) {
            preparedStatement.setString(1, truck.getModel());
            preparedStatement.setString(2, truck.getType());
            preparedStatement.setDate(3, truck.getYearOfProduction());
            preparedStatement.setString(4, truck.getRegisterSign());
            preparedStatement.setString(5, truck.getInsuranceNumber());
            preparedStatement.setDate(6, truck.getInsuranceValidity());
            preparedStatement.setDate(7, truck.getTechnicalInspectionValidity());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Truck selectTruck(int id) {
        Truck truck = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TRUCKS_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String model = rs.getString("model");
                String type = rs.getString("type");
                Date yearOfProduction = rs.getDate("yearOfProduction");
                String registerSign = rs.getString("registerSign");
                String insuranceNumber = rs.getString("insuranceNumber");
                Date insuranceValidity = rs.getDate("insuranceValidity");
                Date technicalInspectionValidity = rs.getDate("technicalInspectionValidity");

                truck = new Truck(id, model, type, yearOfProduction, registerSign, insuranceNumber, insuranceValidity, technicalInspectionValidity);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return truck;
    }

    public List<Truck> selectAllTrucks() {
        List<Truck> trucks = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TRUCKS);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String model = rs.getString("model");
                String type = rs.getString("type");
                Date yearOfProduction = rs.getDate("yearOfProduction");
                String registerSign = rs.getString("registerSign");
                String insuranceNumber = rs.getString("insuranceNumber");
                Date insuranceValidity = rs.getDate("insuranceValidity");
                Date technicalInspectionValidity = rs.getDate("technicalInspectionValidity");

                trucks.add(new Truck(id, model, type, yearOfProduction, registerSign, insuranceNumber, insuranceValidity, technicalInspectionValidity));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return trucks;
    }

    public boolean deleteTruck(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_TRUCKS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateTruck(Truck truck) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_TRUCKS_SQL);) {
            statement.setString(1, truck.getModel());
            statement.setString(2, truck.getType());
            statement.setDate(3, truck.getYearOfProduction());
            statement.setString(4, truck.getRegisterSign());
            statement.setString(5, truck.getInsuranceNumber());
            statement.setDate(6, truck.getInsuranceValidity());
            statement.setDate(7, truck.getTechnicalInspectionValidity());
            statement.setInt(8, truck.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public List<Truck> selectAllTrucksWhoseValidatyIsEnds() {
        List<Truck> trucks = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TRUCKS_WHOSE_VALIDATY_IS_ENDS);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String model = rs.getString("model");
                String type = rs.getString("type");
                Date yearOfProduction = rs.getDate("yearOfProduction");
                String registerSign = rs.getString("registerSign");
                String insuranceNumber = rs.getString("insuranceNumber");
                Date insuranceValidity = rs.getDate("insuranceValidity");
                Date technicalInspectionValidity = rs.getDate("technicalInspectionValidity");

                trucks.add(new Truck(id, model, type, yearOfProduction, registerSign, insuranceNumber, insuranceValidity, technicalInspectionValidity));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return trucks;
    }


    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
