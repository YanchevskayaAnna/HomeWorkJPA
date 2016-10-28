package dao.hibernate;

import dao.intefaces.TeacherDAO;
import model.Student;
import model.Subject;
import model.Teacher;
import org.apache.log4j.Logger;

import javax.persistence.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAOHibernateImpl implements TeacherDAO {

    private EntityManagerFactory factory;
    private static final Logger LOGGER = Logger.getLogger(TeacherDAOHibernateImpl.class);

    public TeacherDAOHibernateImpl(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<Teacher> getAll() {
        EntityManager em = factory.createEntityManager();
        TypedQuery<Teacher> namedQuery = em.createNamedQuery("Teacher.getAll", Teacher.class);
        return namedQuery.getResultList();
    }

    @Override
    public Teacher getEntityById(Integer id) {
        EntityManager em = factory.createEntityManager();
        return em.find(Teacher.class, id);
    }

    @Override
    public boolean update(Teacher entity) {
        LOGGER.info("update group");
        EntityManager em = factory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(entity);
            transaction.commit();
            LOGGER.info("group was saved");
        } catch (Exception e) {
            LOGGER.error("group was not saved", e);
            transaction.rollback();
        } finally {
            em.close();
        }

        return true;
    }

    @Override
    public boolean create(Teacher entity) {

        LOGGER.info("create new teacher");
        EntityManager em = factory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(entity);
            transaction.commit();
            LOGGER.info("teacher was saved");
        } catch (Exception e) {
            LOGGER.error("teacher was not saved", e);
            transaction.rollback();
        } finally {
            em.close();
        }

        return true;
    }

    @Override
    public boolean delete(Teacher entity) {
        LOGGER.info("delete teacher");

        EntityManager em = factory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        Teacher teacher = em.find(Teacher.class, entity.getId());

        try {
            transaction.begin();
            em.remove(teacher);
            transaction.commit();
            LOGGER.info("teacher was saved");
        } catch (Exception e) {
            LOGGER.error("teacher was not saved", e);
            transaction.rollback();
        } finally {
            em.close();
        }
        return true;
    }
}
