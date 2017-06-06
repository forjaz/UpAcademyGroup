package io.altar.upacademy.service;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.transaction.Transactional;

@Named("roomService")
@RequestScoped
@Transactional
public class RoomService implements Serializable {
	private static final long serialVersionUID = 1L;

	// Constructor
	public RoomService() {

	}

	// Getters and Setters
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
