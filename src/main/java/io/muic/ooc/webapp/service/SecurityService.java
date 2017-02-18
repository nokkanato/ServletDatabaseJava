/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.muic.ooc.webapp.service;

import javax.servlet.http.HttpServletRequest;

import static io.muic.ooc.webapp.Webapp.tracker;

/**
 *
 * @author gigadot
 */
public class SecurityService {
    private SecurityService securityService;
    private MySQLService mySQLService;

    public void setMySQLManager(MySQLService mySQLManager) {
        this.mySQLService = mySQLManager;
    }

    public void setSecurityManager(SecurityService securityService) {
        this.securityService = securityService;
    }



    public boolean isAuthorized(HttpServletRequest request) {
        ///TODO
        String username = (String) request.getSession().getAttribute("username");

        return username != null;

    }
    public boolean isUsernamePassword(String username, String password){
        if (tracker.getPassword().equals(password) &&
                tracker.getUsername().equals(username)){
            return true;
        }
        return false;
    }



    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }



    
}
