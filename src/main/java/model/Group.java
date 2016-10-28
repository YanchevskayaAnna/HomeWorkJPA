package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "groups")
@NamedQuery(name = "Group.getAll", query = "SELECT c from Group c")
public class Group {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private List<Student> studentList;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "learning",
            joinColumns = {@JoinColumn(name = "group_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "subject_id", referencedColumnName = "id")})
    private List<Subject> subjectList;

    public Group() {
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

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group)) return false;

        Group group = (Group) o;

        if (name != null ? !name.equals(group.name) : group.name != null) return false;

        return true;
    }
}
