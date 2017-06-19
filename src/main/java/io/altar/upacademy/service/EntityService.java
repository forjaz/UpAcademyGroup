package io.altar.upacademy.service;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import io.altar.upacademy.dto.Global;

import java.io.Serializable;

public abstract class EntityService implements Serializable {
	private static final long serialVersionUID = 1L;

	// Entity Manager
	@PersistenceContext(name = "UpAcademyGroupPU")
	protected static EntityManager em;
	
	@Inject
	protected Global global;

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

	public Global getGlobal() {
		return global;
	}

	public void setGlobal(Global global) {
		this.global = global;
	}
	
	

}
