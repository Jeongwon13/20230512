package edu.kh.comm.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.comm.member.model.vo.Member;

@Repository
public class MemberDAO {
	
	private static final Object Member = null;
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	public Member login(Member member) {
		Member loginMember = sqlSession.selectOne("memberMapper.login", member);
		return loginMember;
	}

	public int emailDupCheck(String memberEmail) {
		return sqlSession.selectOne("memberMapper.emailDupCheck", memberEmail);
	}

	public int nicknameDupCheck(String memberNickname) {
		return sqlSession.selectOne("memberMapper.nicknameDupCheck", memberNickname);
	}

	public int signUp(Member member) {
		return sqlSession.insert("memberMapper.signUp", member);
	}
	
	
	
}
