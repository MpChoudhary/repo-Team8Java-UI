package springproject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import springproject.entity.FeatureValue;


@Repository
public class FeatureValueDAOImpl implements FeatureValueDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<FeatureValue> getFeatureValues() {
		Session session = sessionFactory.getCurrentSession();
		List<FeatureValue> features = session.createQuery("from FeatureValue", FeatureValue.class)
								.getResultList();
		return features;
	}

	@Override
	public FeatureValue getFeatureValue(int id) {
		Session session = sessionFactory.getCurrentSession();
		FeatureValue f = session.get(FeatureValue.class, id);
		return f;
	}

	@Override
	public void deleteFeatureValue(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("delete from FeatureValue where id=:featureValueId");
		q.setParameter("featureValueId", id);
		q.executeUpdate();
	}

	@Override
	public void saveFeatureValue(FeatureValue fv) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(fv);
	}

}
