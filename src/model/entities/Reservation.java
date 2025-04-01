package model.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import model.exceptions.DomainException;

public class Reservation {
	
	private Integer roomNumber;
	private LocalDate checkIn;
	private LocalDate checkOut;
	
	private static DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public Reservation(Integer roomNumber, LocalDate checkIn, LocalDate checkOut) throws DomainException {
		
		if(!checkOut.isAfter(checkIn)) {
			throw new DomainException("Error in reservation!");
			
		}
		
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public LocalDate getCheckIn() {
		return checkIn;
	}

	public LocalDate getCheckOut() {
		return checkOut;
	}
	
	public long duration() {
		long diff = ChronoUnit.DAYS.between(checkIn, checkOut);
		return diff;
	}
	
	public void updateDates(LocalDate checkIn, LocalDate checkOut) throws DomainException {
		
		if(checkIn.isBefore(LocalDate.now()) || checkOut.isBefore(LocalDate.now())) {
			throw new DomainException("Error in reservation: Reservation dates for updates must be futures dates!");
			
		}
		if(!checkOut.isAfter(checkIn)) {
			throw new DomainException("Error in reservation!");
			
		}
		
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		
		
	}
	
	public String toString() {
		return "Room "
				+ roomNumber
				+ ", check-in: "
				+ dtf1.format(checkIn)
				+ ", check-out: "
				+ dtf1.format(checkOut)
				+ ", "
				+ duration()
				+ " nights";
	}

}
