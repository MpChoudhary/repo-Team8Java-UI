package springproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springproject.dao.ProjectDAO;
import springproject.dao.ResourceDAO;
import springproject.entity.Project;
import springproject.entity.Resource;

@Service
public class ProjectServiceImpl implements ProjectService{
	
	@Autowired
	private ProjectDAO projectDAO;
	
	@Autowired
	private ResourceDAO resourceDAO;
	
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

}
