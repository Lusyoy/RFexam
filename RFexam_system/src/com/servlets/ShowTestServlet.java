package com.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Examination;
import com.dao.BaseDao;

public class ShowTestServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        //设置request编码为UTF-8
        request.setCharacterEncoding("UTF-8");

		// set request utf-8
	    String uid = request.getParameter("UID");
	    uid = new String(uid.getBytes("ISO-8859-1"), "UTF-8");

		BaseDao<Examination> bd=new BaseDao<Examination>();
		ArrayList<Examination> elist= bd.getByWhere("", "Examination");
		request.setAttribute("elist", elist);
		request.getRequestDispatcher("/showtest.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
