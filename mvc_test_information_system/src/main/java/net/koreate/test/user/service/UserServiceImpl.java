package net.koreate.test.user.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.koreate.test.user.dao.UserDAO;
import net.koreate.test.user.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {

	@Inject
	UserDAO dao;
	
	@Override
	public void join(UserVO vo) {
		dao.join(vo);
	}

	@Override
	public UserVO login(UserVO vo) {
		UserVO user = dao.login(vo);
		return user;
	}	
}