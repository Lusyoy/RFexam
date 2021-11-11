package com.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Examination;
import com.dao.BaseDao;

public class AddTestServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int courseid=Integer.parseInt(request.getParameter("courseid"));
		String question=request.getParameter("question");
		String ansA=request.getParameter("ansA");
		String ansB=request.getParameter("ansB");
		String ansC=request.getParameter("ansC");
		String ansD=request.getParameter("ansD");
		String ans=request.getParameter("ans");
		BaseDao<Examination> bd=new BaseDao<Examination>();
		bd.add(new Examination(-1, courseid, question, ansA, ansB, ansC, ansD, ans), "Examination", "id");
		response.sendRedirect(request.getContextPath()+"/servlet/ShowTestServlet");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
