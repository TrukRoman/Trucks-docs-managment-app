package by.dao;

import by.model.Driver;

import java.util.List;

public interface IDriverDAO {
    void insert(Driver driver);
    Driver selectById(int id);
    List<Driver> selectAll();
    void delete(int id);
    void update(Driver driver);
    List<Driver> selectAllWhoseValidatyIsEnds();
}
