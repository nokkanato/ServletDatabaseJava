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
public class DeleteServlet extends HttpServlet {
    private SecurityService securityService;
    private MySQLService mySQLService;

    public void setMySQLManager(MySQLService mySQLManager) {
        this.mySQLService = mySQLManager;
    }


    public void setSecurityManager(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getParameter("userr"));

        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/delete.jsp");
            rd.include(request, response);
            mySQLService.deleteDB(request.getParameter("userr"));

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/delete.jsp");
        rd.include(request, response);
    }
}
