package net.koreate.test.user.dao;

import net.koreate.test.user.vo.UserVO;

public interface UserDAO {
	
	void join(UserVO user);	

	UserVO login(UserVO vo);
	
}
