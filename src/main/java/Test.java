import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

//import com.lz.hibernateTest.student;



public class Test {
	
	private Configuration conf;
	private SessionFactory factory;
	public Session ses;
	private Transaction tx;
	
	public Session getSes() {
		return ses;
	}

	public void setSes(Session ses) {
		this.ses = ses;
	}

	public Transaction getTx() {
		return tx;
	}

	public void setTx(Transaction tx) {
		this.tx = tx;
	}

	public void startHibernate() {
		conf =  new Configuration();
    	conf=conf.configure("hibernate.cfg.xml").addAnnotatedClass(StudentTest.class);
    	factory =conf.buildSessionFactory();
    	ses=factory.openSession();
    	tx= ses.beginTransaction();
	}
	
	public void saveSession(Session ses, StudentTest student) {
		ses.save(student);
	}
	
	public void endTransaction(Transaction tx) {
		assert(tx.isActive()) : "Start the tx first";
		tx.commit();
	}
	public StudentTest getBob(Session ses) {
		Query q = ses.createQuery("from student");
    	List<StudentTest> students = q.list();
    	return students.get(0);
	}

}
