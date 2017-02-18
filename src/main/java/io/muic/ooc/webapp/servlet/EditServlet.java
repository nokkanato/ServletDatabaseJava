package io.muic.ooc.webapp.servlet;

import io.muic.ooc.webapp.service.EditTracker;
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
 * Created by Apple on 2/16/2017 AD.
 */
public class EditServlet extends HttpServlet {

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
        String uname = request.getParameter("yim");



        request.getSession().setAttribute("newID",editId);
        request.getSession().setAttribute("newUname", uname);




        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/edit.jsp");
        rd.include(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String editId =  (String)request.getSession().getAttribute("newID");
        String uname = request.getParameter("hola");
        System.out.println("postttttttttt" + "\r\n");
//        System.out.println(request.getSession().getAttribute("newID"));
//        System.out.println(request.getParameter("hola"));

        System.out.println(editId);
        System.out.println(uname);

//
//
//
//




        mySQLService.updateDB(editId,uname);
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/home.jsp");
        rd.include(request, response);



    }



}
