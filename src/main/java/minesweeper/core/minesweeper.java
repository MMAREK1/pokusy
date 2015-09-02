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

		if (field == null || "new".equals(req.getParameter("game"))) {
			field = new Field(5, 5, 5);
			session.setAttribute("field", field);
			session.setAttribute("hra", "open");
		} else {
			try {
				int chosenRowI = Integer.parseInt(req.getParameter("row"));
				int chosenColumnI = Integer.parseInt(req.getParameter("column"));
				if ("mark".equals(req.getParameter("hra"))) {
					field.markTile(chosenRowI, chosenColumnI);
				} else {
					Tile tile = field.getTile(chosenRowI, chosenColumnI);
					// open tiles if near clicked opened tile is/are marked
					// tile/s
					if (tile.getState() == Tile.State.OPEN) {
						int countOfMarks = 0;
						int up = -1;
						int down = 1;
						int left = -1;
						int right = 1;
						if (chosenRowI == 0) {
							up = 0;
						}
						if (chosenRowI == field.getRowCount() - 1) {
							down = chosenRowI;
						}
						if (chosenColumnI == 0) {
							left = 0;
						}
						if (chosenColumnI == field.getColumnCount() - 1) {
							right = chosenColumnI;
						}
						for (int row = chosenRowI - up; row <= chosenRowI + down; row++) {
							for (int column = chosenColumnI - left; column <= chosenColumnI + right; column++) {
								if (field.getTile(row, column).getState() == Tile.State.MARKED) {
									countOfMarks++;
								}
							}
						}
						Clue clue = (Clue) field.getTile(chosenRowI, chosenColumnI);
						if (countOfMarks == clue.getValue()) {
							for (int row = chosenRowI - up; row <= chosenRowI + down; row++) {
								for (int column = chosenColumnI - left; column <= chosenColumnI + right; column++) {
									field.openTile(row, column);
								}
							}
						}
					} else {
						field.openTile(chosenRowI, chosenColumnI);
					}
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
		out.println("<a href=\"?game=new\">New Game</a><br>");
		session.setAttribute("hra", "open");
		out.printf("Remaining mines: %s<br>", field.getRemainingMineCount());
		for (int row = 0; row < field.getRowCount(); row++) {
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
		if (req.getParameter("hra") == null || "mark".equals(req.getParameter("hra"))) {
			out.println("<a href=\"?hra=open\">Change to Open</a><br>");
		} else {
			out.println("<a href=\"?hra=mark\">Change to Mark</a><br>");
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
