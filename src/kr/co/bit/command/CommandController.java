package kr.co.bit.command;

import kr.co.bit.dao.ZipcodeDAO;
import kr.co.bit.vo.ZipcodeVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

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

        if (cmd.equals("make")) {
            url = "./zip/result.jsp";
            ZipcodeDAO dao = new ZipcodeDAO();
            String path = this.getServletContext().getRealPath("WEB-INF/file/zipcode.csv");
            boolean flag = dao.insert(path);

            request.setAttribute("result", flag ? "success" : "fail");
        } else if (cmd.equals("search")) {
            ZipcodeDAO dao = new ZipcodeDAO();
            String dong = request.getParameter("dong");
            List<ZipcodeVO> list = dao.search(dong);
            request.setAttribute("list",list);
//
            url = "./zip/postal.jsp";

        }
        resp.setContentType("text/html; charset=UTF-8");

        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, resp);

    }

}
