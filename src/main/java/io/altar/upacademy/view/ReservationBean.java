package io.altar.upacademy.view;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import io.altar.upacademy.model.Reservation;
import io.altar.upacademy.service.ReservationService;

@Named("reservationBean")
@RequestScoped
public class ReservationBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Reservation reservation = new Reservation();

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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
