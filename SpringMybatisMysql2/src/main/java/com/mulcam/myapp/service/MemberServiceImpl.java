package com.mulcam.myapp.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mulcam.myapp.dao.MemberDAO;
import com.mulcam.myapp.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{
	// dao��ü����	MemberDAO dao = new MemberDAO();
	@Inject // ��ü�� �������ִ� ������̼� =	@Autowired
	MemberDAO dao;

	@Override
	public MemberVO login(MemberVO vo) {
		return dao.login(vo);
	}
}
