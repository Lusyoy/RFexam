package com.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Classtb;
import com.beans.Course;
import com.dao.BaseDao;

public class GetCourseAndClasstbServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//�õ���Ŀ
		BaseDao<Course> bd=new BaseDao<Course>();
		ArrayList<Course> clist= bd.getByWhere("", "Course");
		request.setAttribute("clist", clist);
		//�õ��༶
		BaseDao<Classtb> bd2=new BaseDao<Classtb>();
		ArrayList<Classtb> classList=bd2.getByWhere("", "Classtb");
		request.setAttribute("classList", classList);
		request.getRequestDispatcher("/createtest.jsp").forward(request, response);

		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
