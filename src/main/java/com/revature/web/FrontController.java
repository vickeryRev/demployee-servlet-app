package com.revature.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */
public class FrontController extends HttpServlet {

	/**
	 * this method is responsible for determining what resource the client is
	 * requesting
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1- URI rewriting
		// ex blah/employer-servlet-app/loging, waht to capture the log in

		final String URI = request.getRequestURI().replace("/demployee-servlet-app/", "");
		//replacing part of in the quotes with blank nothing, leaves just the nd of the uri.

		// set up a swich case statement in which we call the functionality based on URI
		// returned

		switch (URI) {
		case "login":
			// invoke a function from the reuqest helper
			RequestHelper.processLogin(request, response);
			break;
		case "employees":
			RequestHelper.processEmployees(request,response);
			// invoke a fucntion that reutrn all employees
			break;
		case "register":
			RequestHelper.processRegistration(request, response);
			break;
		default:
			// custom erro page
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
