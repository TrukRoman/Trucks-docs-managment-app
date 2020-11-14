package by.dao.impl;

import by.dao.IDriverDAO;
import by.model.Driver;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DriverDAOImpl implements IDriverDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insert(Driver driver) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(driver);
    }

    @Override
    public Driver selectById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Driver.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Driver> selectAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Driver").list();
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Driver driver = session.byId(Driver.class).load(id);
        session.delete(driver);
    }

    @Override
    public void update(Driver driver) {
        Session session = sessionFactory.getCurrentSession();
        session.update(driver);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Driver> selectAllWhoseValidatyIsEnds() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Driver where dlvalidity - current_date < 30 or mcvalidaty - current_date < 30").list();
    }
}
