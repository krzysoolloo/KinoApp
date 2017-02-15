package pl.libront.kinoapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cinema_halls")
public class CinemaHalls {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "cinema_halls_id_seq")
	@SequenceGenerator(name = "cinema_halls_id_seq", sequenceName = "cinema_halls_id_seq")
	@Column
	@NotNull
	private Integer id;
	
	@NotNull
	@Column(name="number", nullable=false)
	private Integer number;
	
	@NotNull
	@Column(name="rows", nullable=false)
	private Integer rows;
	
	@NotNull
	@Column(name="cells", nullable=false)
	private Integer cells;

	public CinemaHalls(Integer id, Integer number, Integer rows, Integer cells) {
		super();
		this.id = id;
		this.number = number;
		this.rows = rows;
		this.cells = cells;
	}

	public CinemaHalls() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getCells() {
		return cells;
	}

	public void setCells(Integer cells) {
		this.cells = cells;
	}
	
}
