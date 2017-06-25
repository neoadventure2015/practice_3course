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
@NamedQuery(name = "Passenger.findAll", query = "SELECT p FROM Passenger p ORDER BY p.name")
public class Passenger implements Serializable , IModel{
	@Override
	public String toString() {
		return "Passenger id=" + id + ", baggage=" + baggage + ", name=" + name +  ", surname="
				+ surname + ", phone=" + phone ;
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
		Passenger other = (Passenger) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private int id;

	private int baggage;

	private String name;

	private String phone;

	private String surname;

	// bi-directional many-to-one association to Ticket
	@OneToMany(mappedBy = "passenger")
	private List<Ticket> tickets;

	public Passenger() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBaggage() {
		return this.baggage;
	}

	public void setBaggage(int baggage) {
		this.baggage = baggage;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public List<Ticket> getTickets() {
		return this.tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Ticket addTicket(Ticket ticket) {
		getTickets().add(ticket);
		ticket.setPassenger(this);

		return ticket;
	}

	public Ticket removeTicket(Ticket ticket) {
		getTickets().remove(ticket);
		ticket.setPassenger(null);

		return ticket;
	}
	@Override
	public String[] getTableHeaders() {
		return new String[] { "ID", "Name", "Surname", "Phone", "Baggage" };
	}
	@Override
	public Object[] getTableRowData() {
		return new Object[] { id, name, surname, phone, baggage };
	}
	@Override
	public void updateWith(Object mask) {
		Passenger obj = (Passenger) mask;
		name = obj.getName();
		surname = obj.getSurname();
		phone = obj.getPhone();
		baggage = obj.getBaggage();
	}
	@Override
	public void setObjectId(int id) {
		setId(id);
	}
}