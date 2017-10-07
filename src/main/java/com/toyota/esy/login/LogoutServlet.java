package com.toyota.esy.login;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/jsp");
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}
		HttpSession session = request.getSession(false);
		if(session != null){
			session.invalidate();
		}
		response.sendRedirect("/login.jsp");
	}
}