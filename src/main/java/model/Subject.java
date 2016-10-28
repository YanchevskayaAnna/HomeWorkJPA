package model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "subjects")
@NamedQuery(name = "Subject.getAll", query = "SELECT c from Subject c")
public class Subject {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @Column
    private String description;

    @OneToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Teacher teacher;

    @ManyToMany(mappedBy = "subjectList",fetch = FetchType.LAZY)
    private List<Group> groupList;

    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY)
    private List<Mark> markList;

    public Subject() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }

    public List<Mark> getMarkList() {
        return markList;
    }

    public void setMarkList(List<Mark> markList) {
        this.markList = markList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subject)) return false;

        Subject subject = (Subject) o;

//        if (id != subject.id) return false;
        if (name.compareTo(subject.name)== 0) return true;
        return  false;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", teacher=" + teacher +
                '}';
    }
}
