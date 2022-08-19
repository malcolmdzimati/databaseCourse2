package working;

import javax.persistence.ManyToOne;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class Practical implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Student student;
	private String pracName;
	private int totalMarks;
	
	@Id
	@GeneratedValue
	private long id;
	
	public Practical(Student s, String nm, int ttl) {
		student = s;
		pracName = nm;
		totalMarks = ttl;
	}
	
	public Student getStudent() {
		return student;
	}
	
	public void setStudent(Student s) {
		student = s;
	}
	
	@Override
	public String toString() {
		return "Practical Name: " +pracName+ " Total: "+totalMarks;
	}
}
