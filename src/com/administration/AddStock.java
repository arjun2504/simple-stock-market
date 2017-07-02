package com.administration;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.actors.Company;

/**
 * Servlet implementation class AddStock
 */
@WebServlet("/AddStock")
public class AddStock extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStock() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("userid") != null && session.getAttribute("isadmin").equals("1")) {
			RequestDispatcher rd = request.getRequestDispatcher("AddStock.jsp");
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
		
		Company company = new Company();
		String companyId = company.addCompany(name, totalShares, availShares, price);
		
		response.sendRedirect("add?added=" + companyId);
	}

}
