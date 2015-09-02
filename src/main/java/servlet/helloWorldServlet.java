package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/hello")
public class helloWorldServlet extends HttpServlet {
	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>");
		out.println("Hello ");
		out.println("</title>");
		out.println("</head>");
		out.println("<body>");
		// out.println(new Date());
		out.println("<img src=\"resources/gfx/dualbrand_logo.png\" /><br>");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String country = request.getParameter("country");
		if (name != null && lengthCorrected(name)
				&& lengthCorrected(password)
				&& passwordPattern(password)
				&& !"none".equals(country)) {
			out.printf("<h1>Hello %s</h1>", name);
			out.printf("<h2>from  %s</h2>", country);
			out.printf("<h3>, you are %s years old</h3>", request.getParameter("age"));
		} else {
			out.println(new Date());
			out.println("<form method=\"post\">");

			if (name != null) {
				out.println("Name:<input type=\"text\" name=\"name\" value=\"" + name + "\">");
				if (!lengthCorrected(name)) {
					out.println("<font color=\"red\">Wrong length of name</font>");
				}
			} else {
				out.println("Name:<input type=\"text\" name=\"name\">");
			}
			out.println("<br>");

			if (password != null) {
				out.println("Password:<input type=\"password\" name=\"password\" value=\""
						+ password + "\">");
				if (!lengthCorrected(password)) {
					out.println("<font color=\"red\">Wrong length of password</font>");
				}
				if (!passwordPattern(password)) {
					out.println("<font color=\"red\">Password doesn't have at least one number</font>");
				}

			} else {
				out.println("Password:<input type=\"password\" name=\"password\">");
			}
			out.println("<br>");
			if (request.getParameter("age") != null) {
				out.println("Age:<input type =\"number\" name=\"age\" value=\"" + request.getParameter("age") + "\">");
				int intAge = Integer.parseInt(request.getParameter("age"));
				if (intAge > 150) {
					out.println("<font color=\"red\">You are too old!</font>");
				}
			} else {
				out.println("Age:<input type =\"number\" name=\"age\" value=\"0\">");
			}
			out.println("<br>");
			out.println("<select name=\"country\">");
			if (country != null || country == "none") {
				out.println("<option value=\"" + country + "\">"
						+ country + "</option>");
			} else {
				out.println("<option value=\"none\">--Select--</option>");
			}
			out.println("<option value=\"Afghanistan\">Afghanistan</option>");
			out.println("<option value=\"Åland Islands\">Åland Islands</option>");
			out.println("<option value=\"Albania\">Albania</option>");
			out.println("<option value=\"Algeria\">Algeria</option>");
			out.println("<option value=\"American Samoa\">American Samoa</option>");
			out.println("<option value=\"Andorra\">Andorra</option>");
			out.println("<option value=\"Angola\">Angola</option>");
			out.println("<option value=\"Anguilla\">Anguilla</option>");
			out.println("<option value=\"Antarctica\">Antarctica</option>");
			out.println("<option value=\"Antigua and Barbuda\">Antigua and Barbuda</option>");
			out.println("<option value=\"Argentina\">Argentina</option>");
			out.println("<option value=\"Armenia\">Armenia</option>");
			out.println("<option value=\"Aruba\">Aruba</option>");
			out.println("<option value=\"Australia\">Australia</option>");
			out.println("<option value=\"Austria\">Austria</option>");
			out.println("<option value=\"Azerbaijan\">Azerbaijan</option>");
			out.println("<option value=\"Bahamas\">Bahamas</option>");
			out.println("<option value=\"Bahrain\">Bahrain</option>");
			out.println("<option value=\"Bangladesh\">Bangladesh</option>");
			out.println("<option value=\"Barbados\">Barbados</option>");
			out.println("<option value=\"Belarus\">Belarus</option>");
			out.println("<option value=\"Belgium\">Belgium</option>");
			out.println("<option value=\"Belize\">Belize</option>");
			out.println("<option value=\"Benin\">Benin</option>");
			out.println("<option value=\"Bermuda\">Bermuda</option>");
			out.println("<option value=\"Bhutan\">Bhutan</option>");
			out.println("<option value=\"Bolivia, Plurinational State of\">Bolivia, Plurinational State of</option>");
			out.println("<option value=\"Bonaire, Sint Eustatius and Saba\">Bonaire, Sint Eustatius and Saba</option>");
			out.println("<option value=\"Bosnia and Herzegovina\">Bosnia and Herzegovina</option>");
			out.println("<option value=\"Botswana\">Botswana</option>");
			out.println("<option value=\"Bouvet Island\">Bouvet Island</option>");
			out.println("<option value=\"Brazil\">Brazil</option>");
			out.println("<option value=\"British Indian Ocean Territory\">British Indian Ocean Territory</option>");
			out.println("<option value=\"Brunei Darussalam\">Brunei Darussalam</option>");
			out.println("<option value=\"Bulgaria\">Bulgaria</option>");
			out.println("<option value=\"Burkina Faso\">Burkina Faso</option>");
			out.println("<option value=\"Burundi\">Burundi</option>");
			out.println("<option value=\"Cambodia\">Cambodia</option>");
			out.println("<option value=\"Cameroon\">Cameroon</option>");
			out.println("<option value=\"Canada\">Canada</option>");
			out.println("<option value=\"Cape Verde\">Cape Verde</option>");
			out.println("<option value=\"Cayman Islands\">Cayman Islands</option>");
			out.println("<option value=\"Central African Republic\">Central African Republic</option>");
			out.println("<option value=\"Chad\">Chad</option>");
			out.println("<option value=\"Chile\">Chile</option>");
			out.println("<option value=\"China\">China</option>");
			out.println("<option value=\"Christmas Island\">Christmas Island</option>");
			out.println("<option value=\"Cocos (Keeling) Islands\">Cocos (Keeling) Islands</option>");
			out.println("<option value=\"Colombia\">Colombia</option>");
			out.println("<option value=\"Comoros\">Comoros</option>");
			out.println("<option value=\"Congo\">Congo</option>");
			out.println(
					"<option value=\"Congo, the Democratic Republic of the\">Congo, the Democratic Republic of the</option>");
			out.println("<option value=\"Cook Islands\">Cook Islands</option>");
			out.println("<option value=\"Costa Rica\">Costa Rica</option>");
			out.println("<option value=\"CI\">Côte d\'Ivoire</option>");
			out.println("<option value=\"HR\">Croatia</option>");
			out.println("<option value=\"CU\">Cuba</option>");
			out.println("<option value=\"CW\">Curaçao</option>");
			out.println("<option value=\"CY\">Cyprus</option>");
			out.println("<option value=\"CZ\">Czech Republic</option>");
			out.println("<option value=\"DK\">Denmark</option>");
			out.println("<option value=\"DJ\">Djibouti</option>");
			out.println("<option value=\"DM\">Dominica</option>");
			out.println("<option value=\"DO\">Dominican Republic</option>");
			out.println("<option value=\"EC\">Ecuador</option>");
			out.println("<option value=\"EG\">Egypt</option>");
			out.println("<option value=\"SV\">El Salvador</option>");
			out.println("<option value=\"GQ\">Equatorial Guinea</option>");
			out.println("<option value=\"ER\">Eritrea</option>");
			out.println("<option value=\"EE\">Estonia</option>");
			out.println("<option value=\"ET\">Ethiopia</option>");
			out.println("<option value=\"FK\">Falkland Islands (Malvinas)</option>");
			out.println("<option value=\"FO\">Faroe Islands</option>");
			out.println("<option value=\"FJ\">Fiji</option>");
			out.println("<option value=\"FI\">Finland</option>");
			out.println("<option value=\"FR\">France</option>");
			out.println("<option value=\"GF\">French Guiana</option>");
			out.println("<option value=\"PF\">French Polynesia</option>");
			out.println("<option value=\"TF\">French Southern Territories</option>");
			out.println("<option value=\"GA\">Gabon</option>");
			out.println("<option value=\"GM\">Gambia</option>");
			out.println("<option value=\"GE\">Georgia</option>");
			out.println("<option value=\"DE\">Germany</option>");
			out.println("<option value=\"GH\">Ghana</option>");
			out.println("<option value=\"GI\">Gibraltar</option>");
			out.println("<option value=\"GR\">Greece</option>");
			out.println("<option value=\"GL\">Greenland</option>");
			out.println("<option value=\"GD\">Grenada</option>");
			out.println("<option value=\"GP\">Guadeloupe</option>");
			out.println("<option value=\"GU\">Guam</option>");
			out.println("<option value=\"GT\">Guatemala</option>");
			out.println("<option value=\"GG\">Guernsey</option>");
			out.println("<option value=\"GN\">Guinea</option>");
			out.println("<option value=\"GW\">Guinea-Bissau</option>");
			out.println("<option value=\"GY\">Guyana</option>");
			out.println("<option value=\"HT\">Haiti</option>");
			out.println("<option value=\"HM\">Heard Island and McDonald Islands</option>");
			out.println("<option value=\"VA\">Holy See (Vatican City State)</option>");
			out.println("<option value=\"HN\">Honduras</option>");
			out.println("<option value=\"HK\">Hong Kong</option>");
			out.println("<option value=\"HU\">Hungary</option>");
			out.println("<option value=\"IS\">Iceland</option>");
			out.println("<option value=\"IN\">India</option>");
			out.println("<option value=\"ID\">Indonesia</option>");
			out.println("<option value=\"IR\">Iran, Islamic Republic of</option>");
			out.println("<option value=\"IQ\">Iraq</option>");
			out.println("<option value=\"IE\">Ireland</option>");
			out.println("<option value=\"IM\">Isle of Man</option>");
			out.println("<option value=\"IL\">Israel</option>");
			out.println("<option value=\"IT\">Italy</option>");
			out.println("<option value=\"JM\">Jamaica</option>");
			out.println("<option value=\"JP\">Japan</option>");
			out.println("<option value=\"JE\">Jersey</option>");
			out.println("<option value=\"JO\">Jordan</option>");
			out.println("<option value=\"KZ\">Kazakhstan</option>");
			out.println("<option value=\"KE\">Kenya</option>");
			out.println("<option value=\"KI\">Kiribati</option>");
			out.println("<option value=\"KP\">Korea, Democratic People\'s Republic of</option>");
			out.println("<option value=\"KR\">Korea, Republic of</option>");
			out.println("<option value=\"KW\">Kuwait</option>");
			out.println("<option value=\"KG\">Kyrgyzstan</option>");
			out.println("<option value=\"LA\">Lao People\'s Democratic Republic</option>");
			out.println("<option value=\"LV\">Latvia</option>");
			out.println("<option value=\"LB\">Lebanon</option>");
			out.println("<option value=\"LS\">Lesotho</option>");
			out.println("<option value=\"LR\">Liberia</option>");
			out.println("<option value=\"LY\">Libya</option>");
			out.println("<option value=\"LI\">Liechtenstein</option>");
			out.println("<option value=\"LT\">Lithuania</option>");
			out.println("<option value=\"LU\">Luxembourg</option>");
			out.println("<option value=\"MO\">Macao</option>");
			out.println("<option value=\"MK\">Macedonia, the former Yugoslav Republic of</option>");
			out.println("<option value=\"MG\">Madagascar</option>");
			out.println("<option value=\"MW\">Malawi</option>");
			out.println("<option value=\"MY\">Malaysia</option>");
			out.println("<option value=\"MV\">Maldives</option>");
			out.println("<option value=\"ML\">Mali</option>");
			out.println("<option value=\"MT\">Malta</option>");
			out.println("<option value=\"MH\">Marshall Islands</option>");
			out.println("<option value=\"MQ\">Martinique</option>");
			out.println("<option value=\"MR\">Mauritania</option>");
			out.println("<option value=\"MU\">Mauritius</option>");
			out.println("<option value=\"YT\">Mayotte</option>");
			out.println("<option value=\"MX\">Mexico</option>");
			out.println("<option value=\"FM\">Micronesia, Federated States of</option>");
			out.println("<option value=\"MD\">Moldova, Republic of</option>");
			out.println("<option value=\"MC\">Monaco</option>");
			out.println("<option value=\"MN\">Mongolia</option>");
			out.println("<option value=\"ME\">Montenegro</option>");
			out.println("<option value=\"MS\">Montserrat</option>");
			out.println("<option value=\"MA\">Morocco</option>");
			out.println("<option value=\"MZ\">Mozambique</option>");
			out.println("<option value=\"MM\">Myanmar</option>");
			out.println("<option value=\"NA\">Namibia</option>");
			out.println("<option value=\"NR\">Nauru</option>");
			out.println("<option value=\"NP\">Nepal</option>");
			out.println("<option value=\"NL\">Netherlands</option>");
			out.println("<option value=\"NC\">New Caledonia</option>");
			out.println("<option value=\"NZ\">New Zealand</option>");
			out.println("<option value=\"NI\">Nicaragua</option>");
			out.println("<option value=\"NE\">Niger</option>");
			out.println("<option value=\"NG\">Nigeria</option>");
			out.println("<option value=\"NU\">Niue</option>");
			out.println("<option value=\"NF\">Norfolk Island</option>");
			out.println("<option value=\"MP\">Northern Mariana Islands</option>");
			out.println("<option value=\"NO\">Norway</option>");
			out.println("<option value=\"OM\">Oman</option>");
			out.println("<option value=\"PK\">Pakistan</option>");
			out.println("<option value=\"PW\">Palau</option>");
			out.println("<option value=\"PS\">Palestinian Territory, Occupied</option>");
			out.println("<option value=\"PA\">Panama</option>");
			out.println("<option value=\"PG\">Papua New Guinea</option>");
			out.println("<option value=\"PY\">Paraguay</option>");
			out.println("<option value=\"PE\">Peru</option>");
			out.println("<option value=\"PH\">Philippines</option>");
			out.println("<option value=\"PN\">Pitcairn</option>");
			out.println("<option value=\"PL\">Poland</option>");
			out.println("<option value=\"PT\">Portugal</option>");
			out.println("<option value=\"PR\">Puerto Rico</option>");
			out.println("<option value=\"QA\">Qatar</option>");
			out.println("<option value=\"RE\">Réunion</option>");
			out.println("<option value=\"RO\">Romania</option>");
			out.println("<option value=\"RU\">Russian Federation</option>");
			out.println("<option value=\"RW\">Rwanda</option>");
			out.println("<option value=\"BL\">Saint Barthélemy</option>");
			out.println("<option value=\"SH\">Saint Helena, Ascension and Tristan da Cunha</option>");
			out.println("<option value=\"KN\">Saint Kitts and Nevis</option>");
			out.println("<option value=\"LC\">Saint Lucia</option>");
			out.println("<option value=\"MF\">Saint Martin (French part)</option>");
			out.println("<option value=\"PM\">Saint Pierre and Miquelon</option>");
			out.println("<option value=\"VC\">Saint Vincent and the Grenadines</option>");
			out.println("<option value=\"WS\">Samoa</option>");
			out.println("<option value=\"SM\">San Marino</option>");
			out.println("<option value=\"ST\">Sao Tome and Principe</option>");
			out.println("<option value=\"SA\">Saudi Arabia</option>");
			out.println("<option value=\"SN\">Senegal</option>");
			out.println("<option value=\"RS\">Serbia</option>");
			out.println("<option value=\"SC\">Seychelles</option>");
			out.println("<option value=\"SL\">Sierra Leone</option>");
			out.println("<option value=\"SG\">Singapore</option>");
			out.println("<option value=\"SX\">Sint Maarten (Dutch part)</option>");
			out.println("<option value=\"Slovakia\">Slovakia</option>");
			out.println("<option value=\"SI\">Slovenia</option>");
			out.println("<option value=\"SB\">Solomon Islands</option>");
			out.println("<option value=\"SO\">Somalia</option>");
			out.println("<option value=\"ZA\">South Africa</option>");
			out.println("<option value=\"GS\">South Georgia and the South Sandwich Islands</option>");
			out.println("<option value=\"SS\">South Sudan</option>");
			out.println("<option value=\"ES\">Spain</option>");
			out.println("<option value=\"LK\">Sri Lanka</option>");
			out.println("<option value=\"SD\">Sudan</option>");
			out.println("<option value=\"SR\">Suriname</option>");
			out.println("<option value=\"SJ\">Svalbard and Jan Mayen</option>");
			out.println("<option value=\"SZ\">Swaziland</option>");
			out.println("<option value=\"SE\">Sweden</option>");
			out.println("<option value=\"CH\">Switzerland</option>");
			out.println("<option value=\"SY\">Syrian Arab Republic</option>");
			out.println("<option value=\"TW\">Taiwan, Province of China</option>");
			out.println("<option value=\"TJ\">Tajikistan</option>");
			out.println("<option value=\"TZ\">Tanzania, United Republic of</option>");
			out.println("<option value=\"TH\">Thailand</option>");
			out.println("<option value=\"TL\">Timor-Leste</option>");
			out.println("<option value=\"TG\">Togo</option>");
			out.println("<option value=\"TK\">Tokelau</option>");
			out.println("<option value=\"TO\">Tonga</option>");
			out.println("<option value=\"TT\">Trinidad and Tobago</option>");
			out.println("<option value=\"TN\">Tunisia</option>");
			out.println("<option value=\"TR\">Turkey</option>");
			out.println("<option value=\"TM\">Turkmenistan</option>");
			out.println("<option value=\"TC\">Turks and Caicos Islands</option>");
			out.println("<option value=\"TV\">Tuvalu</option>");
			out.println("<option value=\"UG\">Uganda</option>");
			out.println("<option value=\"UA\">Ukraine</option>");
			out.println("<option value=\"AE\">United Arab Emirates</option>");
			out.println("<option value=\"GB\">United Kingdom</option>");
			out.println("<option value=\"US\">United States</option>");
			out.println("<option value=\"UM\">United States Minor Outlying Islands</option>");
			out.println("<option value=\"UY\">Uruguay</option>");
			out.println("<option value=\"UZ\">Uzbekistan</option>");
			out.println("<option value=\"VU\">Vanuatu</option>");
			out.println("<option value=\"VE\">Venezuela, Bolivarian Republic of</option>");
			out.println("<option value=\"VN\">Viet Nam</option>");
			out.println("<option value=\"VG\">Virgin Islands, British</option>");
			out.println("<option value=\"VI\">Virgin Islands, U.S.</option>");
			out.println("<option value=\"WF\">Wallis and Futuna</option>");
			out.println("<option value=\"EH\">Western Sahara</option>");
			out.println("<option value=\"YE\">Yemen</option>");
			out.println("<option value=\"ZM\">Zambia</option>");
			out.println("<option value=\"ZW\">Zimbabwe</option>");
			out.println("</select>");
			out.println("<br>");
			out.println("<input type=\"submit\" value=\"Send!\">");
			out.println("</form>");
		}
		out.println("</body>");
		out.println("</html>");
	}

	private boolean passwordPattern(String password) {
		Pattern pattern = Pattern.compile("([a-z]*[A-Z]*[0-9]+)+");
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();

	}

	private boolean lengthCorrected(String name) {
		return name.length() <= 10 && name.length() >= 5;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
