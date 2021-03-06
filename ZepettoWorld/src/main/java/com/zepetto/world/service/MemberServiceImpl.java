package com.zepetto.world.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.zepetto.world.domain.MemberVO;
import com.zepetto.world.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	private MemberDAO memberDao;
		

	@Override
	public MemberVO loginUserInfo(MemberVO vo) {
		// TODO Auto-generated method stub
		MemberVO result = memberDao.loginUserInfo(vo);
		
		return result;
	}
	
	@Override
	public void join(MemberVO vo) {
		// TODO Auto-generated method stub
		memberDao.join(vo);
	}

	@Override
	public Integer getBno() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		return memberDao.getTotalCount();
	}

	@Override
	public int checkId(String id) {
		// TODO Auto-generated method stub
		return memberDao.checkId(id);
	}

	@Override
	public int checkEmail(String checkEmail) {
		// TODO Auto-generated method stub
		return memberDao.checkEmail(checkEmail);
	}

	@Override
	public int checkUser(MemberVO vo) {
		// TODO Auto-generated method stub
		return memberDao.checkUser(vo);
	}
	
	
	
}
