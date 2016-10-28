package dao.hibernate;

import dao.intefaces.SubjectDAO;
import model.Student;
import model.Subject;
import org.apache.log4j.Logger;

import javax.persistence.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 08.10.2016.
 */
public class SubjectDAOHibernateImpl implements SubjectDAO {

    private EntityManagerFactory factory;
    private static final Logger LOGGER = Logger.getLogger(SubjectDAOHibernateImpl.class);

    public SubjectDAOHibernateImpl(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<Subject> getAll() {
        EntityManager em = factory.createEntityManager();
        TypedQuery<Subject> namedQuery = em.createNamedQuery("Subject.getAll", Subject.class);
        return namedQuery.getResultList();
    }

    @Override
    public Subject getEntityById(Integer id) {

        EntityManager em = factory.createEntityManager();
        return em.find(Subject.class, id);
    }

    @Override
    public boolean update(Subject entity) {
        LOGGER.info("subject group");
        EntityManager em = factory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(entity);
            transaction.commit();
            LOGGER.info("subject was saved");
        } catch (Exception e) {
            LOGGER.error("subject was not saved", e);
            transaction.rollback();
        } finally {
            em.close();
        }

        return true;
    }

    @Override
    public boolean create(Subject entity) {

        LOGGER.info("create new subject");
        EntityManager em = factory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(entity);
            transaction.commit();
            LOGGER.info("subject was saved");
        } catch (Exception e) {
            LOGGER.error("subject was not saved", e);
            transaction.rollback();
        } finally {
            em.close();
        }

        return true;
    }

    @Override
    public boolean delete(Subject entity) {
        LOGGER.info("delete subject");
        EntityManager em = factory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        Subject subject = em.find(Subject.class, entity.getId());

        try {
            transaction.begin();
            em.remove(subject);
            transaction.commit();
            LOGGER.info("subject was saved");
        } catch (Exception e) {
            LOGGER.error("subject was not saved", e);
            transaction.rollback();
        } finally {
            em.close();
        }
        return true;
    }

}
