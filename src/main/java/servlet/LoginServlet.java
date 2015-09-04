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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private Map<String, String> countries = getCountryNames();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// extract parameters
		String name = request.getParameter("name");
		String passwd = request.getParameter("passwd");
		String age = request.getParameter("age");
		String country = request.getParameter("country");

		// validate form
		boolean validName = validateName(name);
		boolean validPasswd = validatePasswd(passwd);
		boolean validAge = validateAge(age);
		boolean validCountry = validateCountry(country);

		if (validName && validPasswd && validAge && validCountry) {
			// all data valid - store user into request
			User user = new User();
			user.setName(name);
			user.setPasswd(passwd);
			user.setAge(Integer.parseInt(age));
			user.setCountry(countries.get(country));
			request.setAttribute("user", user);

			// forward to main screen
			request.getRequestDispatcher("main").forward(request, response);
		} else {
			// first access or invalid data - restore form
			PrintWriter out = response.getWriter();

			response.setContentType("text/html");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Hello world</title>");
			out.println("</head>");
			out.println("<body>");

			out.println("<form>");

			// Name
			out.printf("Name: <input type=\"text\" name=\"name\" value=\"%s\">\n", 
					name == null ? "" : name);
			if (!validName) {
				out.print("Zadajte meno 5 az 10 znakov!");
			}
			out.printf("<br>");

			// Passwd
			out.printf("Password: <input type=\"text\" name=\"passwd\" value=\"%s\">\n", 
					passwd == null ? "" : passwd);
			if (!validPasswd) {
				out.print("Zadajte heslo 5 az 10 znakov s aspon jednou cislicou!");
			}
			out.printf("<br>");

			// Age
			out.printf("Age: <input type=\"number\" name=\"age\" value=\"%s\">\n", 
					age == null ? "" : age);
			if (!validAge) {
				out.print("Zadajte vek v rozsahu 0 az 150 rokov!");
			}
			out.printf("<br>");

			// Country
			out.println("Country:");

			out.println("<select name=\"country\">");
			for (Map.Entry<String, String> entry : countries.entrySet()) {
				out.printf("<option value=\"%s\" %s>%s</option>\n", entry.getKey(),
						entry.getKey().equals(country) ? "selected" : "", entry.getValue());
			}
			out.println("</select>");
			if (!validCountry) {
				out.print("Vyberte si krajinu zo zoznamu!");
			}
			out.println("<br>");

			// Submit button
			out.println("<input type=\"submit\" value=\"Send!\">");
			out.println("</form>");

			out.println("</body>");
			out.println("</html>");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	private boolean validateName(String name) {
		return name != null && name.length() >= 5 && name.length() <= 10;
	}

	private boolean validatePasswd(String passwd) {
		return validateName(passwd) && passwd.matches(".*\\d.*");
	}

	private boolean validateCountry(String country) {
		return country != null && !"--".equals(country) && countries.containsKey(country);
	}

	private boolean validateAge(String age) {
		try {
			int ageint = Integer.parseInt(age);
			return ageint >= 0 && ageint <= 150;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private Map<String, String> getCountryNames() {
		Map<String, String> countries = new TreeMap<>();

		countries.put("--", "-- Select Country --");
		for (String code : Locale.getISOCountries()) {
			Locale locale = new Locale("", code);
			String name = locale.getDisplayCountry();
			countries.put(code, name);
		}

		return countries;
	}
}
