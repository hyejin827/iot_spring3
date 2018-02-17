package com.iot.spring.dao;

import com.iot.spring.vo.UserInfoVO;

public interface UserInfoDAO {

	public UserInfoVO selectUserInfo(UserInfoVO ui);
	
	public int insertUserInfo(UserInfoVO ui);
}
