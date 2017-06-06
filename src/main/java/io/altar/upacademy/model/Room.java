package io.altar.upacademy.model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;

@Entity
public class Room extends HotelEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private double price;
	private int maxOccupation;
	private int currOccupation;
	private String description;
	private String type;
	private ArrayList<String> extras;
	private String imageSrc;
	
	// Constructor
	public Room() {
		
	}
	
	public Room(double price, int maxOccupation, int currOccupation, String description, String type,
			ArrayList<String> extras, String imageSrc) {
		super();
		this.price = price;
		this.maxOccupation = maxOccupation;
		this.currOccupation = currOccupation;
		this.description = description;
		this.type = type;
		this.extras = extras;
		this.imageSrc = imageSrc;
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

	public ArrayList<String> getExtras() {
		return extras;
	}

	public void setExtras(ArrayList<String> extras) {
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

}
