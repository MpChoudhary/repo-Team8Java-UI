package springproject.dao;

import java.util.List;

import springproject.entity.Project;

public interface ProjectDAO {
	List<Project> getProjects();
	Project getProject(int id);
	void deleteProject(int id);
	void saveProject(Project p);
}
