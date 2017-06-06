package io.altar.upacademy.view;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import io.altar.upacademy.service.RoomService;

@Named("roomBean")
@RequestScoped
public class RoomBean implements Serializable {
	private static final long serialVersionUID = 1L;

	// Constructor
	public RoomBean() {

	}

	// Injector
	@Inject
	private RoomService roomService;

	// Getters and Setters
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public RoomService getRoomService() {
		return roomService;
	}

	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
	}

}
