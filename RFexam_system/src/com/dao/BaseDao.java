package com.dao;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.common.DBHelper;

//类名=表名   属性=列
public class BaseDao<T> {
	DBHelper helper=new DBHelper();
	
	//添加
	public int add(T t,String className,String pk){
		int n=0;
		try {
			String classPath="com.beans."+className;
			//通过字符串产生类型 clazz
			Class clazz=Class.forName(classPath);
			//得到属性数组
			Field[] fields=clazz.getDeclaredFields();
			//得到方法数组
			Method[]methods=clazz.getDeclaredMethods();
			//insert into 表(列1,列2..) values (值1,值2..)
			//insert into dog(name,age) values (?,?)
			//构造sql
			String wh="";
			String sql="insert into "+className+" (";
			for(Field f:fields){
				//排除点主键
				if(!f.getName().equals(pk)){
					sql+=f.getName()+",";
					wh+="?,";
				}
			}
			//截取最后一个逗号
			sql=sql.substring(0, sql.length()-1);
			wh=wh.substring(0, wh.length()-1); 
			sql+=") values ("+wh+")";
			System.out.println(sql);
			
			//构建 值params
			Object[]params=new Object[fields.length-1];
			int index=0;
			for(Field f:fields){ 
				//排除点主键
				if(!f.getName().equals(pk)){  // name age
					for(Method m:methods){ //set getXXX
						//得到所有的get方法
						if(m.getName().substring(0, 3).equals("get")&&m.getName().substring(3).equalsIgnoreCase(f.getName())){ //getName getAge getId
							//找到了和属性对象的getXXX方法了
							//执行该方法
							params[index]= m.invoke(t, null); 
							index++;
							break;
						}
					}
				}
			}
			//遍历数组params
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
	
	//删除
	public int del(String className,String pk,int id){
		//delete from 表 where id=? 
		//构建sql
		String sql="delete from "+className+" where "+pk+"=?";
		int n=helper.execUpdate(sql, id);
		helper.closeResource(null, helper.pst, helper.conn);
		return n;
	}
	
	//修改
	public int upd(String className,T t,String pk){
		int n=0;
		try {
			//update 表 set 列1=?,.... where id=?
			String classPath="com.beans."+className;
			Class clazz=Class.forName(classPath);
			//得到属性数组
			Field[] fields=clazz.getDeclaredFields();
			//得到方法数组
			Method[]methods=clazz.getDeclaredMethods();
			//构建sql
			String sql="update "+className+" set ";
			for(Field f:fields){
				if(!f.getName().equals(pk)){
					sql+=f.getName()+"=?,";
				}
			}
			//截取最后一个逗号
			sql=sql.substring(0, sql.length()-1);
			sql+=" where "+pk+"=?";
			
			//构建 值params
			Object[]params=new Object[fields.length];
			int index=0;
			for(Field f:fields){ 
				//排除点主键
				if(!f.getName().equals(pk)){  // name age
					for(Method m:methods){ //set getXXX
						//得到所有的get方法
						if(m.getName().substring(0, 3).equals("get")&&m.getName().substring(3).equalsIgnoreCase(f.getName())){ //getName getAge getId
							//找到了和属性对象的getXXX方法了
							//执行该方法
							params[index]= m.invoke(t, null); 
							index++;
							break;
						}
					}
				}
			}
			//params最后装入主键值
			for(Method m:methods){
				//找到主键    getId
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
			//select * from 表 where id=?
			//构建sql
			String sql="select * from "+className+" where "+pk+"=?";
			
			rs=helper.execQuery(sql, id);
			if(rs.next()){
				//创建新对象
				Object obj= clazz.newInstance();
				Method[]methods=clazz.getDeclaredMethods();
				for(Method m:methods){
					//找到所有的set方法 setName setAge
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
			//select * from 表 where id=?
			//构建sql
			String sql="select * from "+className+" "+where;
			
			rs=helper.execQuery(sql, null);
			while(rs.next()){
				//创建新对象
				Object obj= clazz.newInstance();
				Method[]methods=clazz.getDeclaredMethods();
				for(Method m:methods){
					//找到所有的set方法 setName setAge
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
	
	//分页
	public ArrayList<T> getBypage(String where,String className,int pageindex,int pagesize){
		ResultSet rs=null;
		ArrayList<T> arr=new ArrayList<T>();
		try {
			String classPath="com.beans."+className;
			Class clazz=Class.forName(classPath);
			//构建sql
			String sql="select * from "+className+" limit "+(pageindex-1)*pagesize+" , "+pagesize;
			rs=helper.execQuery(sql, null);
			while(rs.next()){
				//创建新对象
				Object obj= clazz.newInstance();
				Method[]methods=clazz.getDeclaredMethods();
				for(Method m:methods){
					//找到所有的set方法 setName setAge
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
