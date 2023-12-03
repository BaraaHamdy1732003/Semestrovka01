package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.FileInfo;
import repository.FileService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
@WebServlet("/insert")
@MultipartConfig

public class InsertFiles extends HttpServlet {
    private ObjectMapper objectMapper= new ObjectMapper();
    private FileService fileService;

    public void init(ServletConfig config )throws ServletException{
        this.fileService= (FileService) config.getServletContext().getAttribute("filesUploadService");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.getRequestDispatcher("jsp/insert.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
        Part part= request.getPart("file");
        System.out.println(part.getInputStream());
        fileService.saveFileToStorage(part.getInputStream(),part.getSubmittedFileName(),part.getContentType(),part.getSize());
        response.sendRedirect("/insert");
    }
}
