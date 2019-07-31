package springproject.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="project")
public class Project {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	// set up OneToMany mapping
	
	@OneToMany(mappedBy="project",
				cascade={CascadeType.PERSIST, CascadeType.MERGE,
						 CascadeType.DETACH, CascadeType.REFRESH})
	private List<Resource> resources;

	//private List<Feature> features;
	
	//private List<FeatureValue> featureValues;
	
	public Project() {
		
	}
	
	public Project(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", resources=" + resources + "]";
	}
	
	public void add(Resource r) {
		if (resources == null) {
			resources = new ArrayList<Resource>();
		}
		resources.add(r);
		r.setProject(this);
	}
}
