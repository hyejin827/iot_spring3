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
			ui.setUiId(hs.getAttribute("user").toString()); 
		}else {
			map.put("msg", "로그인을 먼저 해주세요");
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
	public @ResponseBody Map<String,Object> insertConnectionInfo(@RequestParam Map<String,Object> map, HttpSession hs) {
		ConnectionInfoVO ci = om.convertValue(map, ConnectionInfoVO.class);
		ci.setUiId(hs.getAttribute("user").toString());
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
		long startTime = System.currentTimeMillis();
		
		List<Object> sqlList = new ArrayList<Object>();
		List<Object> UDISqlList = new ArrayList<Object>();
		List<Object> columnIdx = new ArrayList<Object>();
		List<Object> sqlQuery = new ArrayList<Object>();
		List<Object> UDISqlQuery = new ArrayList<Object>();
		String sqls = (String)map.get("sqlTa");	
		String[] sqlArr = sqls.split(";");
		
		for(String str : sqlArr) {
			str.trim();
			if(str.indexOf("select")!=-1) {
				sqlList.add(cis.getSqlList(hs, str));
				columnIdx.add(cis.getSqlList(hs, str).size());
				sqlQuery.add(str);
			}else {
				UDISqlList.add(cis.UDISqlList(hs, str));
				UDISqlQuery.add(str);
			}
		}

		System.out.println("columnIdx  :  "+columnIdx);
		System.out.println("UDISqlList  :  "+UDISqlList);
		System.out.println("sqlQuery  :  "+sqlQuery);
		System.out.println("sqlList는 "+sqlList);
		
		map.put("list", sqlList);
		map.put("UDISql", UDISqlList);
		map.put("sqlQuery", sqlQuery);
		map.put("columnIdx", columnIdx);
		map.put("UDISqlQuery", UDISqlQuery);
		
		long setTime = System.currentTimeMillis()-startTime;
		map.put("time", setTime);
		return map;
	}
}
