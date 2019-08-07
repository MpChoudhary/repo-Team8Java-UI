package springproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springproject.dao.ProjectDAO;
import springproject.dao.ResourceDAO;
import springproject.dao.FeatureDAO;
import springproject.dao.FeatureValueDAO;
import springproject.entity.Project;
import springproject.entity.Resource;
import springproject.entity.Feature;
import springproject.entity.FeatureValue;

@Service
public class ProjectServiceImpl implements ProjectService{
	
	@Autowired
	private ProjectDAO projectDAO;
	
	@Autowired
	private ResourceDAO resourceDAO;

	@Autowired
	private FeatureDAO featureDAO;

	@Autowired
	private FeatureValueDAO featureValueDAO;

	/**
	 * Project
	 */
	@Override
	@Transactional
	public List<Project> getProjects() {
		return projectDAO.getProjects();
	}

	@Override
	@Transactional
	public Project getProject(int id) {
		return projectDAO.getProject(id);
	}

	@Override
	@Transactional
	public void deleteProject(int id) {
		projectDAO.deleteProject(id);
	}

	@Override
	@Transactional
	public void saveProject(Project p) {
		projectDAO.saveProject(p);
	}

	/**
	 * Resource
	 */

	@Override
	@Transactional
	public List<Resource> getResources() {
		return resourceDAO.getResources();
	}

	@Override
	@Transactional
	public Resource getResource(int id) {
		return resourceDAO.getResource(id);
	}

	@Override
	@Transactional
	public void deleteResource(int id) {
		resourceDAO.deleteResource(id);
	}

	@Override
	@Transactional
	public void saveResource(Resource r) {
		resourceDAO.saveResource(r);
	}

	/**
	 * Feature
	 */

	@Override
	@Transactional
	public List<Feature> getFeatures() {
		return featureDAO.getFeatures();
	}

	@Override
	@Transactional
	public Feature getFeature(int id) {
		return featureDAO.getFeature(id);
	}

	@Override
	@Transactional
	public void deleteFeature(int id) {
		featureDAO.deleteFeature(id);
	}

	@Override
	@Transactional
	public void saveFeature(Feature f) {
		featureDAO.saveFeature(f);
	}

	/**
	 * FeatureValue
	 */

	@Override
	@Transactional
	public List<FeatureValue> getFeatureValues() {
		return featureValueDAO.getFeatureValues();
	}

	@Override
	@Transactional
	public FeatureValue getFeatureValue(int id) {
		return featureValueDAO.getFeatureValue(id);
	}

	@Override
	@Transactional
	public void deleteFeatureValue(int id) {
		featureValueDAO.deleteFeatureValue(id);
	}

	@Override
	@Transactional
	public void saveFeatureValue(FeatureValue fv) {
		featureValueDAO.saveFeatureValue(fv);
	}

}
