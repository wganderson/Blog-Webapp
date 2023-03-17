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
import ryerson.ca.lab4helper.PostXML;
import ryerson.ca.lab4helper.UserInfo;
import ryerson.ca.lab4helper.UserXML;

/**
 *
 * @author student
 */
@WebServlet(name = "BrowseUser", urlPatterns = {"/BrowseUser"})
public class BrowseUser extends HttpServlet {

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
        System.out.println("Creator page name: " + request.getParameter("browseTo"));
        PostXML pxml = retreiveFeedFromBackend((String) request.getParameter("browseTo"));
        request.getSession().setAttribute("browseTo", (String) request.getParameter("browseTo"));
        request.getSession().setAttribute("feed", pxml);
        UserXML result = retreiveSubscriptionsFromBackend((String) request.getSession().getAttribute("username"));
        request.getSession().setAttribute("subscriptions", result);
        ArrayList<UserInfo> subscribed = result.getFeed();
        if (request.getSession().getAttribute("subbed") == null) {
            for (int i = 0; i < subscribed.size(); i++) {
                if (subscribed.get(i).getUsername().equals(request.getParameter("browseTo"))) {
                    request.getSession().setAttribute("subbed", true);
                }
            }
            if(request.getSession().getAttribute("subbed") == null){
                request.getSession().setAttribute("subbed", false);
            }
        }

        RequestDispatcher rd = request.getRequestDispatcher("Blog.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);

    }

    private PostXML retreiveFeedFromBackend(String username) {
        try {
            return (Business.getFeed(username));
        } catch (IOException ex) {
            Logger.getLogger(FrontEndIn.class.getName()).log(Level.SEVERE, null, ex);
            return (null);
        }
    }

    private UserXML retreiveSubscriptionsFromBackend(String username) {
        try {
            return (Business.getSubscriptions(username));
        } catch (IOException ex) {
            Logger.getLogger(FrontEndIn.class.getName()).log(Level.SEVERE, null, ex);
            return (null);
        }
    }
}
