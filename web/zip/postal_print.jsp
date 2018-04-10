<%@ page import="kr.co.bit.dao.ZipcodeDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="kr.co.bit.vo.ZipcodeVO" %>
<%@ page import="java.util.ArrayList" %>
<%
    request.setCharacterEncoding("UTF-8");
%><%--
  Created by IntelliJ IDEA.
  User: bit-user
  Date: 2018-04-09
  Time: 오후 5:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    ArrayList<ZipcodeVO> list = (ArrayList<ZipcodeVO>) request.getAttribute("list");
    for(ZipcodeVO vo : list) {
        out.println(vo.toString() + "<br>");
    }

//    String dong = request.getParameter("dong");
//    List<ZipcodeVO> list;
//    ZipcodeDAO dao = new ZipcodeDAO();
//    list = dao.search(dong);
//    for (ZipcodeVO vo :list) {
//        System.out.println(vo.getZipcode());
//        System.out.println(vo.getSido());
//        System.out.println(vo.getGugun());
//        System.out.println(vo.getDong());
//        System.out.println(vo.getRi());
//        System.out.println(vo.getBldg());
//        System.out.println(vo.getBunji());
//        System.out.println(vo.getSeq());
//    }
%>
</body>
</html>
