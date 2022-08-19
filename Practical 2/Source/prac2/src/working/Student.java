package working;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Student implements Serializable {
    //The serialVersionUID is a universal version identifier for a Serializable class
    private static final long serialVersionUID = 1L;
    //This annotation specifies the primary key of the entity

    @Id
    private long studentNumber;
    private String name;
    private String surname;
    private String degree;

    public Student() {

    }
    public Student(String n, String sn, String d, long snm) {
        this.surname = sn;
        this.name = n;
        this.degree = d;
        this.studentNumber = snm;
    }

    public Long getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(Long id) {
        this.studentNumber = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        this.name = n;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String a) {
        this.surname = a;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String n) {
        this.degree = n;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s, %s, %s)",
            this.name, this.surname, this.degree, Long.toString(this.studentNumber));
    }
}
