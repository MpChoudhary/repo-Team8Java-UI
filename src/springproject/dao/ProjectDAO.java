package springproject.dao;

import java.util.List;

import springproject.entity.Project;

public interface ProjectDAO {
	public List<Project> getProjects();
	public Project getProject(int id);
	public void deleteProject(int id);
	public void saveProject(Project p);
}
