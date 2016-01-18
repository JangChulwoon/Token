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
import jwtPack.Hidden;
import jwtPack.JwtCreat;
import jwtPack.RiotApi;

/**
 * Handles requests for the application home page.
 */
@Controller
public class RuneController {
	
	private static final Logger logger = LoggerFactory.getLogger(RuneController.class);
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/rune.do", method = RequestMethod.GET)
	public String rune(Locale locale, Model model,HttpServletRequest request,HttpSession session,HttpServletResponse response) {
		String id = request.getParameter("id");
		RiotApi api = new RiotApi();
		long num = api.judge(id);
		if(num==0){
			return "fail";
		}
		String p_name = api.pagename(num);
		Hidden hide = new Hidden(id);
		model.addAttribute("run_name", hide.ch_value());
		session.setAttribute("lol_id", id);
		return "runpage";
	}
	
	@RequestMapping(value = "/runepage.do", method = RequestMethod.GET)
	public String runepage(Locale locale, Model model,HttpServletRequest request,HttpSession session,HttpServletResponse response) {
		String id  = (String)session.getAttribute("lol_id");
		RiotApi api = new RiotApi();
		long num = api.judge(id);
		String p_name = api.pagename(num);
		Hidden hide = new Hidden(id);
		String rune_code = Integer.toString(hide.ch_value());
		p_name = p_name.trim();
		rune_code = rune_code.trim();
		if(rune_code.equals(p_name)){
			return "success";
		}else{
			return "home";
		}
	}
}
