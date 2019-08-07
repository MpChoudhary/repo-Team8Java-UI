package springproject.entity;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="feature")
public class Feature {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="type")
	private String type;

	@Column(name="content")
	private String content;
	
	@ManyToOne
	@Cascade({CascadeType.SAVE_UPDATE})
	@JoinColumn(name="project_id")
	@JsonBackReference
	private Project project;
	
	@OneToMany(mappedBy="feature", fetch = FetchType.EAGER)
	@Cascade({CascadeType.SAVE_UPDATE})
	@JsonManagedReference
	private List<FeatureValue> featureValues;


	public Feature() {
		
	}
	
	public Feature(String name, String type, String content) {
		this.name = name;
		this.type = type;
		this.content = content;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<FeatureValue> getFeatureValues() {
		return featureValues;
	}

	public void setFeatureValues(List<FeatureValue> featureValues) {
		this.featureValues = featureValues;
	}

	@Override
	public String toString() {
		return "Feature{" +
				"id=" + id +
				", name='" + name + '\'' +
				", type='" + type + '\'' +
				", content='" + content + '\'' +
				", project=" + project +
				", featureValues=" + featureValues +
				'}';
	}
}
