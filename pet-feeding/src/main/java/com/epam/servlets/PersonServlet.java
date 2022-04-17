package com.epam.servlets;

import com.epam.dao.PersonDaoImpl;
import com.epam.domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

public class PersonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //String htmlResponse = "<html><h3>Welcome to Servlet!</h3></html>";
        Enumeration<String> parameterList = req.getParameterNames();
        if (parameterList.hasMoreElements()) {
            String firstParameter = parameterList.nextElement();
            if (firstParameter.equals("id")){
                String[] parameterValueList = req.getParameterValues("id");
                Integer firstParamValue = Integer. valueOf(parameterValueList[0]);
                req.setAttribute("persons", PersonDaoImpl.instance().getById(firstParamValue));
                req.getRequestDispatcher("/person.jsp").forward(req, resp);

            } else if (firstParameter.equals("email")) {
                    String[] parameterValueList = req.getParameterValues("email");
                    String firstParamValue = parameterValueList[0];
                    req.setAttribute("persons", PersonDaoImpl.instance().getByEmail(firstParamValue));
                    req.getRequestDispatcher("/person.jsp").forward(req, resp);
            } else if (firstParameter.equals("all")) {
                List<Person> person = PersonDaoImpl.instance().getAll();
                req.setAttribute("persons", person);
                req.getRequestDispatcher("/person.jsp").forward(req, resp);
            }
        } else resp.setStatus(400);
    }
}
