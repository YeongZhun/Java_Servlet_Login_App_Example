package loginservlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import loginbean.LoginBean;
import logindao.LoginDao;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDao loginDao;

	@Override
	public void init() {
		loginDao = new LoginDao();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		LoginBean loginBean = new LoginBean();
		loginBean.setUsername(username);
		loginBean.setPassword(password);

		try {
			if (loginDao.validate(loginBean)) {
			    HttpSession session = request.getSession();
			    session.setAttribute("name", loginBean.getName());
			    session.setAttribute("phoneNumber", loginBean.getPhoneNumber());

			    response.sendRedirect("loginsuccess.jsp");
			}
			else {
				 response.sendRedirect("login.jsp"); // Redirect back to login page
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}