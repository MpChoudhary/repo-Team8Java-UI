package springproject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name="project")
public class Project {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;

	@OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
	@Cascade({CascadeType.SAVE_UPDATE})
	@JsonBackReference
	private List<Resource> resources;

	@OneToMany(mappedBy="project", fetch = FetchType.EAGER)
	@Cascade({CascadeType.SAVE_UPDATE})
	@JsonBackReference
	private List<Feature> features;

	@OneToMany(mappedBy="project", fetch = FetchType.EAGER)
	@Cascade({CascadeType.SAVE_UPDATE})
	@JsonBackReference
	private List<FeatureValue> featureValues;

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

	public List<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(List<Feature> features) {
		this.features = features;
	}

	public List<FeatureValue> getFeatureValues() {
		return featureValues;
	}

	public void setFeatureValues(List<FeatureValue> featureValues) {
		this.featureValues = featureValues;
	}

	@Override
	public String toString() {
		return "Project{" +
				"id=" + id +
				", name='" + name + '\'' +
				", resources=" + resources +
				", features=" + features +
				", featureValues=" + featureValues +
				'}';
	}
}
