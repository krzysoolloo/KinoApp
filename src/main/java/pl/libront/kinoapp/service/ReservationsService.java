package pl.libront.kinoapp.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.libront.kinoapp.dao.interfaces.ReservationsDAO;
import pl.libront.kinoapp.dao.interfaces.ShowingsDAO;
import pl.libront.kinoapp.model.Reservations;
import pl.libront.kinoapp.model.Showings;
import pl.libront.kinoapp.model.ShowsDate;

@Service("ReservationsService")
@Transactional
public class ReservationsService {
	
	@Autowired
	ReservationsDAO rDAO;
	
	public List<Reservations> getReservations(int id) {  	
		return rDAO.findById(id);
    }
	
	public void saveOrUpdate(Reservations res) {  	
		rDAO.saveOrUpdate(res);
    }

	public void save(Reservations r) {
		rDAO.save(r);
	}

	public List<Reservations> getUserReservations(String name) {
		return rDAO.getUserReservations(name);
	}

}
