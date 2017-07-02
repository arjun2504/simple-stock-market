package com.usersystem;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.actors.Company;
import com.actors.User;
import com.customexceptions.InsufficientSharesException;
import com.customexceptions.PurchaseLimitExceededException;

/**
 * Servlet implementation class Purchase
 */
@WebServlet("/Purchase")
public class Purchase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Purchase() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("userid") != null) {
			
			String companyId = request.getParameter("id");
			Company company = new Company().getCompany(companyId);
			
			request.setAttribute("company", company);
			RequestDispatcher rd = request.getRequestDispatcher("Purchase.jsp");
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
		if(session.getAttribute("userid") != null) {
			
			String companyId = request.getParameter("id");
			String qty = request.getParameter("qty");
			
			Company company = new Company().getCompany(companyId);
			User user = new User().getUser((String) session.getAttribute("userid"));
			
			long totalCost = Long.parseLong(qty) * company.getCurrentPrice();
			
			if(company.getAvailableShares() < Long.parseLong(qty)) {
				try {
					throw new InsufficientSharesException();
				}
				catch(InsufficientSharesException ise) {
					//response.sendRedirect("purchase?id=" + companyId + "&ise_error=" + ise.getMessage());
					request.setAttribute("ise_error", ise.getMessage());
					doGet(request, response);
				}
			} else if(totalCost > 100000) {
				try {
					throw new PurchaseLimitExceededException();
				}
				catch(PurchaseLimitExceededException ple) {
					//response.sendRedirect("purchase?id=" + companyId + "&ise_error=" + ise.getMessage());
					request.setAttribute("ple_error", ple.getMessage());
					doGet(request, response);
				}
			} else if(user.purchase(qty, company)) {
				response.sendRedirect("view-shares?mode=purchase&success=1");
			}
			else {
				response.sendRedirect("view-shares?mode=purchase&error=1");
			}
		}
		else {
			response.sendRedirect("login");
		}
	}

}
