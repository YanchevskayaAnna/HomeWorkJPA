package dao.hibernate;

import dao.intefaces.StudentDAO;
import model.Mark;
import model.Student;
import org.apache.log4j.Logger;

import javax.persistence.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOHibernateImpl implements StudentDAO {

    private EntityManagerFactory factory;
    private static final Logger LOGGER = Logger.getLogger(StudentDAOHibernateImpl.class);

    public StudentDAOHibernateImpl(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<Student> getAll() {
        EntityManager em = factory.createEntityManager();

        TypedQuery<Student> namedQuery = em.createNamedQuery("Student.getAll", Student.class);
        return namedQuery.getResultList();
    }

    @Override
    public Student getEntityById(Integer id) {
        EntityManager em = factory.createEntityManager();
        return em.find(Student.class, id);
    }

    @Override
    public boolean update(Student entity) {
        LOGGER.info("update student");
        EntityManager em = factory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(entity);
            transaction.commit();
            LOGGER.info("student was saved");
        } catch (Exception e) {
            LOGGER.error("student was not saved", e);
            transaction.rollback();
        } finally {
            em.close();
        }

        return true;
    }

    @Override
    public boolean create(Student entity) {

        LOGGER.info("create new student");
        EntityManager em = factory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(entity);
            transaction.commit();
            LOGGER.info("student was saved");
        } catch (Exception e) {
            LOGGER.error("student was not saved", e);
            transaction.rollback();
        } finally {
            em.close();
        }

        return true;
    }

    @Override
    public boolean delete(Student entity) {
        LOGGER.info("delete student");
        EntityManager em = factory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        Student student = em.find(Student.class, entity.getId());

        try {
            transaction.begin();
            em.remove(student);
            transaction.commit();
            LOGGER.info("student was saved");
        } catch (Exception e) {
            LOGGER.error("student was not saved", e);
            transaction.rollback();
        } finally {
            em.close();
        }
        return true;
    }

}
