package com.zepetto.world.ca;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import com.zepetto.world.domain.MemberVO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zepetto.world.service.MemberService;



@Controller
@RequestMapping("member")
public class MemberController {

	@Inject
	private MemberService memberService;

	@Inject
	BCryptPasswordEncoder passwordEncoder;
	
	//로그인 Success
	@RequestMapping(value = "loginSuc", method = RequestMethod.POST)
	public MemberVO loginSuc(HttpServletRequest request, Model model) {
		String userId = request.getParameter("userId");
		String passWord = request.getParameter("passWord");
		
		MemberVO vo = new MemberVO();
		vo.setUserId(userId);
		vo.setPassWord(passWord);
		
		MemberVO result = memberService.loginUserInfo(vo);
		
		return result;
	}
	
	
	
	@RequestMapping(value = "join", method = RequestMethod.GET)
	public void joinGet() {
		
	}
	
	@RequestMapping(value = "join", method = RequestMethod.POST)
	public String joinPost(MemberVO vo, RedirectAttributes rttr) {
		
		
		vo.setPassWord(passwordEncoder.encode(vo.getPassWord())); //비밀번호 암호화 

		memberService.join(vo);
		
		rttr.addFlashAttribute("msg", "INSERT_SUCCESS");

		return "redirect:/";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "checkId", method = RequestMethod.GET)
	public String checkSignup(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        int rowcount = memberService.checkId(id);
        
        return String.valueOf(rowcount);
	}
	
	@ResponseBody
	@RequestMapping(value = "checkEmail", method = RequestMethod.POST)
	public String checkEmail(HttpServletRequest request, Model model) {
		String checkEmail = request.getParameter("checkEmail");
		
		int rowcount = memberService.checkEmail(checkEmail);
	        
	    return String.valueOf(rowcount);
	}
	
	@ResponseBody
	@RequestMapping(value = "checkPassword", method = RequestMethod.POST)
	public String checkPassword(HttpServletRequest request, Model model) {
		String checkPassword = request.getParameter("checkPassword");
		
		return checkPassword;
	}
	
	@ResponseBody
	@RequestMapping(value = "checkUser", method = RequestMethod.POST)
	public String checkUser(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		String passWord = request.getParameter("passWord");
		
		MemberVO vo = new MemberVO();
		vo.setUserId(userId);
		vo.setPassWord(passWord);
		
		int flag = memberService.checkUser(vo);
	
		return String.valueOf(flag);
	}

	
}
