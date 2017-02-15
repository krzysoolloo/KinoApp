package pl.libront.kinoapp.model;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class ShowsDate {
	private Date date;
	private List<ShowsTime> time;
	
	public ShowsDate(Date date){
		this.date = date;
		this.time = null;
	}
	
	public ShowsDate(Date date, List<ShowsTime> time){
		this.date = date;
		this.time = time;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<ShowsTime> getTime() {
		return time;
	}
	public void setTime(List<ShowsTime> time) {
		this.time = time;
	}
}
