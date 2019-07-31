package springproject.service;

import java.util.List;

import springproject.entity.Project;
import springproject.entity.Resource;

public interface ProjectService {
	public List<Project> getProjects();
	public Project getProject(int id);
	public void deleteProject(int id);
	public void saveProject(Project p);
	public List<Resource> getResources();
	public Resource getResource(int id);
	public void deleteResource(int id);
	public void saveResource(Resource r);
}
