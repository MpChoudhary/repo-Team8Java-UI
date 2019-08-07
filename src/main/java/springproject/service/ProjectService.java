package springproject.service;

import java.util.List;

import springproject.entity.Feature;
import springproject.entity.FeatureValue;
import springproject.entity.Project;
import springproject.entity.Resource;

public interface ProjectService {
	List<Project> getProjects();
	Project getProject(int id);
	void deleteProject(int id);
	void saveProject(Project p);

	List<Resource> getResources();
	Resource getResource(int id);
	void deleteResource(int id);
	void saveResource(Resource r);

	List<Feature> getFeatures();
	Feature getFeature(int id);
	void deleteFeature(int id);
	void saveFeature(Feature f);

	List<FeatureValue> getFeatureValues();
	FeatureValue getFeatureValue(int id);
	void deleteFeatureValue(int id);
	void saveFeatureValue(FeatureValue fv);
}
