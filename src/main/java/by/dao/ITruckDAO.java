package by.dao;

import by.model.Truck;

import java.util.List;

public interface ITruckDAO {
    void insert(Truck truck);
    Truck selectById(int id);
    List<Truck> selectAll();
    void delete(int id);
    void update(Truck truck);
    List<Truck> selectAllWhoseValidatyIsEnds();
}
