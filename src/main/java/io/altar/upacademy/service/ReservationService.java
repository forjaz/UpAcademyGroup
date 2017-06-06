package io.altar.upacademy.service;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.transaction.Transactional;

import io.altar.upacademy.model.Reservation;

@Named("reservationService")
@RequestScoped
@Transactional
public class ReservationService extends EntityService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// Constructor
	public ReservationService() {
		
	}

	// DB Methods
	public String newReservation(Reservation reservation, String nextPage) {
		em.persist(reservation);
		return nextPage;
	}

	// Getters and Setters
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
