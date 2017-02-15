package pl.libront.kinoapp.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "movies")
public class Movies {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "movies_id_seq")
	@SequenceGenerator(name = "movies_id_seq", sequenceName = "movies_id_seq")
	@Column
	private Integer id;
	
	@NotEmpty
	@Column(name="title", nullable=false)
	private String title;
	
	@NotEmpty
	@Column(name="description", nullable=false)
	private String description;
	
	@NotEmpty
	@Column(name="trailer", nullable=false)
	private String trailer;
	
	@NotEmpty
	@Column(name="image", nullable=false)
	private String image;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="id", referencedColumnName="movie_id")
	private MovieScore movieScore;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="movie_id", referencedColumnName="id")
	@JsonIgnore
	private Set<Showings> showings;
	
	@NotEmpty
	@Column(name="direction", nullable=false)
	private String direction;

	@NotEmpty
	@Column(name="scenario", nullable=false)
	private String scenario;

	@NotEmpty
	@Column(name="type", nullable=false)
	private String type;

	@NotEmpty
	@Column(name="production", nullable=false)
	private String production;

	@NotEmpty
	@Column(name="premiere", nullable=false)
	private String premiere;

	@NotEmpty
	@Column(name="time", nullable=false)
	private String time;
	
	@NotEmpty
	@Column(name="recommended", nullable=false)
	private String recommended;
	


	public Movies() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	    
    public MovieScore getMovieScore() {
        return movieScore;
    }
 
    public void setMovieScore(MovieScore author) {
        this.movieScore = author;
    }

	public String getRecommended() {
		return recommended;
	}

	public void setRecommended(String recommended) {
		this.recommended = recommended;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getScenario() {
		return scenario;
	}

	public void setScenario(String scenario) {
		this.scenario = scenario;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProduction() {
		return production;
	}

	public void setProduction(String production) {
		this.production = production;
	}

	public String getPremiere() {
		return premiere;
	}

	public void setPremiere(String premiere) {
		this.premiere = premiere;
	}

	public Set<Showings> getShowings() {
		return showings;
	}

	public void setShowings(Set<Showings> showings) {
		this.showings = showings;
	}
	
	

}
