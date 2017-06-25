package test;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.table.TableModel;

import controller.JpaController;
import model.Passenger;

/**
 * Servlet implementation class ServletConToDB
 */
@WebServlet("/ServletConToDB")
public class ServletConToDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static JpaController controller;
	public static String className;

	public static JpaController getController() {
		if (controller == null)
			controller = new JpaController();
		return controller;
	}

	public static TableModel getTableModel() {
		return getController().getModel(className);
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletConToDB() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		className = request.getParameter("className");
		request.setAttribute("model", getTableModel());
		request.setAttribute("className", className);
		request.getRequestDispatcher("showTable.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
