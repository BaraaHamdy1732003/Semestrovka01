package servlets;

import models.FileInfo;
import repository.FileService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/upload")
@MultipartConfig
public class FilesUploadServlet extends HttpServlet {
    private FileService fileService;

    public void init(ServletConfig config )throws ServletException{
        this.fileService= (FileService) config.getServletContext().getAttribute("filesUploadService");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String fileId= request.getParameter("id");
        FileInfo fileInfo= fileService.getFileInfo(Long.parseLong(fileId));
        response.setContentType(fileInfo.getType());
        response.setContentLength(fileInfo.getSize().intValue());
        response.setHeader("Content-Discription", "filename=\"" + fileInfo.getOriginalFileName() + "\"");
        fileService.writeFileFormStorage(Long.parseLong(fileId), response.getOutputStream());
        response.flushBuffer();
    }
}
