package edu.kh.comm.member.controller;

 
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import edu.kh.comm.member.model.service.MemberService;
import edu.kh.comm.member.model.vo.Member;

@Controller
@RequestMapping("/member")
@SessionAttributes({"loginMember"})
public class MemberController {
	
	private static final int Member = 0;

	private static final int List = 0;
	
	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService service;
	
	@PostMapping("/login")
	public String login(@ModelAttribute Member member,
						Model model,
						RedirectAttributes ra,
						HttpServletRequest req,
						HttpServletResponse resp,
					@RequestParam(value="saveId", required=false) String saveId
			) { 
		logger.info("로그인 수행~");
		
		Member loginMember = service.login(member);
		
		if(loginMember != null) {
			model.addAttribute("loginMember", loginMember);
			
			Cookie cookie = new Cookie("saveId", loginMember.getMemberEmail());
			
			if(saveId != null) {
				cookie.setMaxAge(60 * 60 * 24 * 365);
			} else {
				cookie.setMaxAge(0);
			}
		
			cookie.setPath(req.getContextPath());
			resp.addCookie(cookie);
		
		} else {
			ra.addFlashAttribute("message", "아이디 또는 비밀번호 일치하지 않아~");
		}
		return "redirect:/";
	}
	
	
	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		logger.info("로그아웃 수행됨~");
		
		status.setComplete();
		
		return "redirect:/";
	}
	
	@GetMapping("/signUp")
	public String signUp() {
		return "member/signUp";
	}
	
	@ResponseBody
	@GetMapping("/emailDupCheck")
	public int emailDupCheck(String memberEmail) {
		
		return service.emailDupCheck(memberEmail);
		
		 
		
	}
	
	@ResponseBody
	@GetMapping("/nicknameDupCheck")
	public int nicknameDupCheck(String memberNickname) {
		int result = service.nicknameDupCheck(memberNickname);
		return result;
	}
	
	@PostMapping("/signUp")
	public String signUp(Member member, String[] memberAddress, RedirectAttributes ra) {
		
		member.setMemberAddress(String.join(",,", memberAddress));
		
		if(member.getMemberAddress().equals(",,,,")) {
			member.setMemberAddress(null);
		}
		
		int result = service.signUp(member);
		
		String message = null;
		String path = null;
		
		if(result > 0) {
			message = "회원가입 성공!";
			path = "redirect:/";
		} else {
			message = "회원가입 실패~";
			path = "redirect:/member/signUp";
		}
		
		ra.addFlashAttribute("message", message);
		return path;
	}
	
	
	
}











