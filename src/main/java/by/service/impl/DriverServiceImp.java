package by.service.impl;

import by.dao.IDriverDAO;
import by.model.Driver;
import by.service.IDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DriverServiceImp implements IDriverService {

    private IDriverDAO driverDAO;

    @Autowired
    public void setDriverService(IDriverDAO driverDAO) {
        this.driverDAO = driverDAO;
    }

    @Override
    @Transactional
    public void insert(Driver driver) {
        driverDAO.insert(driver);
    }

    @Override
    @Transactional
    public Driver selectById(int id) {
        return driverDAO.selectById(id);
    }

    @Override
    @Transactional
    public List<Driver> selectAll() {
        return driverDAO.selectAll();
    }

    @Override
    @Transactional
    public void delete(int id) {
        driverDAO.delete(id);
    }

    @Override
    @Transactional
    public void update(Driver driver) {
        driverDAO.update(driver);
    }

    @Override
    @Transactional
    public List<Driver> selectAllWhoseValidatyIsEnds() {
        return driverDAO.selectAllWhoseValidatyIsEnds();
    }
}
