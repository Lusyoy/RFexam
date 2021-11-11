package com.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBHelper {
	public Connection conn;//连接对象
	public PreparedStatement pst;//操作对象
	public DataSource ds;
	//获得连接源 ds  用mysql连接池
	public DBHelper(){
		try {
			Context ctx= new InitialContext();
			ds=(DataSource)ctx.lookup("java:comp/env/exam_systemDB");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	//连接对象
	public void getConnection(){
		try {
			conn=ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//增删改
	public int execUpdate (String sql,Object...params){//该数组用于装？所代表的参数值
		System.out.println(sql);
		try {
			//获取连接对象
			getConnection();
			//获取操作对象
			pst=conn.prepareStatement(sql);
			if(params!=null){
				//遍历数组
				for(int i=0;i<params.length;i++){
					pst.setObject(i+1, params[i]);
				}
			}
			//执行
			return pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	
	
	
	//查
	public ResultSet execQuery (String sql,Object...params){//该数组用于装？所代表的参数值
		System.out.println(sql);
		try {
			//获取连接对象
			getConnection();
			//获取操作对象
			pst=conn.prepareStatement(sql);
			if(params!=null){
				//遍历数组
				for(int i=0;i<params.length;i++){
					pst.setObject(i+1, params[i]);
				}
			}
			//执行
			return pst.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	//关闭连接
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
