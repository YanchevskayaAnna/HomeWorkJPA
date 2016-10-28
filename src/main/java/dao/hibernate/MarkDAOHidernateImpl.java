package dao.hibernate;

import dao.intefaces.MarkDAO;
import model.Group;
import model.Mark;
import model.Student;
import org.apache.log4j.Logger;

import javax.persistence.*;
import java.util.List;

public class MarkDAOHidernateImpl implements MarkDAO {

    private EntityManagerFactory factory;
    private static final Logger LOGGER = Logger.getLogger(MarkDAOHidernateImpl.class);

    public MarkDAOHidernateImpl(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<Mark> getAll() {
        EntityManager em = factory.createEntityManager();
        TypedQuery<Mark> namedQuery = em.createNamedQuery("Mark.getAll", Mark.class);
        return namedQuery.getResultList();
    }

    @Override
    public Mark getEntityById(Integer id) {
        EntityManager em = factory.createEntityManager();
        return em.find(Mark.class, id);
    }

    @Override
    public boolean update(Mark entity) {

        LOGGER.info("update mark");
        EntityManager em = factory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(entity);
            transaction.commit();
            LOGGER.info("mark was saved");
        } catch (Exception e) {
            LOGGER.error("mark was not saved", e);
            transaction.rollback();
        } finally {
            em.close();
        }

        return true;
    }

    @Override
    public boolean create(Mark entity) {

        LOGGER.info("create mark");

        EntityManager em = factory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(entity);
            transaction.commit();
            LOGGER.info("mark was saved");
        } catch (Exception e) {
            LOGGER.error("mark was not saved", e);
            transaction.rollback();
        } finally {
            em.close();
        }

        return true;
    }

    @Override
    public boolean delete(Mark entity) {

        LOGGER.info("delete mark");

        EntityManager em = factory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        Mark mark = em.find(Mark.class, entity.getId());

        try {
            transaction.begin();
            em.remove(mark);
            transaction.commit();
            LOGGER.info("mark was saved");
        } catch (Exception e) {
            LOGGER.error("mark was not saved", e);
            transaction.rollback();
        } finally {
            em.close();
        }
        return true;
    }
}
