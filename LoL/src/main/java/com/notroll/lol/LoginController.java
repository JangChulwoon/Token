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
		// �켱 id passwd �� �Է��ϸ� �α��� ����
		if("id".equals(id)&& "passwd".equals(passwd)){
			// ���� ������, id�� ������ �̿��Ͽ� result ���� �����.
			// key ���� id ���� ���ǿ� �����ص� ���� ��Ű�� result ���� ������ �д�.
			JwtCreat creat = new JwtCreat(request,response);
			session = creat.id_enroll(session, id);
			Cookie[] cookies = creat.cookie_enroll(id,(Key)session.getAttribute("key"));
			// ���ǿ� key ���� �ְ� Ű�� id�� ���ļ� ������ ����� , �װ� ������ �Ѿ��
			return "login";
		}else{
			return "home";
		}
		
		
		
		
	}
	
}
