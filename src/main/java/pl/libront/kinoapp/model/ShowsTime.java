package pl.libront.kinoapp.model;

public class ShowsTime {
	private Integer id;
	private String time;
	
	
	public ShowsTime(Integer id, String time) {
		super();
		this.id = id;
		this.time = time.substring(0, 5);
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
}
