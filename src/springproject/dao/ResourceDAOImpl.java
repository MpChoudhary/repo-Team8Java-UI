package springproject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import springproject.entity.Resource;

@Repository
public class ResourceDAOImpl implements ResourceDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Resource> getResources() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Resource", Resource.class).getResultList();
	}

	@Override
	public Resource getResource(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Resource.class, id);
	}

	@Override
	public void deleteResource(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("delete from Resource where id=:resourceId");
		q.setParameter("resourceId", id);
		q.executeUpdate();
	}

	@Override
	public void saveResource(Resource r) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(r);
	}
}
