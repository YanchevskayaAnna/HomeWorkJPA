package model;

import javax.persistence.*;
import javax.persistence.criteria.Subquery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "students")
@NamedQuery(name = "Student.getAll", query = "SELECT c from Student c")
public class Student {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "group_id",
            referencedColumnName = "id")
    private Group group;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Mark> markList;

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", group=" + group +
                '}';
    }

    public List<Mark> getMarkList() {
        return markList;
    }

    public void setMarkList(List<Mark> markList) {
        this.markList = markList;
    }
}
