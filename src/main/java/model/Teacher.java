package model;


import javax.persistence.*;

@Entity
@Table(name = "teachers")
@NamedQuery(name = "Teacher.getAll", query = "SELECT c from Teacher c")
public class Teacher {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @Column
    private double exp;

    private int subjectId;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "teacher")
    private Subject subject;

    public Teacher() {
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

    public double getExp() {
        return exp;
    }

    public void setExp(double exp) {
        this.exp = exp;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override

    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", exp=" + exp +
                ", subjectId=" + subjectId +
                ", subject=" + subject +
                '}';
    }
}
