package working;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class DatabaseManager {
	 /**
     * @param args the command line arguments
     */
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("db/studentDatabase.odb");
    private EntityManager em = emf.createEntityManager();
	
	public DatabaseManager() {
		/*emf = Persistence.createEntityManagerFactory("db/studentDatabase.odb");
	    em = emf.createEntityManager();*/
	}
	
	public void close() {
		em.close();
	    emf.close();
	}
	
	public void clearDB() {
		TypedQuery < Student > query = em.createQuery("SELECT b FROM Student b", Student.class);
	    List < Student > results = query.getResultList();
	
	    for (Student bb: results) {
	    	deleteStudent(bb.getStudentNumber());
	    }
	}
	
	public String displayAllStudents() {
		String res ="";
	    TypedQuery < Student > query = em.createQuery("SELECT b FROM Student b", Student.class);
	    List < Student > results = query.getResultList();
	
	    for (Student bb: results) {
	        System.out.println(bb);
	        res+=bb.toString()+"\n";
	    }
	    return res;
	}
	
	public void addStudent(String name, String surname, String degree, long sn) {
	    em.getTransaction().begin();
	    Student b1 = new Student(name, surname, degree, sn);
	    em.persist(b1);
	    em.getTransaction().commit();
	    em.close();
	    
	    emf = Persistence.createEntityManagerFactory("db/studentDatabase.odb");
	    em = emf.createEntityManager();
	}
	
	public void deleteStudent(long sn) {
		Student student = em.find(Student.class, sn);
		
		em.getTransaction().begin();
		em.remove(student);
		em.getTransaction().commit();
		em.close();
		
		emf = Persistence.createEntityManagerFactory("db/studentDatabase.odb");
	    em = emf.createEntityManager();
	}
	
	public String findStudent(long sn) {
		Student student = em.find(Student.class, sn);
		System.out.println(student);
		if(student==null) {
			return "null";
		}
		return student.toString();
	}
	
	public Student findStudentO(long sn) {
		Student student = em.find(Student.class, sn);
		System.out.println(student);
		return student;
	}
	
	public void update(long osn, String name, String surname, String degree) {
		em.getTransaction().begin();
		Student student = em.find(Student.class, osn);
	    student.setName(name);
	    student.setDegree(degree);
	    student.setSurname(surname);
	    em.getTransaction().commit();
	    em.close();
	    
	    emf = Persistence.createEntityManagerFactory("db/studentDatabase.odb");
	    em = emf.createEntityManager();
	}
}
