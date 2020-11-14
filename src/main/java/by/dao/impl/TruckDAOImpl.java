package by.dao.impl;

import by.dao.ITruckDAO;
import by.model.Truck;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TruckDAOImpl implements ITruckDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insert(Truck truck) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(truck);
    }

    @Override
    public Truck selectById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Truck.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Truck> selectAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Truck").list();
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Truck truck = session.byId(Truck.class).load(id);
        session.delete(truck);
    }

    @Override
    public void update(Truck truck) {
        Session session = sessionFactory.getCurrentSession();
        session.update(truck);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Truck> selectAllWhoseValidatyIsEnds() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Truck where  insuranceValidity - current_date < 30 or technicalInspectionValidity - current_date < 30").list();
    }
}
