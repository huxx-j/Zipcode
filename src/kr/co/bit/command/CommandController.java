package kr.co.bit.command;

import kr.co.bit.dao.ZipcodeDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class CommandController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        this.doPost(req, resp);
        //System.out.println("in");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // TODO Auto-generated method stub
        String cmd = request.getParameter("cmd");
        cmd= cmd==null?"":cmd;
                String url = "";
        ZipcodeDAO dao = new ZipcodeDAO();

        if (cmd.equals("make")) {
            url = "./zip/result.jsp";
            String path = this.getServletContext().getRealPath("WEB-INF/file/zipcode.csv");
            boolean flag = dao.insert(path);

            request.setAttribute("result", flag ? "success" : "fail");
        } else if (cmd.equals("search")) {
//            String dong = request.getParameter("dong");
//            System.out.println(12+dong);
            url = "./zip/postal_print.jsp";

        }
        resp.setContentType("text/html; charset=UTF-8");

        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, resp);

    }

}
