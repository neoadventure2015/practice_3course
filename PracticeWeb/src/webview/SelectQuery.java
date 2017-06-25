package webview;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.JpaController;

/**
 * Servlet implementation class SelectQuery
 */
@WebServlet("/SelectQuery")
public class SelectQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectQuery() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JpaController controller = SelectTable.getController();
		String query = request.getParameter("query");
		if (query.equals("ticket")) {
			//request.setAttribute("model", controller.doQuery1());
			request.setAttribute("className", "'ticket with price > 200'");
		}
	/*	if (query.equals("something")) {
			request.setAttribute("model", controller.doQuery2());
			request.setAttribute("className", "__");
		}*/
		request.getRequestDispatcher("showTable.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
