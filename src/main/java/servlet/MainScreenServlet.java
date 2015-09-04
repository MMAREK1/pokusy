package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/main")
public class MainScreenServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		response.setContentType("text/html");
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Main screen</title>");
			
		out.println("</head>");
		out.println("<body>");
		
		User user = (User)request.getAttribute("user");

		if(user !=null) {
			out.printf("<h1>Hello %s from %s,  %d yers old!\n</h1>", 
					user.getName(), user.getCountry(), user.getAge());
		} else {
			request.getRequestDispatcher("login").forward(request, response);
		}
		
		out.println("</body>");
		out.println("</html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
