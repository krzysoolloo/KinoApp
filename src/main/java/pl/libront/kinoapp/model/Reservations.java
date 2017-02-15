package pl.libront.kinoapp.model;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "reservations")
public class Reservations {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "reservations_id_seq")
	@SequenceGenerator(name = "reservations_id_seq", sequenceName = "reservations_id_seq")
	@Column
	@NotNull
	private Integer id;
	
	@NotNull
	@Column(name="row", nullable=false)
	private Integer row;
	
	@NotNull
	@Column(name="cell", nullable=false)
	private Integer cell;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="showing_id", referencedColumnName="id")
	private Showings showings;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="user_id", referencedColumnName="email")
	private User user;

	public Reservations(){}
	
	public Reservations(Integer id, Integer row, Integer cell, Showings showings, User user) {
		super();
		this.id = id;
		this.row = row;
		this.cell = cell;
		this.showings = showings;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public Integer getCell() {
		return cell;
	}

	public void setCell(Integer cell) {
		this.cell = cell;
	}

	public Showings getShowings() {
		return showings;
	}

	public void setShowings(Showings showings) {
		this.showings = showings;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
