package com.dao;

import java.sql.ResultSet;

import com.common.DBHelper;

public class GetIdDao {
	DBHelper helper = new DBHelper();
	public int getLastId(){
		ResultSet rs =null;
		try {
			rs = helper.execQuery("select @@IDENTITY", null);
			if(rs.next()){
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			helper.closeResource(rs, helper.pst, helper.conn);
		}
		return -1;
	}
}
