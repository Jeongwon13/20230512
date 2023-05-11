package edu.kh.comm.member.controller;

 
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.comm.member.model.service.MyPageService;
import edu.kh.comm.member.model.vo.Member;

@Controller
@RequestMapping("/member/myPage")
@SessionAttributes({"loginMember"})
public class MyPageController {
	
	@Autowired
	private MyPageService service;

	Logger logger = LoggerFactory.getLogger(MyPageController.class);

	@GetMapping("/info")
	public String info() {
		return "member/myPage-info";
	}
	
	@GetMapping("/changePw")
	public String changePw() {
		return "member/myPage-changePw";
	}
	
	@GetMapping("/secession")
	public String secession() {
		return "member/myPage-secession";
	}
	
	@GetMapping("profile")
	public String profile() {
		return "member/myPage-profile";
	}
	
	@PostMapping("/info")
	public String updateInfo(@ModelAttribute("loginMember") Member loginMember,
							@RequestParam Map<String, Object> paramMap,
							RedirectAttributes ra,
							String[] updateAddress
			) {
		
		String memberAddress = String.join(",,", updateAddress);
		if(memberAddress.equals(",,,,")) memberAddress = null;
		
		paramMap.put("memberNo", loginMember.getMemberNo());
		paramMap.put("memberAddress", memberAddress);
		
		int result = service.updateInfo(paramMap);
		
		String message = null; 
		
		if(result > 0) {
			message = "회원 정보가 수정 되었어요~";
			
			loginMember.setMemberNickname((String)paramMap.get("updateNickname"));
			loginMember.setMemberTel((String)paramMap.get("updateTel"));
			loginMember.setMemberAddress((String)paramMap.get("memberAddress"));
			
		} else {
			message = "회원 정보 수정 실패~";
		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:info";
	}
	
	@PostMapping("/changePw")
	public String changePw(@ModelAttribute("loginMember") Member loginMember,
						@RequestParam Map<String, Object> paramMap,
						RedirectAttributes ra
			) {
		
		paramMap.put("memberNo", loginMember.getMemberNo());
		
		int result = service.changePw(paramMap);
		
		String message = null;
		String path = null;
		
		if(result > 0) {
			message = "비밀번호 변경 성공~";
			path = "info";
		} else {
			message = "비밀번호 변경 실패ㅋ";
			path = "changePw";
		}
		 
		ra.addFlashAttribute("message", message);
		
		return "redirect:" + path;
	}
	
	@PostMapping("/secession")
	public String secession(@ModelAttribute("loginMember") Member loginMember,
						SessionStatus status,
						HttpServletRequest req,
						HttpServletResponse resp,
						RedirectAttributes ra
			
			) { 
		
		int result = service.secession(loginMember);
		
		String message = null;
		String path = null;
		
		if(result > 0) {
			message = "회원 탈퇴 완료~ㅋ";
			path = "/";
			
			status.setComplete();
			
			Cookie cookie = new Cookie("saveId", "");
			cookie.setMaxAge(0);
			cookie.setPath(req.getContextPath());
			resp.addCookie(cookie);
		} else {
			message = "현재 비밀번호가 일치하지 않습니다~";
			path = "secession";
		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:" + path;
		
	}
	
	
	@PostMapping("/profile")
	public String updateProfile(@ModelAttribute("loginMember") Member loginMember,
			@RequestParam("uploadImage") MultipartFile uploadImage,
			@RequestParam Map<String, Object> map,
			HttpServletRequest req, 
			RedirectAttributes ra) throws IOException {
	
		String webPath = "/resources/images/memberProfile/";
		
		String folderPath = req.getSession().getServletContext().getRealPath(webPath);
		
		map.put("webPath", webPath);
		map.put("folderPath", folderPath);
		map.put("uploadImage", uploadImage);
		map.put("memberNo", loginMember.getMemberNo());

		int result = service.updateProfile(map);
		
		String message = null;
		
		if(result > 0) {
			message = "프로필 이미지가 변경되었습니다.";
			loginMember.setProfileImage((String)map.get("profileImage"));
		} else {
			message = "프로필 이미지 변경 실패";
		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:profile";
	}
	
	
	
	
}
