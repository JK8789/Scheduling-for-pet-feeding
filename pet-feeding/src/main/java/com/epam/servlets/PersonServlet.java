package com.epam.servlets;

import com.epam.domain.Person;
import com.epam.services.PersonServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class PersonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration<String> parameterList = req.getParameterNames();
        if (!parameterList.hasMoreElements()) {
            List<Person> persons = PersonServiceImpl.instance().getAll();
            PrintWriter out = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.println(persons.toString());
//            req.setAttribute("persons", persons);
//            req.getRequestDispatcher("html/person.jsp").forward(req, resp);
            return;
        }
        if (parameterList.hasMoreElements()) {
            String firstParameter = parameterList.nextElement();
            if (firstParameter.equals("id")) {
                String[] parameterValueList = req.getParameterValues("id");
                Integer firstParamValue = 0;
                try {
                    firstParamValue = Integer.valueOf(parameterValueList[0]);
                } catch (NumberFormatException e) {
                    resp.sendError(400, "BAD_REQUEST. Id is not a number");
                    System.out.println(e + "Id is not a number");
                    return;
                }
                Optional<Person> person = PersonServiceImpl.instance().getById(firstParamValue);
                List<Person> persons = new LinkedList<>();
                if (person.isPresent()){
                    persons.add(person.get());
                }
                PrintWriter out = resp.getWriter();
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                out.println(persons);
//                req.setAttribute("persons", persons);
//                req.getRequestDispatcher("html/person.jsp").forward(req, resp);
                return;

            } else if (firstParameter.equals("email")) {
                String[] parameterValueList = req.getParameterValues("email");
                String firstParamValue = parameterValueList[0];
                Optional<Person> person = PersonServiceImpl.instance().getByEmail(firstParamValue);
                List<Person> persons = new LinkedList<>();
                if (person.isPresent()){
                    persons.add(person.get());
                }
                PrintWriter out = resp.getWriter();
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                out.println(persons);
//                req.setAttribute("persons", persons);
//                req.getRequestDispatcher("html/person.jsp").forward(req, resp);
                return;
            }
        }
    }
}
