package webview;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SelectOperation
 */
@WebServlet("/SelectOperation")
public class SelectOperation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static int operation;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectOperation() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		operation = Integer.parseInt(request.getParameter("operation"));
		String tableClassName = SelectTable.className;
		String formName = "";
		if( operation == 4) formName = "getId.html";
		else if(tableClassName.equals("Passenger")){
			if(operation==1) formName = "addPassenger.html";
			else if( operation==2) formName = "editPassenger.html";
		}
		else if(tableClassName.equals("Flight")){
			if(operation==1) formName = "addFlight.html";
			else if( operation==2) formName = "editFlight.html";
		}
		else if(tableClassName.equals("Ticket")){
			if(operation==1) formName = "addTicket.html";
			else if( operation==2) formName = "editTicket.html";
		}
		else if(tableClassName.equals("Airport")){
			if(operation==1) formName = "addAirport.html";
			else if( operation==2) formName = "editAirport.html";
		}
		request.getRequestDispatcher(formName).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
