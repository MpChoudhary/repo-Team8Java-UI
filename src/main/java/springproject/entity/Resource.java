package springproject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;

@Entity
@Table(name="resource")
public class Resource {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="code")
	private String code;
	
	@Column(name="name")
	private String name;

	// Javax old cascade type
	//	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE,
	//			   			CascadeType.DETACH, CascadeType.REFRESH})

	// New hibernate cascade
	@ManyToOne
	@JoinColumn(name="project_id")
	@Cascade({CascadeType.SAVE_UPDATE})
	@JsonBackReference
	private Project project;

	@OneToOne(mappedBy="resource", fetch = FetchType.EAGER)
	@Cascade(CascadeType.SAVE_UPDATE)
	@JsonManagedReference
	private FeatureValue featureValue;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Resource(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public Resource() {
	}

	public FeatureValue getFeatureValue() {
		return featureValue;
	}

	public void setFeatureValue(FeatureValue featureValue) {
		this.featureValue = featureValue;
	}

	@Override
	public String toString() {
		return "Resource{" +
				"id=" + id +
				", code='" + code + '\'' +
				", name='" + name + '\'' +
				", project=" + project +
				", featureValue=" + featureValue +
				'}';
	}
}
