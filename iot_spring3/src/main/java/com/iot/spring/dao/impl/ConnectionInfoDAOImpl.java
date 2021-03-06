package com.iot.spring.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iot.spring.dao.ConnectionInfoDAO;
import com.iot.spring.vo.ColumnVO;
import com.iot.spring.vo.ConnectionInfoVO;
import com.iot.spring.vo.TableVO;

@Repository
public class ConnectionInfoDAOImpl implements ConnectionInfoDAO {

	@Autowired
	private SqlSessionFactory ssf;
	
	@Override
	public List<ConnectionInfoVO> selectConnectionInfoList(String uiId) {
		SqlSession ss = ssf.openSession();
		List<ConnectionInfoVO> ciList = ss.selectList("connection_info.selectConnectionInfo", uiId);
		ss.close();
		return ciList;
	}
	@Override
	public ConnectionInfoVO selectConnectionInfo(int ciNo) {
		SqlSession ss = ssf.openSession();
		ConnectionInfoVO ci = ss.selectOne("connection_info.selectConnectionInfoWithCiNo", ciNo);
		ss.close();
		return ci; 
	}

	@Override
	public List<ConnectionInfoVO> selectConnectionInfoList(ConnectionInfoVO ci) {
		List<ConnectionInfoVO> result = null;
		final SqlSession ss = ssf.openSession();
		result = ss.selectList("connection_info.selectConnectionInfo",ci);
		ss.close();
		return result;
	}

	@Override
	public int insertConnectionInfo(ConnectionInfoVO ci) {
		int result = 0;
		final SqlSession ss = ssf.openSession();
		result = ss.insert("connection_info.insertConnectionInfo", ci);
		ss.close();
		return result;
	}

	@Override
	public List<Map<String, Object>> selectDatabaseList(SqlSession ss) throws Exception {
		return ss.selectList("connection_info.selectDatabase");
	}
	@Override
	public List<TableVO> selectTableList(SqlSession ss,String dbName) {
		List<TableVO> result = null;
		result = ss.selectList("connection_info.selectTable",dbName);
		return result;
	}

	@Override
	public List<ColumnVO> selectColumnList(SqlSession ss,Map<String,String> map) {
		return ss.selectList("connection_info.selectColumn",map);
	}
	@Override
	public List<Object> selectSqlList(SqlSession ss, String str) {
		System.out.println("제발      : "+str);
		return ss.selectList("connection_info.selectSql",str);
	}
	@Override
	public List<Object> selectTableList(SqlSession ss, Map<String, String> map) {
		System.out.println("제발      : "+map);
		return ss.selectList("connection_info.selectTableName",map);
	}
	@Override
	public int UDISqlList(SqlSession ss, String str) {
		int result = ss.update("connection_info.UDISql",str);
		System.out.println("str :   "+str);
		System.out.println("result :    "+result);
		return result;
	}
}
