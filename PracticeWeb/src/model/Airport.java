package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import static javax.persistence.GenerationType.IDENTITY;
/**
 * The persistent class for the PASSENGER database table.
 * 
 */
@Entity
@NamedQuery(name = "Airport.findAll", query = "SELECT a FROM Airport a ORDER BY a.name")
public class Airport implements Serializable , IModel{

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private int id;

	private String name;
	private String country;
	private String city;

	// bi-directional many-to-one association to Ticket
//	@ManyToMany(mappedBy = "airport")
	private List<Flight> flights;

	@Override
	public String toString() {
		return "Airport [id=" + id + ", name=" + name + ", country=" + country + ", city=" + city + ", flights="
				+ flights + "]";
	}
	private static final long serialVersionUID = 1L;

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
		Airport other = (Airport) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


	public Airport() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCoutnry() {
		return this.country;
	}

	public void setCoutnry(String country) {
		this.country = country;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public List<Flight> getFlights() {
		return this.flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	public Flight addFlightFROM(Flight flight) {
		getFlights().add(flight);
		flight.setAirportFrom(this);
		return flight;
	}
	public Flight addFlightTO(Flight flight) {
		getFlights().add(flight);
		flight.setAirportTO(this);
		return flight;
	}

	public Flight removeFlightFrom(Flight flight) {
		getFlights().remove(flight);
		flight.setAirportFrom(null);
		return flight;
	}
	public Flight removeFlightTo(Flight flight) {
		getFlights().remove(flight);
		flight.setAirportTO(null);
		return flight;
	}

	@Override
	public String[] getTableHeaders() {
		return new String[] { "ID", "Name", "Coutnry", "City"};
	}
	@Override
	public Object[] getTableRowData() {
		return new Object[] { id, name, country, city };
	}
	@Override
	public void updateWith(Object mask) {
		Airport obj = (Airport) mask;
		name = obj.getName();
		country = obj.getCoutnry();
		city = obj.getCity();
	}

	@Override
	public void setObjectId(int id) {
		setId(id);
	}
}