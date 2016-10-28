package model;

import javax.persistence.*;


@Entity
@Table(name = "marks")
@NamedQuery(name = "Mark.getAll", query = "SELECT c from Mark c")
public class Mark {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "subject_id",
            referencedColumnName = "id")
    private Subject subject;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "student_id",
            referencedColumnName = "id")
     private Student student;

    @Column
    private int value;

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
