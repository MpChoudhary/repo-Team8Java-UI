package springproject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="featureValue")
public class FeatureValue {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="value")
	private String value;
	
	@ManyToOne
	@Cascade({CascadeType.MERGE})
	@JoinColumn(name="feature_id")
	@JsonManagedReference
	private Feature feature;

	@OneToOne
	@Cascade({CascadeType.MERGE})
	@JoinColumn(name="resource_id")
	@JsonManagedReference
	private Resource resource;
	
	@ManyToOne
	@Cascade({CascadeType.MERGE})
	@JoinColumn(name="project_id")
	@JsonManagedReference
	private Project project;
	
	public FeatureValue() {
		
	}
	
	public FeatureValue(String value) {
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Feature getFeature() {
		return feature;
	}

	public void setFeature(Feature feature) {
		this.feature = feature;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public String toString() {
		return "FeatureValue [id=" + id + ", value=" + value + ", feature=" + feature + ", resource=" + resource
				+ ", project=" + project + "]";
	}
	
}
