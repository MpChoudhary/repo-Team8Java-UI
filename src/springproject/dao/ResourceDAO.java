package springproject.dao;

import java.util.List;

import springproject.entity.Resource;

public interface ResourceDAO {
	public List<Resource> getResources();
	public Resource getResource(int id);
	public void deleteResource(int id);
	public void saveResource(Resource r);
}
