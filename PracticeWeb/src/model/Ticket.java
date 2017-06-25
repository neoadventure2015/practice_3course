package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import javax.persistence.*;
import java.util.Date;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * The persistent class for the TICKET database table.
 * 
 */
@Entity
@NamedQuery(name = "Ticket.findAll", query = "SELECT t FROM Ticket t ORDER BY t.price")
public class Ticket implements Serializable, IModel {
	@Override
	public String toString() {
		return "Ticket id=" + id + ", date=" + date + ", price=" + price + ", flight=" + flight + ", passenger="
				+ passenger ;
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private int id;


	private String date;

	private int price;

	// bi-directional many-to-one association to Flight
	@ManyToOne
	@JoinColumn(name = "IDFLIGHT")
	private Flight flight;

	// bi-directional many-to-one association to Passenger
	@ManyToOne
	@JoinColumn(name = "IDPASSENGER")
	private Passenger passenger;

	public Ticket() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String data) {
		this.date = data;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Flight getFlight() {
		return this.flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Passenger getPassenger() {
		return this.passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	@Override
	public String[] getTableHeaders() {
		return new String[] { "ID", "Passenger_name", "Flight_name", "Price", "Date",   };
		//return new String[] { "ID", "Price", "Date", "Flight_id", "Flight_name", "Passenger_id", "Passenger_name" };
	}
	@Override
	public Object[] getTableRowData() {
		return new Object[] { id, passenger.getName(), flight.getName(),  price, date, };
		//return new Object[] { id, price, date, flight.getId(), flight.getName(), passenger.getId(), passenger.getName() };
	}
	@Override
	public void setObjectId(int id) {
		setId(id);
	}
	@Override
	public void updateWith(Object mask) {
		Ticket obj = (Ticket) mask;
		price = obj.getPrice();
		date = obj.getDate();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((flight == null) ? 0 : flight.hashCode());
		result = prime * result + ((passenger == null) ? 0 : passenger.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		if (flight == null) {
			if (other.flight != null)
				return false;
		} else if (!flight.equals(other.flight))
			return false;
		if (passenger == null) {
			if (other.passenger != null)
				return false;
		} else if (!passenger.equals(other.passenger))
			return false;
		return true;
	}
	


}