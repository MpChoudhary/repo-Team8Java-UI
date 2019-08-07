package springproject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import springproject.entity.Project;

@Repository
public class ProjectDAOImpl implements ProjectDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Project> getProjects() {
		Session session = sessionFactory.getCurrentSession();
		List<Project> projects = session.createQuery("from Project", Project.class)
								.getResultList();
		return projects;
	}

	@Override
	public void deleteProject(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("delete from Project where id=:projectId");
		q.setParameter("projectId", id);
		q.executeUpdate();
	}

	@Override
	public Project getProject(int id) {
		Session session = sessionFactory.getCurrentSession();
		Project p = session.get(Project.class, id);
		return p;
	}

	@Override
	public void saveProject(Project p) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(p);
	}
	
}
