package cn.itcast.mp.serviceImpl;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;

import cn.itcast.mp.mapper.UserMapper;
import cn.itcast.mp.model.UserInfo;
import cn.itcast.mp.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	private UserMapper userMapper;
	
	//获得User对象 从Redis里面拿
	@Override
	@Cacheable(cacheNames="User",key="#name")
	public UserInfo getUser(String UserId) {
		if(StringUtils.isEmpty(UserId)) {
			return null;
		}

		return userMapper.getUserById(UserId);
	}

	@Override
	@CachePut(cacheNames="User",key="#name")
	public void UpdateUser(UserInfo user) {

		 userMapper.updateUser(user);
	}	
	
	public void addUser(UserInfo user) {
		
		userMapper.insertUser(user);
	}
}