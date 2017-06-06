package io.altar.upacademy.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Reservation extends HotelEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private Date startDate;
	private Date endDate;
	private int numbNights;
	private String contactName;
	private String contactEmail;
	private String contactPhone;
	private int numbPeople;
	private Long roomID;

	// Constructor
	public Reservation() {

	}

	public Reservation(Date startDate, Date endDate, int numbNights, String contactName, String contactEmail,
			String contactPhone, int numbPeople, Long roomID) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.numbNights = numbNights;
		this.contactName = contactName;
		this.contactEmail = contactEmail;
		this.contactPhone = contactPhone;
		this.numbPeople = numbPeople;
		this.roomID = roomID;
	}

	// Getters and Setters
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getNumbNights() {
		return numbNights;
	}

	public void setNumbNights(int numbNights) {
		this.numbNights = numbNights;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public int getNumbPeople() {
		return numbPeople;
	}

	public void setNumbPeople(int numbPeople) {
		this.numbPeople = numbPeople;
	}

	public Long getRoomID() {
		return roomID;
	}

	public void setRoomID(Long roomID) {
		this.roomID = roomID;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
