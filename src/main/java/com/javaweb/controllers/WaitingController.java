package com.javaweb.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.javaweb.models.UserModel;

/**
 * Servlet implementation class WaitingController
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/waiting")
public class WaitingController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		if (session != null && session.getAttribute("account") != null) {
			UserModel u = (UserModel) session.getAttribute("account");
			session.setAttribute("account", u);
			req.setAttribute("account", u);
			if (u.getRoleid() == 1) {	
				RequestDispatcher rd =req.getRequestDispatcher("/views/home.jsp");
				rd.forward(req, resp);
			} else if (u.getRoleid() == 2) {
				resp.sendRedirect(req.getContextPath() + "/manager/home");
			} else {
				resp.sendRedirect(req.getContextPath() + "/home");
			}
		} else {
			RequestDispatcher rd =req.getRequestDispatcher("/views/home.jsp");
			rd.forward(req, resp);
		}
	}
}
