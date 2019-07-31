//package springproject.entity;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//
//
//public class TestDB {
//
//	public static void main(String[] args) {
//
//		// create session factory
//		SessionFactory factory = new Configuration()
//								.configure("hibernate.cfg.xml")
//								.addAnnotatedClass(Project.class)
//								.addAnnotatedClass(Resource.class)
//								.buildSessionFactory();
//		
//		// create session
//		Session session = factory.getCurrentSession();
//		
//		try {			
//			
//			// start a transaction
//			session.beginTransaction();
//			
//			Project p1 = new Project("Project 1");
//			Resource r1 = new Resource("010000", "GeneralRequirements");
//			Resource r2 = new Resource("020000", "ExistingConditions");
//			
//			// add courses to instructor
//			p1.add(r1);
//			p1.add(r2);
////			r1.setProject(p1);
////			r2.setProject(p1);
////			session.save(r1);
////			session.save(r2);
//			session.saveOrUpdate(p1);
//			// commit transaction
//			session.getTransaction().commit();
//			
//			System.out.println("Done!");
//		}
//		finally {
//			
//			// add clean up code
//			session.close();
//			
//			factory.close();
//		}
//	}
//
//}
//
//
//
//
//
