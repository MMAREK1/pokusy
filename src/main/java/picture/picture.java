package picture;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/pictures")
public class picture extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		HttpSession session = req.getSession();
		Fieldp fieldp = (Fieldp) session.getAttribute("fieldp");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>");
		out.println("Pictures");
		out.println("</title>");
		out.println("</head>");
		out.println("<body BGcolor=\"#E6E6FA\">");
		out.println("<center>");
		if (fieldp == null) {
			fieldp = new Fieldp(5, 5);
			session.setAttribute("fieldp", fieldp);
		} else {
			fieldp = (Fieldp) session.getAttribute("fieldp");
			try {
				int chosenRowI = Integer.parseInt(req.getParameter("row"));
				int chosenColumnI = Integer.parseInt(req.getParameter("column"));
			fieldp.selectSection(chosenRowI, chosenColumnI);
			} catch (Exception e) {
			}
		}
		if("move".equals(req.getParameter("move"))){
			fieldp.move();
		}
		for (int row = 0; row < fieldp.getRowCount(); row++) {
			for (int column = 0; column < fieldp.getColumnCount(); column++) {
				int number = fieldp.getValue(row, column);
				String color;
				if ((fieldp.getFirstColumn() ==column && fieldp.getFirstRow() ==row)
						|| (fieldp.getSecondColumn() ==column && fieldp.getSecondRow() ==row)) {
					color = "red";
				} else {
					color = "white";
				}
				out.print("<a href=\"?row=" + row + "&column=" + column + "\"><img src=\"resources/images/" + number
						+ ".png\" alt=\"sdf\" style=\"border:2px solid " + color + "\"/></a>");
			}
			out.println("<br>");
		}
		out.println("<a href=\"?move=move\">Move Sections</a>");
		out.print("<br><br>");
		if (fieldp.isSolved()) {
			out.println("Vyhral si<br>");
			session.setAttribute("fieldp", null);
		}

		out.print("<img src=\"resources/images/picture.png\" alt=\"sdf\" height=\"150\" width=\"250\" />");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);
	}
}
