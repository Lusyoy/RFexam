package com.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.beans.Examination;
import com.common.DBHelper;

public class ExaminationDao {
      DBHelper helper= new DBHelper();
      public ArrayList<Examination> getRandomTest(int num, int courseid){
    	  ResultSet rs =null;
    	  ArrayList<Examination> elist = new ArrayList<Examination>();
    	  try {
			rs=helper.execQuery("select top "+num+" *,NEWID() as 'random' from examination where  courseid="+courseid+" order by random", null);
		    elist.add(new Examination(rs.getInt("id"), rs.getInt("courseid"), rs.getString("question"), rs.getString("ansA"), rs.getString("ansB"), rs.getString("ansC"), rs.getString("ansD"), rs.getString("ans")));
    	  } catch (Exception e) {
			e.printStackTrace();
		}finally{
			helper.closeResource(rs, helper.pst, helper.conn);
		}
    	  return elist;
      }
} 
