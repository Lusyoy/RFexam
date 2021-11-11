package com.dao;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.common.DBHelper;

//����=����   ����=��
public class BaseDao<T> {
	DBHelper helper=new DBHelper();
	
	//���
	public int add(T t,String className,String pk){
		int n=0;
		try {
			String classPath="com.beans."+className;
			//ͨ���ַ����������� clazz
			Class clazz=Class.forName(classPath);
			//�õ���������
			Field[] fields=clazz.getDeclaredFields();
			//�õ���������
			Method[]methods=clazz.getDeclaredMethods();
			//insert into ��(��1,��2..) values (ֵ1,ֵ2..)
			//insert into dog(name,age) values (?,?)
			//����sql
			String wh="";
			String sql="insert into "+className+" (";
			for(Field f:fields){
				//�ų�������
				if(!f.getName().equals(pk)){
					sql+=f.getName()+",";
					wh+="?,";
				}
			}
			//��ȡ���һ������
			sql=sql.substring(0, sql.length()-1);
			wh=wh.substring(0, wh.length()-1); 
			sql+=") values ("+wh+")";
			System.out.println(sql);
			
			//���� ֵparams
			Object[]params=new Object[fields.length-1];
			int index=0;
			for(Field f:fields){ 
				//�ų�������
				if(!f.getName().equals(pk)){  // name age
					for(Method m:methods){ //set getXXX
						//�õ����е�get����
						if(m.getName().substring(0, 3).equals("get")&&m.getName().substring(3).equalsIgnoreCase(f.getName())){ //getName getAge getId
							//�ҵ��˺����Զ����getXXX������
							//ִ�и÷���
							params[index]= m.invoke(t, null); 
							index++;
							break;
						}
					}
				}
			}
			//��������params
//			for(Object o:params){
//				System.out.println(o);
//			}
			
			
			n=helper.execUpdate(sql, params);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			helper.closeResource(null, helper.pst, helper.conn);
		}
		return n;
	}
	
	//ɾ��
	public int del(String className,String pk,int id){
		//delete from �� where id=? 
		//����sql
		String sql="delete from "+className+" where "+pk+"=?";
		int n=helper.execUpdate(sql, id);
		helper.closeResource(null, helper.pst, helper.conn);
		return n;
	}
	
	//�޸�
	public int upd(String className,T t,String pk){
		int n=0;
		try {
			//update �� set ��1=?,.... where id=?
			String classPath="com.beans."+className;
			Class clazz=Class.forName(classPath);
			//�õ���������
			Field[] fields=clazz.getDeclaredFields();
			//�õ���������
			Method[]methods=clazz.getDeclaredMethods();
			//����sql
			String sql="update "+className+" set ";
			for(Field f:fields){
				if(!f.getName().equals(pk)){
					sql+=f.getName()+"=?,";
				}
			}
			//��ȡ���һ������
			sql=sql.substring(0, sql.length()-1);
			sql+=" where "+pk+"=?";
			
			//���� ֵparams
			Object[]params=new Object[fields.length];
			int index=0;
			for(Field f:fields){ 
				//�ų�������
				if(!f.getName().equals(pk)){  // name age
					for(Method m:methods){ //set getXXX
						//�õ����е�get����
						if(m.getName().substring(0, 3).equals("get")&&m.getName().substring(3).equalsIgnoreCase(f.getName())){ //getName getAge getId
							//�ҵ��˺����Զ����getXXX������
							//ִ�и÷���
							params[index]= m.invoke(t, null); 
							index++;
							break;
						}
					}
				}
			}
			//params���װ������ֵ
			for(Method m:methods){
				//�ҵ�����    getId
				if(m.getName().substring(0, 3).equals("get")&&m.getName().substring(3).toLowerCase().equals(pk)){
					params[index]=m.invoke(t, null);
					break;
				}
			}
			n=helper.execUpdate(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			helper.closeResource(null, helper.pst, helper.conn);
		}
		return n;
	}
	
	public T getById(String className,String pk,int id){
		
		ResultSet rs=null;
		try {
			String classPath="com.beans."+className;
			Class clazz=Class.forName(classPath);
			//select * from �� where id=?
			//����sql
			String sql="select * from "+className+" where "+pk+"=?";
			
			rs=helper.execQuery(sql, id);
			if(rs.next()){
				//�����¶���
				Object obj= clazz.newInstance();
				Method[]methods=clazz.getDeclaredMethods();
				for(Method m:methods){
					//�ҵ����е�set���� setName setAge
					if(m.getName().substring(0, 3).equals("set")){
						m.invoke(obj, rs.getObject(m.getName().substring(3).toLowerCase()));
					}
				}
				return (T)obj;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			helper.closeResource(rs, helper.pst, helper.conn);
		}
		return null;
	}
	
	public ArrayList<T> getByWhere(String where,String className){
		ResultSet rs=null;
		ArrayList<T> arr=new ArrayList<T>();
		try {
			String classPath="com.beans."+className;
			Class clazz=Class.forName(classPath);
			//select * from �� where id=?
			//����sql
			String sql="select * from "+className+" "+where;
			
			rs=helper.execQuery(sql, null);
			while(rs.next()){
				//�����¶���
				Object obj= clazz.newInstance();
				Method[]methods=clazz.getDeclaredMethods();
				for(Method m:methods){
					//�ҵ����е�set���� setName setAge
					if(m.getName().substring(0, 3).equals("set")){
						m.invoke(obj, rs.getObject(m.getName().substring(3).toLowerCase()));
					}
				}
				arr.add((T)obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			helper.closeResource(rs, helper.pst, helper.conn);
		}
		return arr;
	}
	
	//��ҳ
	public ArrayList<T> getBypage(String where,String className,int pageindex,int pagesize){
		ResultSet rs=null;
		ArrayList<T> arr=new ArrayList<T>();
		try {
			String classPath="com.beans."+className;
			Class clazz=Class.forName(classPath);
			//����sql
			String sql="select * from "+className+" limit "+(pageindex-1)*pagesize+" , "+pagesize;
			rs=helper.execQuery(sql, null);
			while(rs.next()){
				//�����¶���
				Object obj= clazz.newInstance();
				Method[]methods=clazz.getDeclaredMethods();
				for(Method m:methods){
					//�ҵ����е�set���� setName setAge
					if(m.getName().substring(0, 3).equals("set")){
						m.invoke(obj, rs.getObject(m.getName().substring(3).toLowerCase()));
					}
				}
				arr.add((T)obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			helper.closeResource(rs, helper.pst, helper.conn);
		}
		return arr;
	}
	
	
	
	
	
	
}
