package by.tms.strore.Listener;


import by.tms.strore.entity.Basket;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class Listener implements HttpSessionListener {//WebListener

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setAttribute("basket", new Basket());
        System.out.println("listener!!!!!!!!!!");
        se.getSession().setAttribute("isGuest", true);
        se.getSession().setAttribute("isUser", false);
        se.getSession().setAttribute("isAdmin", false);
        se.getSession().setAttribute("isManager", false);
    }
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }
}
