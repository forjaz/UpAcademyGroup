package io.altar.upacademy.view;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import io.altar.upacademy.service.ReservationService;

@Named("reservationBean")
@RequestScoped
public class ReservationBean implements Serializable {
	private static final long serialVersionUID = 1L;

	// Constructor
	public ReservationBean() {

	}

	// Injector
	@Inject
	private ReservationService reservationService;

	// Getters and Setters
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ReservationService getReservationService() {
		return reservationService;
	}

	public void setReservationService(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	
	

}
