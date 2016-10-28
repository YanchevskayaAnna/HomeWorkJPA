package hebirnate;

import dao.hibernate.*;
import model.Group;
import model.Mark;
import model.Student;
import model.Subject;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import service.AdminController;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestGroupController_Hibernate {

    private static AdminController groupController;
    private static EntityManagerFactory emFactory;

    @BeforeClass
    public static void beforeClass() throws IOException {

        emFactory = Persistence.createEntityManagerFactory("hibernate-unit");

        groupController = new AdminController(new GroupDAOHibernateImpl(emFactory), new SubjectDAOHibernateImpl(emFactory), new TeacherDAOHibernateImpl(emFactory), new StudentDAOHibernateImpl(emFactory), new MarkDAOHidernateImpl(emFactory));

        //SUBJECTS
        List<Subject> subjects = createSubjects();

        //GROUPS
        List<Group> groups = createGroups(subjects);

        //STUDENTS
        List<Student> students = createStudents(groups);

        //MARKS
        List<Mark> marks = createMarks(students, subjects);

    }

    private static List<Mark> createMarks(List<Student> students, List<Subject> subjects) {

        Mark mark1 = new Mark();
        mark1.setValue(5);
        mark1.setStudent(students.get(0));
        mark1.setSubject(subjects.get(0));
        groupController.createMark(mark1);

        Mark mark2 = new Mark();
        mark2.setValue(2);
        mark2.setStudent(students.get(0));
        mark2.setSubject(subjects.get(1));
        groupController.createMark(mark2);

        Mark mark3 = new Mark();
        mark3.setValue(5);
        mark3.setStudent(students.get(1));
        mark3.setSubject(subjects.get(0));
        groupController.createMark(mark3);

        Mark mark4 = new Mark();
        mark4.setValue(20);
        mark4.setStudent(students.get(1));
        mark4.setSubject(subjects.get(1));
        groupController.createMark(mark4);

        Mark mark5 = new Mark();
        mark5.setValue(20);
        mark5.setStudent(students.get(2));
        mark5.setSubject(subjects.get(0));
        groupController.createMark(mark5);

        Mark mark6 = new Mark();
        mark6.setValue(20);
        mark6.setStudent(students.get(2));
        mark6.setSubject(subjects.get(1));
        groupController.createMark(mark6);

        Mark mark7 = new Mark();
        mark7.setValue(10);
        mark7.setStudent(students.get(3));
        mark7.setSubject(subjects.get(0));
        groupController.createMark(mark7);

        Mark mark8 = new Mark();
        mark8.setValue(10);
        mark8.setStudent(students.get(3));
        mark8.setSubject(subjects.get(1));
        groupController.createMark(mark8);

        return groupController.getAllMarks();
    }

    private static List<Student> createStudents(List<Group> groups) {

        Student student1 = new Student();
        student1.setName("Anna");
        student1.setGroup(groups.get(0));
        groupController.createStudent(student1);

        Student student2 = new Student();
        student2.setName("Vasya");
        student2.setGroup(groups.get(0));
        groupController.createStudent(student2);

        Student student3 = new Student();
        student3.setName("Max");
        student3.setGroup(groups.get(1));
        groupController.createStudent(student3);

        Student student4 = new Student();
        student4.setName("Dima");
        student4.setGroup(groups.get(1));
        groupController.createStudent(student4);

        return groupController.getAllStudents();

    }

    private static List<Group> createGroups(List<Subject> subjects) {

        Group group1 = new Group();
        group1.setName("ACP14");
        group1.setSubjectList(subjects);
        groupController.createGroup(group1);

        Group group2 = new Group();
        group2.setName("ACP11");
        group2.setSubjectList(subjects);
        groupController.createGroup(group2);

        return  groupController.getAllGroups();

    }

    private static List<Subject> createSubjects() {

        Subject subject1 = new Subject();
        subject1.setName("java");
        groupController.createSubject(subject1);

        Subject subject2 = new Subject();
        subject2.setName("css");
        groupController.createSubject(subject2);

        Subject subject3 = new Subject();
        subject2.setName("ios");
        groupController.createSubject(subject2);

        return groupController.getAllSubjects();
    }

    @AfterClass
    public static void afterClass()  {
        emFactory.close();
    }

    @Test
    public void getAllGroups() {
        List<Group> groupList = groupController.getAllGroups();
        Assert.assertNotNull(groupList);
    }

    @Test
    public void getGroupByID() {
        Group group = groupController.getGroupById(1);
        Assert.assertNotNull(group);
    }

    @Test
    public void updateGroupsInfo() {

        Group groupBeforeUpdate = groupController.getGroupById(1);

        String newName = "Middles";
        String oldName = groupBeforeUpdate.getName();
        groupBeforeUpdate.setName(newName);

        groupController.updateGroup(groupBeforeUpdate);
        Group groupAfterUpd = groupController.getGroupById(1);
        Assert.assertEquals(groupBeforeUpdate.getId(), groupAfterUpd.getId());
        Assert.assertEquals(groupBeforeUpdate.getName(), groupAfterUpd.getName());

        groupBeforeUpdate.setName(oldName);
        groupController.updateGroup(groupBeforeUpdate);
    }

    @Test
    public void deleteGroup() {

        Group testGroup = new Group();
        testGroup.setName("test group");
        groupController.createGroup(testGroup);

        Group deleteGroup = groupController.getGroupById(3);
        groupController.deleteGroup(deleteGroup);

        Assert.assertFalse(groupController.getAllGroups().contains(testGroup));

    }

    @Test
    public void createGroup() {

        Group group = new Group();
        group.setName("Test2390");

        Assert.assertTrue(groupController.createGroup(group));
        groupController.deleteGroup(group);
    }

    @Test
    public void getAllGroupsFromResultSet() {
        Subject subjectJava = new Subject();
        subjectJava.setId(1);
        subjectJava.setName("java");
        List<Group> groupList = groupController.getAllGroupsWithSubject(subjectJava, 0, 20);
        Assert.assertNotNull(groupList);
    }

    @Test
    public void getAverageMarks() {
        Map<Student, Integer> groupList = groupController.getAverageRating();
        Assert.assertEquals(4, groupList.size());
    }

    @Test
    public void getAverageRatingSubject() {

        Subject subjectJava = new Subject();
        subjectJava.setName("java");
        Subject subjectCSS = new Subject();
        subjectCSS.setName("css");
        Assert.assertEquals(10, groupController.getAverageRating(subjectJava));
        Assert.assertEquals(13, groupController.getAverageRating(subjectCSS));

    }

    @Test
    public void getAverageRatingGroup() {

        Group group1 = new Group();
        group1.setName("ACP14");

        Group group2 = new Group();
        group2.setName("ACP11");

        Assert.assertEquals(8, groupController.getAverageRatingGroup(group1));
        Assert.assertEquals(15, groupController.getAverageRatingGroup(group2));
    }

    @Test
    public void getAverageRatingSubjectGroup() {

        Subject subjectJava = new Subject();
        subjectJava.setName("java");

        Group groupACP14 = new Group();
        groupACP14.setName("ACP14");

        Assert.assertEquals(5,  groupController.getAverageRating(subjectJava, groupACP14));
    }


}
