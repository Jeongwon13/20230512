package edu.kh.comm.member.model.service;

import edu.kh.comm.member.model.vo.Member;

public interface MemberService {

	Member login(Member member);

	int emailDupCheck(String memberEmail);

	int nicknameDupCheck(String memberNickname);

	int signUp(Member member);

}
