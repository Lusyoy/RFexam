package com.servlets.stuexam;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Stu_info;
import com.beans.Stutest;
import com.beans.V_test;
import com.dao.BaseDao;


public class StartTestServlet extends HttpServlet {    
 
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int tid = Integer.parseInt(request.getParameter("tid"));
		BaseDao<V_test> bd = new BaseDao<V_test>();
		ArrayList<V_test> vlist= bd.getByWhere(" where tid="+tid, "V_test");
		request.setAttribute("vlist", vlist);
		BaseDao<Stutest> bd_stutest=new BaseDao<Stutest>();
		Stu_info stu_info=(Stu_info)request.getSession().getAttribute("stu_info");
		//先根据学生id和考卷id找是否有内容
		ArrayList<Stutest> list=bd_stutest.getByWhere(" where sid="+stu_info.getId()+" and tid="+tid, "Stutest");
		if(list.size()==0){
			//为学生答题表添加数据
			for(V_test v:vlist){
				bd_stutest.add(new Stutest(-1, stu_info.getId(), v.getEid(), 0, tid), "Stutest", "id");
			}
		}
		request.getRequestDispatcher("/stuexam/test.jsp").forward(request, response);
		
		
	}

 
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
