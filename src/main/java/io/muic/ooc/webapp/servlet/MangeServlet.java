package io.muic.ooc.webapp.servlet;

import io.muic.ooc.webapp.service.MySQLService;
import io.muic.ooc.webapp.service.SecurityService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Apple on 2/16/2017 AD.
 */
public class MangeServlet extends HttpServlet {

    private SecurityService securityService;
    private MySQLService mySQLService;

    public void setSecurityManager(SecurityService securityService) {
        this.securityService = securityService;
    }
    public void setMySQLManager(MySQLService mySQLManager) {
        this.mySQLService = mySQLManager;
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/manage.jsp");
        rd.include(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String cpassword = request.getParameter("cpassword");
        if (!username.equals("") && !oldPassword.equals("") && !newPassword.equals("")
                && !cpassword.equals("")){

            if (cpassword.equals(newPassword)){







            }









        }else {
            String nullError = "Something is missing.";
            request.setAttribute("error",nullError);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/manage.jsp");
            rd.include(request, response);
        }

    }
}
