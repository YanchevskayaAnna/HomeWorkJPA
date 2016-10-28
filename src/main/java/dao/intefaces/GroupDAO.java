package dao.intefaces;

import dao.intefaces.AbstractDAO;
import model.Group;
import model.Student;
import model.Subject;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface GroupDAO extends AbstractDAO<Group> {

    public List<Group> getAllGroupsWithSubject(Subject subject, int start, int end);

    public Map<Student, Integer> getAverageRating();

    public int getAverageRatingGroup(Group group);

    public int getAverageRating(Subject subject);

    public int getAverageRating(Subject subject, Group group);

}
