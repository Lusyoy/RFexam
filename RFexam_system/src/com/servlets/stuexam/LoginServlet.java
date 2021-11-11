package com.servlets.stuexam;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Classtb;
import com.beans.Stu_info;
import com.beans.Testexam;
import com.dao.BaseDao;
import com.dao.Stu_infoDao;

@WebServlet("/servlet/LoginServlet")
public class LoginServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		Stu_infoDao<Stu_info> sd=new Stu_infoDao<Stu_info>();
		Stu_info stu_info= sd.login(id);
		if(stu_info!=null){
			//����ѧ�����
			request.getSession().setAttribute("stu_info", stu_info);
			//���ѧ�����classid�ҵ���Ӧ�༶
			BaseDao<Classtb> bd_classtb=new BaseDao<Classtb>();
			Classtb classtb= bd_classtb.getById("Classtb", "id", stu_info.getClassid());
			request.setAttribute("classtb", classtb);
			//��ݰ༶id�ҵ��ð༶���еĿ��Ծ���
			BaseDao<Testexam> bd_testexam=new BaseDao<Testexam>();
			ArrayList<Testexam> tlist= bd_testexam.getByWhere(" where classid="+classtb.getId(), "Testexam");
			if(tlist.size()==0){
				response.sendRedirect(request.getContextPath()+"/stuexam/fail.jsp");
			}else{
				request.setAttribute("tlist", tlist);
				//ת��
				request.getRequestDispatcher("/stuexam/begintest.jsp").forward(request, response);
			}
			
		}
	}

	 
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
