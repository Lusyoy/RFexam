package com.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Examination;
import com.beans.Finalexam;
import com.beans.Testexam;
import com.dao.BaseDao;
import com.dao.ExaminationDao;
import com.dao.GetIdDao;

public class CreateTestServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int courseid=Integer.parseInt(request.getParameter("courseid"));
		int classid=Integer.parseInt(request.getParameter("classid"));
		int num=Integer.parseInt(request.getParameter("num"));
		String examname=request.getParameter("examname");
		//锟斤拷锟斤拷锟捷碉拷锟皆撅拷锟�
		BaseDao<Testexam> bd_testexam=new BaseDao<Testexam>();
		bd_testexam.add(new Testexam(-1, examname, classid), "Testexam", "id");
		GetIdDao getIdDao=new GetIdDao();
		//锟矫碉拷锟斤拷锟斤拷锟斤拷锟絋estexam锟斤拷莸锟斤拷锟斤拷锟絠d(tid)
		int tid=getIdDao.getLastId();
		
		//锟斤拷锟斤拷锟斤拷锟斤拷涂锟侥匡拷锟斤拷锟斤拷锟斤拷锟揭拷锟斤拷锟斤拷募锟斤拷锟�elist
		ExaminationDao ed=new ExaminationDao();
		ArrayList<Examination> elist= ed.getRandomTest(num, courseid);
		
		BaseDao<Finalexam> bd_finalexam=new BaseDao<Finalexam>();
		//通锟斤拷锟斤拷锟絜list锟斤拷锟斤拷锟斤拷锟絝inalexam 锟斤拷
		for(Examination e:elist){
			bd_finalexam.add(new Finalexam(-1, e.getId(), tid), "Finalexam", "id");
		}
			
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}
