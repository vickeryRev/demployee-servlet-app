package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.type.SerializationException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reavature.models.Employee;
import com.revature.dao.EmployeeDao;
import com.revature.service.EmployeeService;

public class RequestHelper {

	// employeeservice
	private static EmployeeService eserv = new EmployeeService(new EmployeeDao());
	// object mapper for(frontend)
	private static ObjectMapper om = new ObjectMapper();

	/*
	 * extract the params from a request (username and password) from the ui it will
	 * call the confimLogin() from EmployeeService, and will see if user and
	 * password exists.
	 * 
	 * whho will provide methods with the Http Request, the Ui will so need ato
	 * build an html dock with a form that can pass information to the server.
	 * 
	 */
	public static void processLogin(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SerializationException {

		// 1 : extract necessary parameters from the request
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// 2: call confirm laoging
		Employee e = eserv.confirmLogin(username, password);
		// 3: print infor t o the screne if they suer exsits
		if (e.getId() > 0) {
			// grab sessions
			HttpSession session = request.getSession();

			// add user to sessions
			session.setAttribute("the-user", e);

			// could also redirect to another resource

			// print out the user data with print writer

			PrintWriter out = response.getWriter();

			out.println("<h1>Welcome " + e.getFirstName() + ".<h1>");
			out.println("<h3>you have logged int succesfully</h3>");

			// could print object as ajson string

			String jString = om.writeValueAsString(e);
			out.println(jString);
		}
		// or we could redirect to another resource
		else {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("No User found!!!!1");
			// response.setStatus(204); //204 means successful connection to the server, but
			// no content found
		}
	}

	public static void processRegistration(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SerializationException, ServletException {
		// extract all vals form the params
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");

		// construct new employee object
		Employee e = new Employee(firstname, lastname, username, password);

		// call register form servixe layer
		int pk = eserv.register(e);

		// check the id, if its > 0 its successfull
		if (pk > 0) {
			e.setId(pk);
			// add em to the session
			HttpSession session = request.getSession();
			session.setAttribute("the-user", e);
			// useing request dispatcher, forward request and response to a new resource
			// send user to a new page --welcome.html
			request.getRequestDispatcher("welcome.html").forward(request, response);
		}

		// if it was -1 means reg failed
		else {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("<h1> Reg failed, username already exists</h1>");
			out.println("<a href=\"index.html\">Go Back!</a>");

		}

	}

	//trigers employee service find all
	// use an objext mapper to turn into a json string
	// useing pritn writer to print json string to the screen
	public static void processEmployees(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//set content type to be application/json
		response.setContentType("text/html");
		//call find all from emp service
		List<Employee> emps = eserv.getAll();
		//transform the list to a string
		String jsonString = om.writeValueAsString(emps);
		//write it out
		
		PrintWriter out = response.getWriter();
		out.write(jsonString);//write string to response body
	}
}
