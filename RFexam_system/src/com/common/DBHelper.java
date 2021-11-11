package com.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBHelper {
	public Connection conn;//���Ӷ���
	public PreparedStatement pst;//��������
	public DataSource ds;
	//�������Դ ds  ��mysql���ӳ�
	public DBHelper(){
		try {
			Context ctx= new InitialContext();
			ds=(DataSource)ctx.lookup("java:comp/env/exam_systemDB");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	//���Ӷ���
	public void getConnection(){
		try {
			conn=ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//��ɾ��
	public int execUpdate (String sql,Object...params){//����������װ��������Ĳ���ֵ
		System.out.println(sql);
		try {
			//��ȡ���Ӷ���
			getConnection();
			//��ȡ��������
			pst=conn.prepareStatement(sql);
			if(params!=null){
				//��������
				for(int i=0;i<params.length;i++){
					pst.setObject(i+1, params[i]);
				}
			}
			//ִ��
			return pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	
	
	
	//��
	public ResultSet execQuery (String sql,Object...params){//����������װ��������Ĳ���ֵ
		System.out.println(sql);
		try {
			//��ȡ���Ӷ���
			getConnection();
			//��ȡ��������
			pst=conn.prepareStatement(sql);
			if(params!=null){
				//��������
				for(int i=0;i<params.length;i++){
					pst.setObject(i+1, params[i]);
				}
			}
			//ִ��
			return pst.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	//�ر�����
	public void closeResource(ResultSet rs,PreparedStatement pst,Connection conn){
		try {
			if(rs!=null){
				rs.close();
			}
			if(pst!=null){
				pst.close();
			}
			if(conn!=null){
				conn.close();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
}
