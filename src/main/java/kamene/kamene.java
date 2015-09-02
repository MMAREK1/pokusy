package kamene;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/stones")
public class kamene extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		HttpSession session = req.getSession();
		Fieldk field = (Fieldk) session.getAttribute("field");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>");
		out.println("Stones");
		out.println("</title>");
		out.println("</head>");
		out.println("<body BGcolor=\"#3a6ea5\">");
		if (field == null) {
			field = new Fieldk(4, 4);
			session.setAttribute("field", field);
		}
		if (req.getParameter("move") != null) {
			field.move(req.getParameter("move").toUpperCase());
		}
		for (int row = 0; row < field.getRowCount(); row++) {
			for (int column = 0; column < field.getColumnCount(); column++) {
				Number number = field.getNumber(row, column);
				if (number.getValue() != 0) {
					out.print("<img src=\"resources/images2/" + number.getValue() + ".png\" alt=\"sdf\"/>");
				}
			}
			out.println("<br>");
		}

		out.println("<br>");
		out.println("<br>");
		out.println("<table border=\"0\">");
		out.println("<tr>");
		out.println("<td>");
		out.println("</td>");
		out.println("<td>");
		out.print("<a href=\"?move=up\"><img src=\"resources/images2/up.png\" alt=\"sdf\"/></a>");
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>");
		out.print("<a href=\"?move=left\"><img src=\"resources/images2/left.png\" alt=\"sdf\"/></a>");
		out.println("</td>");
		out.println("<td>");
		out.print("<a href=\"?move=down\"><img src=\"resources/images2/down.png\" alt=\"sdf\"/></a>");
		out.println("</td>");
		out.println("<td>");
		out.print("<a href=\"?move=right\"><img src=\"resources/images2/right.png\" alt=\"sdf\"/></a>");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	}
}
