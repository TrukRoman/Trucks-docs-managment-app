package by.service.impl;

import by.dao.ITruckDAO;
import by.model.Truck;
import by.service.ITruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TruckServiceImp implements ITruckService {

    private ITruckDAO truckDAO;

    @Autowired
    @Transactional
    public void setTruckService(ITruckDAO truckDAO) {
        this.truckDAO = truckDAO;
    }

    @Override
    @Transactional
    public void insert(Truck truck) {
        truckDAO.insert(truck);
    }

    @Override
    @Transactional
    public Truck selectById(int id) {
        return truckDAO.selectById(id);
    }

    @Override
    @Transactional
    public List<Truck> selectAll() {
        return truckDAO.selectAll();
    }

    @Override
    @Transactional
    public void delete(int id) {
        truckDAO.delete(id);
    }

    @Override
    @Transactional
    public void update(Truck truck) {
        truckDAO.update(truck);
    }

    @Override
    @Transactional
    public List<Truck> selectAllWhoseValidatyIsEnds() {
        return truckDAO.selectAllWhoseValidatyIsEnds();
    }
}
