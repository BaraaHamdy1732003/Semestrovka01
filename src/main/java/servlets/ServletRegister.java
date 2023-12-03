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
import java.io.IOException;

@WebServlet("/register")
public class ServletRegister extends HttpServlet {


    private UsersRepository usersRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        usersRepository = (UsersRepository) config.getServletContext().getAttribute("usersRep");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/registration.jsp");
        dispatcher.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String firstName =request.getParameter("firstName");
        String uname =request.getParameter("uname");
        int age = Integer.parseInt(request.getParameter("age"));
        String password =request.getParameter("password");
        User user = User.builder()
                .firstName(firstName)
                .uname(uname)
                .age(age)
                .password(password)
                .build();
        usersRepository.save(user);
        response.sendRedirect("/login");


    }
}



