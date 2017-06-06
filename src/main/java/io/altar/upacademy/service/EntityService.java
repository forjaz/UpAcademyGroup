package io.altar.upacademy.service;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import io.altar.upacademy.model.HotelEntity;

public abstract class EntityService<T2 extends HotelEntity> implements Serializable {
	private static final long serialVersionUID = 1L;

	// Entity Manager
	@PersistenceContext(name = "UpAcademyGroupPU")
	private static EntityManager em;

	// Getters and Setters
	public static EntityManager getEm() {
		return em;
	}

	public static void setEm(EntityManager em) {
		EntityService.em = em;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
