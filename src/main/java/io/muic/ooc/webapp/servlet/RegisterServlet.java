package io.muic.ooc.webapp.servlet;

import com.ja.security.PasswordHash;
import io.muic.ooc.webapp.service.MySQLService;
import io.muic.ooc.webapp.service.SecurityService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Apple on 2/15/2017 AD.
 */
public class RegisterServlet extends HomeServlet {
    private SecurityService securityService;
    private MySQLService mySQLService;

    public void setSecurityManager(SecurityService securityService) {
        this.securityService = securityService;
    }

    public void setMySQLService(MySQLService mySQLService) {
        this.mySQLService = mySQLService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/register.jsp");
        rd.include(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO
        //make redirect when username is invalid


        String username = request.getParameter("username");
        String password = request.getParameter("password");


        if(!username.equals("") && !password.equals("") ){
//            String[] inp = {username,password};

            try {
                String hashPass = new PasswordHash().createHash(password);

                if (!mySQLService.isInDatabase(username)) {

                    mySQLService.writeData(username, hashPass);
                    RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/login.jsp");
                    rd.include(request, response);

//
                } else {
                    System.out.println("This username has been used");
                    String failError = "This username has been used";
                    request.setAttribute("error", failError);

                }
            }
            catch (Exception e){
                e.printStackTrace();
            }


        }else{
            String nullError = "Username or Password is missing.";
            request.setAttribute("error",nullError);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/register.jsp");
            rd.include(request, response);



    }}

}
