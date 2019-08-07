package springproject.dao;

import java.util.List;

import springproject.entity.Resource;

public interface ResourceDAO {
	List<Resource> getResources();
	Resource getResource(int id);
	void deleteResource(int id);
	void saveResource(Resource r);
}
