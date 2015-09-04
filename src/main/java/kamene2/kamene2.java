package kamene2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/stones2")
public class kamene2 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		HttpSession session = req.getSession();
		Fieldk2 fieldk2 = (Fieldk2) session.getAttribute("fieldk2");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>");
		out.println("Stones2");
		out.println("</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<center>");
		if (fieldk2 == null || "new".equals(req.getParameter("move"))) {
			fieldk2 = new Fieldk2(2, 2);
			session.setAttribute("fieldk2", fieldk2);
		} 
		else{
			if (req.getParameter("row") != null && req.getParameter("column") != null) {
				try {
					int chosenRow = Integer.parseInt(req.getParameter("row"));
					int chosenColumn = Integer.parseInt(req.getParameter("column"));
					fieldk2.move(chosenRow, chosenColumn);
				} catch (Exception e) {
				}
			}
		}
		if (fieldk2.getState() == GameState2.SOLVED) {
			out.println("Vyhral si!<br>");
			session.setAttribute("fieldk2", null);
		}
		
		
		for (int row = 0; row < fieldk2.getRowCount(); row++) {
			for (int column = 0; column < fieldk2.getColumnCount(); column++) {
				Number2 number = fieldk2.getNumber(row, column);
				out.print("<a href=\"?row=" + row + "&column=" + column + "\"><img src=\"resources/images2/"
						+ number.getValue() + ".png\" alt=\"sdf\"/></a>");
			}
			out.println("<br>");
		}
		out.println("<br><a href=\"?move=new\">New Game</a>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
	}
}
