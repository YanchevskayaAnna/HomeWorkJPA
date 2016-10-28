package dao.hibernate;

import dao.intefaces.GroupDAO;
import model.Group;
import model.Mark;
import model.Student;
import model.Subject;
import org.apache.log4j.Logger;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.transform.Transformers;

import javax.persistence.*;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupDAOHibernateImpl implements GroupDAO {

    private EntityManagerFactory factory;
    private static final Logger LOGGER = Logger.getLogger(GroupDAOHibernateImpl.class);

    public GroupDAOHibernateImpl(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<Group> getAll() {
        EntityManager em = factory.createEntityManager();
        TypedQuery<Group> namedQuery = em.createNamedQuery("Group.getAll", Group.class);
        return namedQuery.getResultList();
    }

    @Override
    public Group getEntityById(Integer id) {
        EntityManager em = factory.createEntityManager();
        return em.find(Group.class, id);
    }

    @Override
    public boolean update(Group entity) {

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
    public boolean create(Group entity) {

        LOGGER.info("create new group");

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
    public boolean delete(Group entity) {

        LOGGER.info("delete group");
        EntityManager em = factory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        Group group = em.find(Group.class, entity.getId());

        try {
            transaction.begin();
            em.remove(group);
            transaction.commit();
            LOGGER.info("group was deleted");
        } catch (Exception e) {
            LOGGER.error("group was not deleted", e);
            transaction.rollback();
        } finally {
            em.close();
        }
        return true;
    }

    public List<Group> getAllGroupsWithSubject(Subject subject, int start, int end) {

        EntityManager em = factory.createEntityManager();
        TypedQuery<Group> query = em.createQuery("SELECT n FROM Group n join n.subjectList s WHERE s.name = :subjectName", Group.class);
        query.setParameter("subjectName", subject.getName());

        return query.getResultList();

    }

    @Override
    public int getAverageRatingGroup(Group group) {

        EntityManager em = factory.createEntityManager();
        TypedQuery<Double> query = em.createQuery("SELECT AVG(m.value) FROM Student n join n.markList m join n.group s WHERE s.name = :groupName", Double.class);
        query.setParameter("groupName", group.getName());

        return query.getSingleResult().intValue();
    }

    @Override
    public int getAverageRating(Subject subject) {

        EntityManager em = factory.createEntityManager();
        TypedQuery<Double> query = em.createQuery("SELECT AVG(s.value) FROM Subject n join n.markList s WHERE n.name = :subjectName", Double.class);
        query.setParameter("subjectName", subject.getName());
        return query.getSingleResult().intValue();

    }

    public Map<Student, Integer> getAverageRating() {

        EntityManager em = factory.createEntityManager();
        String queryString = "SELECT m.student,  AVG(m.value) FROM Mark m join m.subject s join m.student st join st.group g GROUP BY m.student";
        TypedQuery<Object[]> query = em.createQuery(queryString, Object[].class);
        List<Object[]> resultList = query.getResultList();

        HashMap<Student, Integer> map = new HashMap<Student, Integer>();

        for (int i = 0; i < resultList.size(); i++) {
            map.put((Student) resultList.get(i)[0], ((Double) resultList.get(i)[1]).intValue());
        }

        return map;

    }

    public int getAverageRating(Subject subject, Group group) {

        EntityManager em = factory.createEntityManager();
        TypedQuery<Double> query = em.createQuery("SELECT AVG(m.value) FROM Mark m join m.subject s join m.student st join st.group g WHERE s.name = :subjectName and g.name = :groupName", Double.class);
        query.setParameter("groupName", group.getName());
        query.setParameter("subjectName", subject.getName());

        return query.getSingleResult().intValue();

    }
}
