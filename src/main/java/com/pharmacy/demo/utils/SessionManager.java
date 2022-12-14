package com.pharmacy.demo.utils;
import com.pharmacy.demo.exceptions.AuthenticationException;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpSession;

@Component
public class SessionManager {
    public static final String LOGGED_USER_ID = "LOGGED_USER_ID";

    public int getLoggedId(HttpSession session) {
        if (session.getAttribute(LOGGED_USER_ID) == null) {
            throw new AuthenticationException("Not logged in");
        } else {
            return (int) session.getAttribute(LOGGED_USER_ID);
        }
    }

    public void loginUser(HttpSession session, int userId){
        session.setAttribute(LOGGED_USER_ID,userId);
    }
    public void logoutUser(HttpSession session){
        session.invalidate();
    }
}
