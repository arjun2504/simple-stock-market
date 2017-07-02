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
 * Servlet implementation class ViewShares
 */
public class ViewShares extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewShares() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("userid") != null) {
			
			Company company = new Company();
			ArrayList<Company> companyList = company.getAllCompanies();
			
			request.setAttribute("stocks", companyList);
			
			RequestDispatcher rd = request.getRequestDispatcher("ViewShares.jsp");
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
		HttpSession session = request.getSession();
		if(session.getAttribute("userid") != null && session.getAttribute("isadmin").equals("1")) {
			String action = request.getParameter("action");
			String companyId = request.getParameter("id");
			if(action.equals("edit")) {
				response.sendRedirect("edit-share?id=" + companyId);
			} else if(action.equals("delete")) {
				Company company = new Company();
				
				if(company.delete(companyId))
					response.sendRedirect("view-shares?deleted=1");
				else
					response.sendRedirect("view-shares?deleted=0");
			}
		}
		else {
			response.sendRedirect("login");
		}
 	}

}
