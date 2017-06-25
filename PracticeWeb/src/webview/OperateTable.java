package webview;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Airport;
import model.Flight;
import model.IModel;
import model.Passenger;
import model.Ticket;

/**
 * Servlet implementation class OperateTable
 */
@WebServlet("/OperateTable")
public class OperateTable extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OperateTable() {
		super();
		// TODO Auto-generated constructor stub
	}

	private IModel findObject(Class clazz, int id) {
		IModel obj = null;
		for (Object x : SelectTable.getController().getObjectList(clazz)) {
			obj = (IModel) x;
			if (obj.getId() == id)
				return obj;
		}
		return null;
	}	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String className = SelectTable.className;
		int operation = SelectOperation.operation;
		IModel obj = null;
		try {
			obj = (IModel) Class.forName("model."+className).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (operation == 4) {
			String id = request.getParameter("Id");
			obj.setObjectId(Integer.parseInt(id));
		} else if (obj instanceof Passenger) {
			switch (operation) {
			case 2:
				int id = Integer.parseInt(request.getParameter("passengerId"));
				obj.setObjectId(id);
			case 1:
				String name = request.getParameter("passengerName");
				((Passenger) obj).setName(name);
				String surname = request.getParameter("passengerSurname");
				((Passenger) obj).setSurname(surname);
				String phone = request.getParameter("passengerPhone");
				((Passenger) obj).setPhone(phone);
				int baggage = Integer.parseInt(request.getParameter("passengerBaggage"));
				((Passenger) obj).setBaggage(baggage);
			}
		} else if (obj instanceof Flight) {
			switch (operation) {
			case 2:
				int id = Integer.parseInt(request.getParameter("flightId"));
				obj.setObjectId(id);
			case 1:
				String name = request.getParameter("flightName");
				((Flight) obj).setName(name);
				int continuance = Integer.parseInt(request.getParameter("flightContinuance"));
				((Flight) obj).setContinuance(continuance);
				
				int idAirFROM = Integer.parseInt(request.getParameter("idAirFROM"));
				if (idAirFROM == 12){
					System.out.println("Debug2");
				}
				Airport airportFROM = (Airport) findObject(Airport.class, idAirFROM);
				((Flight) obj).setAirportFrom(airportFROM);
				
				
				
				int idAirTO = Integer.parseInt(request.getParameter("idAirTO"));
				Airport airportTO = (Airport) findObject(Airport.class, idAirTO);
				((Flight) obj).setAirportTO(airportTO);

			
			}
		} else if (obj instanceof Ticket) {
			int price = Integer.parseInt(request.getParameter("ticketPrice"));
			((Ticket) obj).setPrice(price);
			String date = request.getParameter("ticketDate");
			((Ticket) obj).setDate(date);
			if (operation == 2) {
				int id = Integer.parseInt(request.getParameter("ticketId"));
				obj.setObjectId(id);
			} else if (operation == 1) {
				int passId = Integer.parseInt(request.getParameter("passengerId"));
				Passenger pass = (Passenger) findObject(Passenger.class, passId);
				((Ticket) obj).setPassenger(pass);
				int flightId = Integer.parseInt(request.getParameter("flightId"));
				Flight flight = (Flight) findObject(Flight.class, flightId);
				((Ticket) obj).setFlight(flight);
			}
		}
		else if (obj instanceof Airport) {
			String name = request.getParameter("airportName");
			((Airport) obj).setName(name);
			String country = request.getParameter("airportCountry");
			((Airport) obj).setCoutnry(country);
			String city = request.getParameter("airportCity");
			((Airport) obj).setCity(city);
		}

		SelectTable.getController().operateObject(obj, SelectOperation.operation);
		request.setAttribute("model", SelectTable.getTableModel());
		request.setAttribute("className", SelectTable.className);
		request.getRequestDispatcher("showTable.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
