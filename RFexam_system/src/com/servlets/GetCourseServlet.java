package com.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Course;
import com.dao.BaseDao;

public class GetCourseServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BaseDao<Course> bd=new BaseDao<Course>();
		ArrayList<Course> clist= bd.getByWhere("", "Course");
		request.setAttribute("clist", clist);
		request.getRequestDispatcher("/addtest.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
