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

/**
 * Servlet implementation class Sell
 */
@WebServlet("/Sell")
public class Sell extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sell() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("userid") != null) {
			Portfolio p = new Portfolio().getPortfolio( request.getParameter("id") );
			request.setAttribute("portfolio", p);
			RequestDispatcher rd = request.getRequestDispatcher("ViewPortfolio.jsp");
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
			
			String portfolioId = request.getParameter("id");
			String qty = request.getParameter("qty");
			
			Portfolio portfolio = new Portfolio().getPortfolio( portfolioId );
			Company company = portfolio.getCompany();
			User user = portfolio.getUser();
			
			if(portfolio.getShares() < Long.parseLong(qty)) {
				try {
					throw new InsufficientSharesException();
				}
				catch(InsufficientSharesException ise) {
					request.setAttribute("ise_error", ise.getMessage());
					doGet(request, response);
				}
			}
			else if(user.sell(qty, portfolio)) {
				response.sendRedirect("portfolio?success=1");
			}
			else {
				response.sendRedirect("portfolio?success=0");
			}
		}
		else {
			response.sendRedirect("login");
		}
	}

}
