package by.dao;

import by.model.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table drivers in the database.
 */
public class DriverDAO {
    private String jdbcURL = "jdbc:postgresql://localhost:5432/demo";
    private String jdbcUsername = "postgres";
    private String jdbcPassword = "postgre";

    private static final String INSERT_DRIVERS_SQL = "INSERT INTO drivers" + "  (name, driverLicenceNum, category, dlvalidity, medicalCertificateNumber, mcvalidaty) VALUES " +
            " (?, ?, ?, ?, ?, ?);";

    private static final String SELECT_DRIVER_BY_ID = "select id,name,driverLicenceNum,category,dlvalidity, medicalCertificateNumber, mcvalidaty from drivers where id =?";
    private static final String SELECT_ALL_DRIVERS = "select * from drivers";
    private static final String DELETE_DRIVERS_SQL = "delete from drivers where id = ?;";
    private static final String UPDATE_DRIVERS_SQL = "update drivers set name = ?,driverLicenceNum= ?, category =?, dlvalidity =?, medicalCertificateNumber =?, mcvalidaty =? where id = ?;";
    private static final String SELECT_ALL_DRIVERS_WHOSE_VALIDATY_IS_ENDS = "select * from drivers where dlvalidity - current_date < 30 or mcvalidaty - current_date < 30;";

    public DriverDAO() {
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

    public void insertDriver(Driver driver) {
        System.out.println(INSERT_DRIVERS_SQL);
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DRIVERS_SQL)) {
            preparedStatement.setString(1, driver.getName());
            preparedStatement.setString(2, driver.getDriverLicenceNum());
            preparedStatement.setString(3, driver.getCategory());
            preparedStatement.setDate(4, driver.getDlvalidity());
            preparedStatement.setString(5, driver.getMedicalCertificateNumber());
            preparedStatement.setDate(6, driver.getMcvalidaty());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Driver selectDriver(int id) {
        Driver driver = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DRIVER_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String name = rs.getString("name");
                String driverLicenceNum = rs.getString("driverLicenceNum");
                String category = rs.getString("category");
                Date dlvalidity = rs.getDate("dlvalidity");
                String medicalCertificateNumber = rs.getString("medicalCertificateNumber");
                Date mcvalidaty = rs.getDate("mcvalidaty");

                driver = new Driver(id, name, driverLicenceNum, category, dlvalidity, medicalCertificateNumber, mcvalidaty);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return driver;
    }

    public List<Driver> selectAllDriver() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Driver> driver = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DRIVERS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String driverLicenceNum = rs.getString("driverLicenceNum");
                String category = rs.getString("category");
                Date dlvalidity = rs.getDate("dlvalidity");
                String medicalCertificateNumber = rs.getString("medicalCertificateNumber");
                Date mcvalidaty = rs.getDate("mcvalidaty");

                driver.add(new Driver(id, name, driverLicenceNum, category, dlvalidity, medicalCertificateNumber, mcvalidaty));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return driver;
    }

    public boolean deleteDriver(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_DRIVERS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateDriver(Driver driver) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_DRIVERS_SQL);) {
            statement.setString(1, driver.getName());
            statement.setString(2, driver.getDriverLicenceNum());
            statement.setString(3, driver.getCategory());
            statement.setDate(4, driver.getDlvalidity());
            statement.setString(5, driver.getMedicalCertificateNumber());
            statement.setDate(6, driver.getMcvalidaty());
            statement.setInt(7, driver.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public List<Driver> selectAllDriverWhoseValidatyIsEnds() {
        List<Driver> driver = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DRIVERS_WHOSE_VALIDATY_IS_ENDS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String driverLicenceNum = rs.getString("driverLicenceNum");
                String category = rs.getString("category");
                Date dlvalidity = rs.getDate("dlvalidity");
                String medicalCertificateNumber = rs.getString("medicalCertificateNumber");
                Date mcvalidaty = rs.getDate("mcvalidaty");

                driver.add(new Driver(id, name, driverLicenceNum, category, dlvalidity, medicalCertificateNumber, mcvalidaty));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return driver;
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
