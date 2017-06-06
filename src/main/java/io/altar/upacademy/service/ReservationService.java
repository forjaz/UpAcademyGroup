package io.altar.upacademy.service;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.transaction.Transactional;

import io.altar.upacademy.model.Reservation;

@Named("reservationService")
@RequestScoped
@Transactional
public class ReservationService extends EntityService<Reservation> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// Constructor
	public ReservationService() {
		
	}

	// Getters and Setters
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
