package servlets;

import models.User;
import repository.UsersRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class ServerLogin extends HttpServlet {
    private UsersRepository usersRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        usersRepository = (UsersRepository) config.getServletContext().getAttribute("usersRep");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/login.jsp");
        dispatcher.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname = (String) request.getParameter("uname");
        String password = (String) request.getParameter("password");
        //ServerLogin loginAuth = new ServerLogin();
        // response.sendRedirect("/");
        // Assuming you have a method in your UsersRepository to check credentials
        boolean authenticationIsSuccessful = usersRepository.authenticateUser(uname, password);

        System.out.println("auth -> "+authenticationIsSuccessful);
        if (authenticationIsSuccessful) {
            // User authentication succeeded
            User user = usersRepository.findByEmail(uname);

            HttpSession session = request.getSession();
            session.setAttribute("authenticated", true);
            session.setAttribute("userId", user.getId());
            // Create a cookie for the email
//            Cookie emailCookie = new Cookie("user_email", email);
//            emailCookie.setMaxAge(60 * 60 * 24 * 30); // Cookie lasts for 30 days
//            response.addCookie(emailCookie);

            //   Redirect to the home page or wherever you want after login
            response.sendRedirect("/");
        } else {
            // User authentication failed

            // Redirect back to the login page with an error message or handle it accordingly
            response.sendRedirect("/");


        }
    }
}