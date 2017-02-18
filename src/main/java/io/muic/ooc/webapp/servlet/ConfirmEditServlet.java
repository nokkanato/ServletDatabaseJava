package io.muic.ooc.webapp.servlet;

import io.muic.ooc.webapp.service.MySQLService;
import io.muic.ooc.webapp.service.SecurityService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static io.muic.ooc.webapp.Webapp.editTracker;
import static io.muic.ooc.webapp.Webapp.tracker;

/**
 * Created by Apple on 2/17/2017 AD.
 */
public class ConfirmEditServlet extends HttpServlet{
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
        String newUsername = (String) request.getSession().getAttribute("ohla");
        String editId =  request.getParameter("iddd");
        System.out.println("\r\n");
        System.out.println(request.getAttribute("newID"));
        System.out.println(request.getSession().getAttribute("newID"));
        System.out.println(request.getParameter("newID"));

//        System.out.println(request.getParameter("ohla"));
//        System.out.println(newUsername);
//        System.out.println(editId);
//        System.out.println("hello" +editTracker.getId());
//        System.out.println("hello" +editTracker.getUsername());
//        mySQLService.updateDB(editId,newUsername);


        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/confirmedit.jsp");
        rd.include(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
