package ryerson.ca.lab4frontend;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ryerson.ca.lab4business.Business;

/**
 *
 * @author student
 */
@WebServlet(name = "Subscribe", urlPatterns = {"/Subscribe"})
public class Subscribe extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if((Boolean) request.getSession().getAttribute("subbed")){
            request.getSession().setAttribute("subbed", false);
            Business.getUnsub((String) request.getSession().getAttribute("username"), (String) request.getSession().getAttribute("browseTo"));
            System.out.println("subscribe.java: unsubbed");
        }
        else{
            request.getSession().setAttribute("subbed", true);
            Business.getSub((String) request.getSession().getAttribute("username"), (String) request.getSession().getAttribute("browseTo"));
            System.out.println("subscribe.java: subbed");
        }
        
        
        
        
        RequestDispatcher rd = request.getRequestDispatcher("Blog.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);

    }
}
