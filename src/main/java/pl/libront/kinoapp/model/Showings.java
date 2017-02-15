package pl.libront.kinoapp.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "showings")
public class Showings {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "showings_id_seq")
	@SequenceGenerator(name = "showings_id_seq", sequenceName = "showings_id_seq")
	@Column
	@NotNull
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id", referencedColumnName="id")
	private Movies movie;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cinema_halls_id", referencedColumnName="id")
	private CinemaHalls cinemaHalls;
	
	@Column(name="date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Transient
	private Integer movieid;
	
	public Showings() {
		super();
	}
	
	public Showings(Integer id, Movies movie, CinemaHalls cinemaHalls, Date date) {
		super();
		this.id = id;
		this.movie = movie;
		this.cinemaHalls = cinemaHalls;
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Movies getMovie() {
		return movie;
	}

	public void setMovie(Movies movie) {
		this.movie = movie;
	}

	public CinemaHalls getCinemaHalls() {
		return cinemaHalls;
	}

	public void setCinemaHalls(CinemaHalls cinemaHalls) {
		this.cinemaHalls = cinemaHalls;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getMovieid() {
		return movieid;
	}

	public void setMovieid(Integer movieid) {
		this.movieid = movieid;
	}

	
	
}
