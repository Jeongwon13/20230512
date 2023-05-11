package edu.kh.comm.member.model.service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.kh.comm.common.Util;
import edu.kh.comm.member.model.dao.MyPageDAO;
import edu.kh.comm.member.model.vo.Member;

@Service
public class MyPageServiceImpl implements MyPageService {

	@Autowired
	private MyPageDAO dao;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Override
	public int updateInfo(Map<String, Object> paramMap) {
		return dao.updateInfo(paramMap);
	}

	@Override
	public int changePw(Map<String, Object> paramMap) {
		 
		String encPw = dao.selectEncPw((int)paramMap.get("memberNo"));
		
		if(bcrypt.matches((String)paramMap.get("currentPw"), encPw)) {
			
		
			paramMap.put("newPw", bcrypt.encode((String)paramMap.get("newPw")));
		
		return dao.changePw(paramMap);
	}

		return 0;
	}

	@Override
	public int secession(Member loginMember) {
		
		String encPw = dao.selectEncPw(loginMember.getMemberNo());
		
		if(bcrypt.matches(loginMember.getMemberPw(), encPw)) {
			return dao.secession(loginMember.getMemberNo());	 	
		}
		return 0;
	}

	@Override
	public int updateProfile(Map<String, Object> map) throws IOException {
		
		MultipartFile uploadImage = (MultipartFile) map.get("uploadImage");
		String delete = (String) map.get("delete");
		
		String renameImage = null;
		
		if(delete.equals("0")) {
			renameImage = Util.fileRename(uploadImage.getOriginalFilename());
			map.put("profileImage", map.get("webPath") + renameImage);
			
		} else {
			map.put("profileImage", null);
		}
		
		int result = dao.updateProfile(map);
		
		if(result > 0 && map.get("profileImage") != null) {
			uploadImage.transferTo(new File(map.get("folderPath") + renameImage));
		}
		
		return result;
	}


	
	

}
