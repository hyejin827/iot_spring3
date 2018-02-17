package com.iot.spring.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.iot.spring.vo.ColumnVO;
import com.iot.spring.vo.ConnectionInfoVO;
import com.iot.spring.vo.TableVO;

public interface ConnectionInfoDAO {

	public List<ConnectionInfoVO> selectConnectionInfoList(String uiId);
	ConnectionInfoVO selectConnectionInfo(int ciNo);
	List<ConnectionInfoVO> selectConnectionInfoList(ConnectionInfoVO ci);
	int insertConnectionInfo(ConnectionInfoVO ci);
	List<Map<String,Object>> selectDatabaseList(SqlSession ss)throws Exception ;
	List<TableVO> selectTableList(SqlSession ss, String dbName);
	List<ColumnVO> selectColumnList(SqlSession ss, Map<String,String> map);
}
