package minesweeper.core;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/mines")
public class minesweeper extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
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
		out.println("<center>");

		if (field == null || "new".equals(req.getParameter("game"))) {
			field = new Field(10,10, 15);
			session.setAttribute("field", field);
			session.setAttribute("hra", "open");
		} else {
			try {
				int chosenRowI = Integer.parseInt(req.getParameter("row"));
				int chosenColumnI = Integer.parseInt(req.getParameter("column"));
				Tile tile = field.getTile(chosenRowI, chosenColumnI);
				// open tiles if near clicked opened tile is/are marked
				// tile/s
				if ("mark".equals(req.getParameter("hra"))) {
					field.markTile(chosenRowI, chosenColumnI);
				} else {
					field.setPomoc(0);
					field.openTile(chosenRowI, chosenColumnI);
				}
			} catch (Exception e) {
			}
			

		}
		switch (field.getState())

		{
		case SOLVED:
			session.setAttribute("field", null);
			out.println("Vyhral si!<br>");
			break;
		case FAILED:
			out.println("Prehral si<br>");
			session.setAttribute("field", null);
			break;
		default:
			break;
		}
		out.println("<a href=\"?game=new\">New Game</a><br>");
		session.setAttribute("hra", "open");
		out.printf("Remaining mines: %s<br>", field.getRemainingMineCount());
		for (

		int row = 0; row < field.getRowCount(); row++)

		{
			for (int column = 0; column < field.getColumnCount(); column++) {
				Tile tile = field.getTile(row, column);
				out.print("<a href=\"?row=" + row + "&column=" + column + "&hra=" + req.getParameter("hra")
						+ "\"><img src=\"resources/images/");
				if (tile.getState() == Tile.State.MARKED) {
					out.print("marked.png\" alt=\"sdf\"/></a>");
				} else {
					if (tile.getState() == Tile.State.CLOSED) {
						out.print("closed.png\" alt=\"sdf\"/></a>");
					} else {
						if (tile instanceof Mine) {
							out.print("mine.png\" alt=\"sdf\"/>");
						} else {
							Clue clue = (Clue) tile;
							int value = clue.getValue();
							out.print("open" + value + ".png\" alt=\"sdf\"/>");
						}
					}
				}
			}
			out.println("<br>");
		}
		out.println("<br>");
		if ("mark".equals(req.getParameter("hra")))

		{
			out.println("<a href=\"?hra=open\">Change to Open</a><br>");
		} else

		{
			out.println("<a href=\"?hra=mark\">Change to Mark</a><br>");
		}
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
