package springproject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import springproject.entity.Feature;

@Repository 
public class FeatureDAOImpl implements FeatureDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Feature> getFeatures() {
		Session session = sessionFactory.getCurrentSession();
		List<Feature> features = session.createQuery("from Feature", Feature.class)
								.getResultList();
		return features;
	}

	@Override
	public Feature getFeature(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Feature.class, id);
	}

	@Override
	public void deleteFeature(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("delete from Feature where id=:featureId");
		q.setParameter("featureId", id);
		q.executeUpdate();
	}

	@Override
	public void saveFeature(Feature f) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(f);
	}

}
