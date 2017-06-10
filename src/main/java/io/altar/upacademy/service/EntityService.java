package io.altar.upacademy.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

public abstract class EntityService implements Serializable {
	private static final long serialVersionUID = 1L;

	// Entity Manager
	@PersistenceContext(name = "UpAcademyGroupPU")
	protected static EntityManager em;

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
