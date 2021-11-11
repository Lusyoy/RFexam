package com.dao;

import java.sql.ResultSet;

import com.beans.Stu_info;

/**
 * @author liuyang
 *
 * @param <T>
 */
public class Stu_infoDao<T> extends BaseDao<T> {
	public Stu_info login(int id){
		ResultSet rs =null;
		try {
			rs=helper.execQuery("select * from stu_info where id=?", id);
			if(rs.next()){
				return new Stu_info(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			helper.closeResource(rs, helper.pst, helper.conn);
		}
		return null;
	}

}
