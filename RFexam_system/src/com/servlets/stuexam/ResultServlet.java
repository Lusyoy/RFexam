package com.servlets.stuexam;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Stu_info;
import com.beans.Stutest;
import com.dao.BaseDao;

public class ResultServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int eid=Integer.parseInt(request.getParameter("eid"));
		int tid=Integer.parseInt(request.getParameter("tid"));
		int istrue=Integer.parseInt(request.getParameter("istrue"));
		Stu_info stu_info=(Stu_info)request.getSession().getAttribute("stu_info");
		BaseDao<Stutest> bd=new BaseDao<Stutest>();
		//先找到对应的那行数据改变istrue
		ArrayList<Stutest> list= bd.getByWhere(" where sid="+stu_info.getId()+" and eid="+eid+" and tid="+tid, "Stutest");
		Stutest s=list.get(0);
		s.setIstrue(istrue);
		bd.upd("Stutest", s, "id");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
