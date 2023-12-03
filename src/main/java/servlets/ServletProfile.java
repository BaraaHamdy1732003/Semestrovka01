package servlets;

import models.User;
import repository.UsersRepository;
import repository.UsersRepositoryJdbcImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

@WebServlet("/profile")

public class ServletProfile extends HttpServlet {
    private UsersRepository usersRepository;
    @Override
    public void init(ServletConfig config) throws ServletException {
        usersRepository = (UsersRepository) config.getServletContext().getAttribute("usersRep");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if (userId !=null){
            User user= usersRepository.findById(userId);
            if(user !=null){
                request.setAttribute("user", user);
                RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/profile.jsp");
                dispatcher.forward(request,response);
            }else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
            } }else {
            response.sendRedirect("/login");
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String uname = request.getParameter("uname");
        int age = Integer.parseInt(request.getParameter("age"));

        User updatedUser = User.builder()
                .id(userId)
                .firstName(firstName)
                .uname(uname)
                .age(age)
                .build();

        // Update the user profile
        usersRepository.updateUserProfile(updatedUser);

        // Redirect to the user's profile page
        response.sendRedirect("/"); // Change this URL based on your setup
    }
}
