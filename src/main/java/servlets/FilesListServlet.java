package servlets;

import repository.FilesRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/files")

public class FilesListServlet extends HttpServlet {
    private FilesRepository filesRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        filesRepository = (FilesRepository) config.getServletContext().getAttribute("filesRepository");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        req.setAttribute("FilesForJsp", filesRepository.findAll());
        request.getRequestDispatcher("jsp/files.jsp").forward(request, response);
    }
}
