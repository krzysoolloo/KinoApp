package pl.libront.kinoapp.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "movie_score")
public class MovieScore {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "movie_score_id_seq")
	@SequenceGenerator(name = "movie_score_id_seq", sequenceName = "movie_score_id_seq")
	@Column
	@NotNull
	private Integer id;
	
	@NotNull
	@Column(name="movie_id", nullable=false)
	private Integer movie_id;
	
	@NotNull
	@Column(name="amount", nullable=false)
	private Integer amount;
	 
	@NotNull
	@Column(name="quantity", nullable=false)
	private Integer quantity;

	public MovieScore(){}
	
	public MovieScore(Integer id, Integer movie_id, Integer amount, Integer quantity) {
		super();
		this.id = id;
		this.movie_id = movie_id;
		this.amount = amount;
		this.quantity = quantity;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(Integer movie_id) {
		this.movie_id = movie_id;
	}

	public Integer getAmount1() {
		return amount;
	}

	public void setAmount1(Integer amount) {
		this.amount = amount;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
}
