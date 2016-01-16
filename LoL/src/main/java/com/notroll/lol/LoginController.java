package com.notroll.lol;

import java.security.Key;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.jsonwebtoken.InvalidClaimException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import jwtPack.JwtCreat;

/**
 * Handles requests for the application home page.
 */
@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String home(Locale locale, Model model,HttpServletRequest request,HttpSession session,HttpServletResponse response) {
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		// 우선 id passwd 를 입력하면 로그인 성공
		if("id".equals(id)&& "passwd".equals(passwd)){
			// 난수 생성후, id와 난수를 이용하여 result 값을 만든다.
			// key 값과 id 값을 세션에 저장해둠 또한 쿠키에 result 값을 저장해 둔다.
			JwtCreat creat = new JwtCreat(request,response);
			session = creat.id_enroll(session, id);
			Cookie[] cookies = creat.cookie_enroll(id,(Key)session.getAttribute("key"));
			// 세션에 key 값을 넣고 키와 id를 합쳐서 난수를 만들고 , 그게 맞으면 넘어간다
			return "login";
		}else{
			return "home";
		}
		
		
		
		
	}
	
}
