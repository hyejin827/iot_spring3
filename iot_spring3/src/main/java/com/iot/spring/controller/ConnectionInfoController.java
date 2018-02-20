package com.iot.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iot.spring.service.ConnectionInfoService;
import com.iot.spring.vo.ColumnVO;
import com.iot.spring.vo.ConnectionInfoVO;
import com.iot.spring.vo.TableVO;
import com.iot.spring.vo.UserInfoVO;

@Controller
@RequestMapping("/connection")
public class ConnectionInfoController {
	private static final Logger log = LoggerFactory.getLogger(UrlController.class);
	private ObjectMapper om = new ObjectMapper();
	
	@Autowired
	private ConnectionInfoService cis;
	@RequestMapping("/list")
	public @ResponseBody Map<String,Object> getConnectionList(HttpSession hs,Map<String,Object>map){ //로그인했던 user를 기억하고 있음
		UserInfoVO ui = new UserInfoVO();
		if(hs.getAttribute("user")!=null) {
			ui = (UserInfoVO)hs.getAttribute("user"); 
		}else {
			ui.setUiId("red");
		}
		List<ConnectionInfoVO> ciList = cis.getConnectionInfoList(ui.getUiId()); //red로 로그인했기때문에 uiId가 red인 애들 다 가져옴
		map.put("list", ciList);
		return map;
	}


	@RequestMapping(value="/db_list/{ciNo}", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getDatabaseList(@PathVariable("ciNo") int ciNo,
			Map<String,Object> map,HttpSession hs) {
		List<Map<String, Object>> dbList;
		try {
			dbList = cis.getDatabaseList(hs, ciNo);
			map.put("list", dbList);
			map.put("parentId", ciNo);
		} catch (Exception e) {
			map.put("error",e.getMessage());
			log.error("db connection error =>{}",e);
		}
		return map;
	}

	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> insertConnectionInfo(@RequestParam Map<String,Object> map) {
		ConnectionInfoVO ci = om.convertValue(map, ConnectionInfoVO.class);
		log.info("ci=>{}",ci);
		cis.insertConnectionInfo(map, ci);
		return map;
	}
	@RequestMapping(value="/tables/{dbName}/{parentId}", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getTabeList(
			@PathVariable("dbName")String dbName, 
			@PathVariable("parentId")String parentId,
			HttpSession hs,
			Map<String,Object> map) {
		List<TableVO> tableList = cis.getTableList(hs, dbName);
		map.put("list", tableList);
		map.put("parentId", parentId);
		return map;
	}

	@RequestMapping(value="/columns/{dbName}/{tableName}", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getColumnList(
			@PathVariable("dbName")String dbName, 
			@PathVariable("tableName")String tableName,
			HttpSession hs,
			Map<String,Object> map) {
		Map<String, String> pMap = new HashMap<String, String>();
		pMap.put("dbName", dbName);
		pMap.put("tableName", tableName);
		List<ColumnVO> columnList = cis.getColumnList(hs, pMap);
		map.put("list", columnList);
		return map;
	}
	
	@RequestMapping(value="/tables/{tableName}", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getTableList(
			@PathVariable("tableName")String tableName, 
			HttpSession hs,
			Map<String,Object> map) {
		Map<String, String> pMap = new HashMap<String, String>();
		pMap.put("tableName", tableName);
		System.out.println(tableName);
		List<Object> tableList = cis.getTableList(hs, pMap);
		map.put("tableList", tableList);
		System.out.println(tableList);
		return map;
	}
	
	@RequestMapping(value="/columns", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getColumnList(Map<String,Object> map) {
		//cis.getColumnList(hs, map)
		return map;
	}
	
	
	
	
	
	
	@RequestMapping(value="/sql", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> getSql2(@RequestParam Map<String,Object> map, HttpSession hs){
		List<Object> sqlList = new ArrayList<Object>();
		String sqls = (String)map.get("sqlTa");	
		String[] sqlArr = sqls.split(";");
		
		//List<Object> getSqlList(HttpSession hs, Map<String,Object> map);
		
		for(int i=0;i<sqlArr.length;i++) {
			if(sqlArr[i].indexOf("select")!=-1) {
				sqlList.add(cis.getSqlList(hs, sqlArr[i]));
				
			}else if(sqlArr[i].indexOf("update")!=-1) {
				sqlList.add(cis.getSqlList(hs, sqlArr[i]));
				
			}else if(sqlArr[i].indexOf("delete")!=-1) {
				sqlList.add(cis.getSqlList(hs, sqlArr[i]));
				
			}else if(sqlArr[i].indexOf("insert")!=-1) {
				sqlList.add(cis.getSqlList(hs, sqlArr[i]));
				
			}
		}
		map.put("list", sqlList);
		return map;
	}

//	@RequestMapping(value="/sql/{sql}", method=RequestMethod.POST)
//	public @ResponseBody Map<String,Object> getSql(
//			@PathVariable("sql") String sql,
//			HttpSession hs) {
//		Map<String,Object> sqlMap = new HashMap<String,Object>();
//		
//		System.out.println("나 sql!!!!!"+sql);
//		sqlMap.put("sql", sql);
//		List<Object> sqlResult = cis.getSqlList(hs,sqlMap);		
//
//		String selectSql = "";
//	 	if(sql.indexOf("delete")!=-1) {
//			int deleteIdx = sql.indexOf("m")+2;
//			sql = sql.substring(deleteIdx,sql.indexOf("where"));
//			selectSql = "select * from "+ sql;
//			sqlMap.put("sql", selectSql);
//			sqlResult = cis.getSqlList(hs, sqlMap);
//			
//		}else if(sql.indexOf("update")!=-1) {
//			int updateIdx = sql.indexOf("e")+2;
//			sql = sql.substring(updateIdx, sql.indexOf("set"));
//			selectSql = "select * from "+ sql;
//			sqlMap.put("sql", selectSql);
//			sqlResult = cis.getSqlList(hs, sqlMap);
//		}	
//		
//		sqlMap.put("list", sqlResult);
//		
//		String logFooter = sql + " /* Affected row: +? 찾은 행: ? 경고: ? 지속 시간 ? 쿼리: ?. */ ";
//		sqlMap.put("logFooter", logFooter);
//		return sqlMap;
//	}
}
