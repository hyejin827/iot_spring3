package com.iot.spring.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iot.spring.service.UserInfoService;
import com.iot.spring.vo.UserInfoVO;

@Controller
@RequestMapping("/user")
public class UserInfoController {
	@Autowired
	private UserInfoService uis;
	
	private static final Logger log = LoggerFactory.getLogger(UserInfoController.class);
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> login( UserInfoVO ui, HttpSession hs){
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(ui);
		if(uis.login(map, ui)) {
			hs.setAttribute("user", map.get("user"));
		}
		return map;
	}
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> join(@RequestBody UserInfoVO ui){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", "회원가입에 실패했습니다.");
		map.put("biz", false);
		int result = uis.join(ui);
		if(result==1) {
			map.put("msg", "회원가입에 성공했습니다.");
			map.put("biz", true);
		}else if(result==2) {
			map.put("msg", "아이디가 중복되었습니다.");
		}
		return map;
	}

	@RequestMapping(value="/check/{uiId}", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> join2(@PathVariable String uiId){
		Map<String, Object> map = new HashMap<String, Object>();
		log.info("insertUI=>{}",uiId);
		System.out.println("uiId:   "+uiId);
		map.put("msg", "아이디가 중복되었습니다.");
		map.put("biz", false);
		if(uis.checkUserId(uiId)==0) {
			map.put("msg", "사용할 수 있는 아이디입니다.");
			map.put("biz", true);
		}
		return map;
	}
}
