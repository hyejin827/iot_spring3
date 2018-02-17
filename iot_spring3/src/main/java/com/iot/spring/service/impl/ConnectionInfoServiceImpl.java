package com.iot.spring.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot.spring.common.dbcon.DBConnector;
import com.iot.spring.dao.ConnectionInfoDAO;
import com.iot.spring.service.ConnectionInfoService;
import com.iot.spring.vo.ColumnVO;
import com.iot.spring.vo.ConnectionInfoVO;
import com.iot.spring.vo.TableVO;

@Service
public class ConnectionInfoServiceImpl implements ConnectionInfoService{

	private static final Logger log = LoggerFactory.getLogger(ConnectionInfoServiceImpl.class);
	@Autowired
	private ConnectionInfoDAO cidao;
	@Autowired
	private DBConnector dbc;
	
	@Override
	public List<ConnectionInfoVO> getConnectionInfoList(String uiId) {
		return cidao.selectConnectionInfoList(uiId);
	}

	@Override
	public ConnectionInfoVO getConnectionInfo(ConnectionInfoVO ci) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ConnectionInfoVO> getConnectionInfoList(ConnectionInfoVO ci) {
		// TODO Auto-generated method stub
		List<ConnectionInfoVO> ciList = cidao.selectConnectionInfoList(ci);
//		for(ConnectionInfoVO civ:ciList) {
//			civ.setItems(new Object[] {"a","b"});
//		}
		return ciList;
	}

	@Override
	public void insertConnectionInfo(Map<String, Object> rMap, ConnectionInfoVO ci) {
		int result = cidao.insertConnectionInfo(ci);
		rMap.put("msg", "저장이 실패하였습니다.");
		if(result==1) {
			rMap.put("msg", "저장이 성공하였습니다.");
		}
	}
	
	public List<Map<String,Object>> getDatabaseList(HttpSession hs,int ciNo)throws Exception {
		ConnectionInfoVO ci = cidao.selectConnectionInfo(ciNo);
		dbc.setConnectionInfo(ci);
		SqlSession ss = dbc.getSqlSession();
		hs.setAttribute("sqlSession", ss);
		log.info("sqlSession=>{}",ss);
		List<Map<String,Object>> dbList = cidao.selectDatabaseList(ss);
		int idx = 0;
		for(Map<String,Object> mDb : dbList) {
			mDb.put("id", ciNo + "_" + (++idx));
			mDb.put("text", mDb.get("Database"));
			mDb.put("items", new Object[] {});
		}
		return dbList;
	}

	@Override
	public List<TableVO> getTableList(HttpSession hs, String dbName) {
		SqlSession ss = (SqlSession)hs.getAttribute("sqlSession");
		return cidao.selectTableList(ss, dbName);
	}


	@Override
	public List<ColumnVO> getColumnList(HttpSession hs, Map<String,String> map) {
		SqlSession ss = (SqlSession)hs.getAttribute("sqlSession");
		return cidao.selectColumnList(ss, map);
	}
}
