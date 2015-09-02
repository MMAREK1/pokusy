package minesweeper.core;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/mines")
public class minesweeper extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		boolean stateTile=true;
		resp.setContentType("text/html");
		HttpSession session = req.getSession();
		Field field = (Field) session.getAttribute("field");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>");
		out.println("Minesweeper");
		out.println("</title>");
		out.println("</head>");
		out.println("<body>");
		
		if (field == null || "new".equals(req.getParameter("hra"))) {
			field = new Field(5, 5, 5);
			session.setAttribute("field", field);
		} else {
			try {
				int chosenRowI = Integer.parseInt(req.getParameter("row"));
				int chosenColumnI = Integer.parseInt(req.getParameter("column"));
				if ("open".equals(req.getParameter("hra"))||req.getParameter("hra")==null) {
					field.openTile(chosenRowI, chosenColumnI);
				} else {
					field.markTile(chosenRowI, chosenColumnI);
				}
			} catch (Exception e) {

			}
		}
		switch (field.getState()) {
		case SOLVED:
			session.setAttribute("field", null);
			out.println("Vyhral si!<br>");
			break;
		case FAILED:
			out.println("Prehral si<br>");
			session.setAttribute("field", null);
			break;
		}
		out.println("<a href=\"?hra=new\">New Game</a><br>");

		for (int row = 0; row < field.getRowCount(); row++) {
			for (int column = 0; column < field.getColumnCount(); column++) {
				Tile tile = field.getTile(row, column);
				if (tile.getState() == Tile.State.MARKED) {
					out.print("<a href=\"?row=" + row + "&column=" + column
							+"\"><img src=\"resources/images/marked.png\" alt=\"sdf\"/></a>");
				} else {
					if (tile.getState() == Tile.State.CLOSED) {
						out.print("<a href=\"?row=" + row + "&column=" + column
								+ "\"><img src=\"resources/images/closed.png\" alt=\"sdf\"/></a>");
					} else {
						if (tile instanceof Mine) {
							out.print("<img src=\"resources/images/mine.png\" alt=\"sdf\"/>");
						} else {
							Clue clue = (Clue) tile;
							int value = clue.getValue();
							out.print("<img src=\"resources/images/open" + value + ".png\" alt=\"sdf\"/>");
						}
					}
				}
			}
			out.println("<br>");
		}
		out.println("<br>");
		if ("open".equals(req.getParameter("hra"))||req.getParameter("hra")==null) {
			out.println("<a href=\"?hra=mark\">Change to Mark</a><br>");
		} else {
			out.println("<a href=\"?hra=open\">Change to Open</a><br>");
		}
		out.println("</body>");
		out.println("</html>");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
