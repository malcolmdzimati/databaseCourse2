package working;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class Student implements Serializable {
    //The serialVersionUID is a universal version identifier for a Serializable class
    private static final long serialVersionUID = 1L;
    //This annotation specifies the primary key of the entity

    @Id
    private String name;
    private long studentNumber;
    private String surname;
    Set<Practical> practicals = new HashSet<>();

    public Student() {

    }
    public Student(String n, String sn, long snm) {
        surname = sn;
        this.name = n;
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
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@JoinColumn(name="childFK")
    public Set<Practical> getPracticals() {
    	return practicals;
    }
    
    public void setPracticals(Set<Practical> pracs) {
        this.practicals = pracs;
    }

    @Override
    public String toString() {
    	String prac = "\t";
    	if(practicals==null) {
    		prac= "Student has no pracs";
    	}else {
	    	Iterator<Practical> itr = practicals.iterator();
	    	while(itr.hasNext()) {
	    		prac+=itr.next()+"\n\t";
	    	}
    	}
    	
        return String.format("Name: %s, \nSurname: %s, \nStudentNumber: %s \n Pracs:\n %s",
            this.name, this.surname, Long.toString(this.studentNumber), prac);
    }
}
