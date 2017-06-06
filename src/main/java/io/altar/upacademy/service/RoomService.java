package io.altar.upacademy.service;

import io.altar.upacademy.model.Room;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.transaction.Transactional;

@Named("roomService")
@RequestScoped
@Transactional
public class RoomService extends EntityService implements Serializable {
	private static final long serialVersionUID = 1L;

	// Constructor
	public RoomService() {

	}

	// DB Methods
	public String newRoom(Room room, String nextPage) {
		em.persist(room);
		return nextPage;
	}

	// Getters and Setters
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
