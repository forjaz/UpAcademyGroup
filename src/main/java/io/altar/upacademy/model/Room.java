package io.altar.upacademy.model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Room implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	private double price;
	private int maxOccupation;
	private int currOccupation = 0;
	private String description;
	private String type;
	private String extras;
	private String imageSrc;
	
	// Constructor
	public Room() {
		
	}

	// Getters and Setters
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getMaxOccupation() {
		return maxOccupation;
	}

	public void setMaxOccupation(int maxOccupation) {
		this.maxOccupation = maxOccupation;
	}

	public int getCurrOccupation() {
		return currOccupation;
	}

	public void setCurrOccupation(int currOccupation) {
		this.currOccupation = currOccupation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExtras() {
		return extras;
	}

	public void setExtras(String extras) {
		this.extras = extras;
	}

	public String getImageSrc() {
		return imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
