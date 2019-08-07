package springproject.dao;

import java.util.List;

import springproject.entity.Feature;

public interface FeatureDAO {
	public List<Feature> getFeatures();
	public Feature getFeature(int id);
	public void deleteFeature(int id);
	public void saveFeature(Feature f);
}
