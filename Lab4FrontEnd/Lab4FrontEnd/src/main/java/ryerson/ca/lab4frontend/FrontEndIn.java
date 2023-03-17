/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.lab4frontend;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.AbstractMap;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.NewCookie;
import ryerson.ca.lab4business.Business;
import ryerson.ca.lab4helper.PostXML;
import ryerson.ca.lab4helper.UserInfo;
import ryerson.ca.lab4helper.UserXML;

/**
 *
 * @author student
 */
@WebServlet(name = "FrontEnd", urlPatterns = {"/FrontEnd"})
public class FrontEndIn extends HttpServlet {

    Authenticate autho;

    public FrontEndIn() {
        autho = new Authenticate();
    }
    private final String authenticationCookieName = "login_token";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private Map.Entry<String, String> isAuthenticated(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String token = "";

        System.out.println("TOKEN IS");
        try {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName());
                if (cookie.getName().equals(authenticationCookieName)) {
                    token = cookie.getValue();
                }
            }
        } catch (Exception e) {

        }
        if (!token.isEmpty())
           try {
            if (this.autho.verify(token).getKey()) {
                Map.Entry entry = new AbstractMap.SimpleEntry<String, String>(token, this.autho.verify(token).getValue());
                return entry;

            } else {
                Map.Entry entry = new AbstractMap.SimpleEntry<String, String>("", "");
                return entry;
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FrontEndIn.class.getName()).log(Level.SEVERE, null, ex);
        }

        Map.Entry entry = new AbstractMap.SimpleEntry<String, String>("", "");
        return entry;

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("processing request");
        String token = isAuthenticated(request).getKey();
        String uname = isAuthenticated(request).getValue();
        String username = request.getParameter("username");
        String passwrod = request.getParameter("password");
        boolean isAuthenticated = Business.isAuthenticated(username, passwrod);
        System.out.println(isAuthenticated);
        if (isAuthenticated) {
            UserXML result = retreiveSubscriptionsFromBackend(username);
            request.setAttribute("subscriptions", result);
            request.setAttribute("username", username);
            request.getSession().setAttribute("username", username);
            token = autho.createJWT("FrontEnd", username, 100000);

            Cookie newCookie = new Cookie(authenticationCookieName, token);
            response.addCookie(newCookie);
            System.out.println("FrontEnd.java: " + request.getAttribute("username"));
            System.out.println("FrontEnd.java(session): " + request.getSession().getAttribute("username"));
            RequestDispatcher requestDispatcher = request.
                    getRequestDispatcher("UserHomePage.jsp");

            requestDispatcher.forward(request, response);

        } else {
            RequestDispatcher requestDispatcher = request.
                    getRequestDispatcher("LoginFailed.jsp");

            requestDispatcher.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private UserXML retreiveSubscriptionsFromBackend(String username) {
        try {
            return (Business.getSubscriptions(username));
        } catch (IOException ex) {
            Logger.getLogger(FrontEndIn.class.getName()).log(Level.SEVERE, null, ex);
            return (null);
        }

    }

    private UserInfo retreiveUserFromBackend(String username) {
        try {
            return (Business.getUser(username));
        } catch (IOException ex) {
            Logger.getLogger(FrontEndIn.class.getName()).log(Level.SEVERE, null, ex);
            return (null);
        }

    }

    public static UserXML retreiveSubscriptionsFromBackendTest(String username) {
        try {
            return (Business.getSubscriptions(username));
        } catch (IOException ex) {
            Logger.getLogger(FrontEndIn.class.getName()).log(Level.SEVERE, null, ex);
            return (null);
        }

    }

}
