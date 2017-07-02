package com.administration;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.actors.Company;

/**
 * Servlet implementation class EditShares
 */

public class EditShares extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditShares() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("userid") != null && session.getAttribute("isadmin").equals("1")) {
			
			String companyId = request.getParameter("id");
			Company company = new Company().getCompany(companyId);
			request.setAttribute("company", company);
			
			RequestDispatcher rd = request.getRequestDispatcher("EditShares.jsp");
			rd.forward(request, response);
		}
		else {
			response.sendRedirect("login");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String totalShares = request.getParameter("totalshares");
		String availShares = request.getParameter("availableshares");
		String price = request.getParameter("price");
		String id = request.getParameter("id");
		
		Company company = new Company();
		
		if(company.edit(id, name, totalShares, availShares, price))
			response.sendRedirect("view-shares?edited=1");
		else
			response.sendRedirect("view-shares?edited=0");
			
		
	}

}
