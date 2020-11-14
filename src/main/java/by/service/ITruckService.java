package by.service;

import by.model.Truck;

import java.util.List;

public interface ITruckService {
    void insert(Truck truck);
    Truck selectById(int id);
    List<Truck> selectAll();
    void delete(int id);
    void update(Truck truck);
    List<Truck> selectAllWhoseValidatyIsEnds();
}
