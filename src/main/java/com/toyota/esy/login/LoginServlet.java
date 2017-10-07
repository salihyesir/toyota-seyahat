package com.toyota.esy.login;

import com.toyota.esy.dto.UsersDTO;
import com.toyota.esy.service.UsersService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;// Derleyici uyarı vermesin JVM için gerekli daha hızlı serileştirme oluyor
	//daha hızlı byte dizisine çeviriliyor
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/jsp");
		String userMail=request.getParameter("userMail");
		String password=request.getParameter("password");

		UsersService usersService =new UsersService();
		 UsersDTO user = usersService.signInUser(userMail,new UsersDTO().encryption(password));


		if(user != null && user.getIsDeleted()== false)
		{
			HttpSession session = request.getSession(true); // reuse existing
			// session if exist
			// or create one
			session.setAttribute("rol",user.getUserRole().getId());
			session.setMaxInactiveInterval(30 * 60); // session 30 dk sonra sonlanacak şekilde ayarlandı
			//HTTP session nesnesini tanımlamak amaçlı
            Cookie userId = new Cookie("id", user.getId().toString());
			//cookie nin ömrü 30 dk
			//userId.setMaxAge(30*60);
			response.addCookie(userId);

			Cookie userName = new Cookie("userName", user.getUserName());
			//userName.setMaxAge(30*60);
			response.addCookie(userName);
			//Session nesnesinin takibi için kullanılacak
			Cookie mail = new Cookie("mail", user.getUserMail());
			response.addCookie(mail);
			if (user.getUserRole().getId()== 1 ) {
				response.sendRedirect("/index.jsp");
			}
			else if ( user.getUserRole().getId()== 2)
			{
				response.sendRedirect("/Admin.jsp");
			}
		}else{
            Cookie err = new Cookie("err", URLEncoder.encode("Geçeriz kullanıcı adı veya parola girdiniz!", "UTF-8"));
            err.setMaxAge(1);
            response.addCookie(err);//cookie eklendi
			response.sendRedirect("/login.jsp");
		}
	}
}
