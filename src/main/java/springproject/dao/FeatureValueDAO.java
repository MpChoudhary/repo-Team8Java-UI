package springproject.dao;

import java.util.List;

import springproject.entity.FeatureValue;

public interface FeatureValueDAO {
	public List<FeatureValue> getFeatureValues();
	public FeatureValue getFeatureValue(int id);
	public void deleteFeatureValue(int id);
	public void saveFeatureValue(FeatureValue fv);
}
