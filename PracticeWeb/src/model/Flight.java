package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the FLIGHT database table.
 * 
 */
@Entity
@NamedQuery(name = "Flight.findAll", query = "SELECT f FROM Flight f ORDER BY f.name")
public class Flight implements Serializable, IModel{
	

	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	private String name;
	private int continuance;
	
	
	@ManyToOne
	@JoinColumn(name = "FLIGHT_FROM")
	private Airport airportFROM;

	@ManyToOne
	@JoinColumn(name = "FLIGHT_TO")
	private Airport airportTO;

	
	
	// bi-directional many-to-one association to Ticket
	@OneToMany(mappedBy = "flight")
	private List<Ticket> tickets;

	public Flight() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getContinuance() {
		return this.continuance;
	}

	public void setContinuance(int continuance) {
		this.continuance = continuance;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public Airport getAirportFrom() {
		return this.airportFROM;
	}
	public void setAirportFrom(Airport airportFROM) {
		this.airportFROM = airportFROM;
	}
	public Airport getAirportTO() {
		return this.airportTO;
	}
	public void setAirportTO(Airport airportTO) {
		this.airportTO = airportTO;
	}

	
	public List<Ticket> getTickets() {
		return this.tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Ticket addTicket(Ticket ticket) {
		getTickets().add(ticket);
		ticket.setFlight(this);

		return ticket;
	}

	public Ticket removeTicket(Ticket ticket) {
		getTickets().remove(ticket);
		ticket.setFlight(null);

		return ticket;
	}

	
	@Override
	public String[] getTableHeaders() {
		return new String[] { "Id", "Name", "Continuance", "AirportFrom", "AirportTO" };
	}
	@Override
	public Object[] getTableRowData() {
		return new Object[] { id, name, continuance, airportFROM.getId(), airportTO.getId() };
	}

	
	
	
	@Override
	public void updateWith(Object mask) {
		Flight obj = (Flight) mask;
		name = obj.getName();
		continuance = obj.getContinuance();
		//airportFROM = obj.getAirportFrom();
		//airportTO = obj.getAirportTO();
		
	}
	@Override
	public void setObjectId(int id) {
		setId(id);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Flight other = (Flight) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Flight [id=" + id + ", continuance=" + continuance + ", name=" + name + ", airportFROM=" + airportFROM
				+ ", airportTO=" + airportTO + "]";
	}



}