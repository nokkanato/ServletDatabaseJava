/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.muic.ooc.webapp.servlet;

import io.muic.ooc.webapp.service.MySQLService;
import io.muic.ooc.webapp.service.SecurityService;
import io.muic.ooc.webapp.service.Tracker;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static io.muic.ooc.webapp.Webapp.tracker;

/**
 *
 * @author gigadot
 */
public class LoginServlet extends HttpServlet {

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
        request.getSession().invalidate();
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/login.jsp");
        rd.include(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // do login post logic
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try{
            ArrayList<String[]> read = mySQLService.readData();
        }catch (Exception e){
            e.printStackTrace();
        }


        if(!username.equals("") && !password.equals("")){


            // authenticate
            tracker.setUsername(username);
            request.getSession().setAttribute("trackerUsername", tracker.getUsername());

//            tracker.setPassword(password);
            String[] inp = {username,password};
            try {
                if (mySQLService.isValidInput(inp)) {
                    request.getSession().setAttribute("username", username);
                    response.sendRedirect("/");

                } else {
                    String failError = "Wrong username or password.";
                    request.setAttribute("error", failError);
                    RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/login.jsp");
                    rd.include(request, response);
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else{
            String nullError = "Username or Password is missing.";
            request.setAttribute("error",nullError);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/login.jsp");
            rd.include(request, response);
        }

    }




    }



