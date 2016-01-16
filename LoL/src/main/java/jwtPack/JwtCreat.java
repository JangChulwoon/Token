package jwtPack;

import java.security.Key;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;

import io.jsonwebtoken.InvalidClaimException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

public class JwtCreat {
	/**
	 * 사용법
	 * 
	 * 1. maven 에 다음과 같이 등록해 주세요
	 * 
	 * <dependency> <groupId>io.jsonwebtoken</groupId>
	 * <artifactId>jjwt</artifactId> <version>0.6.0</version> </dependency>
	 * 
	 * 2. 설명
	 * 
	 * 객체를 생성시 request 와 response 를 받습니다.
	 * 
	 * id_enroll(HttpSession session, String id) 는 session 값과 id 값을 받아 key와 id 를
	 * 등록한후 session을 반환합니다.
	 * 
	 * 사용 예 ) session = creat.id_enroll(session, id);
	 * 
	 * cookie_enroll(String id, Key key) 는 id 와 key 값을 받아 난수를 생성한후 cookie에 저장 .
	 * 쿠키 목록을 반환합니다.
	 *
	 * 사용 예 ) Cookie[] cookies =
	 * creat.cookie_enroll(id,(Key)session.getAttribute("key"));
	 * 
	 * TokenCheck(HttpSession session)는 session 값을 받은후 session에 저장된 id 와 key 값을
	 * 이용하여 결과값을 만들고 cookie 에 저장된 값과 비교하여 같을 경우 true 다를 경우 false 를 반환합니다.
	 * 
	 */

	// requset값을 받아서 세션을 만드는 로직
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;

	public JwtCreat(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
	}

	public Key initKey() {
		return MacProvider.generateKey();
	}

	// 세션을 등록하는 부분
	public HttpSession id_enroll(HttpSession session, String id) {
		Key key = initKey();
		session.setAttribute("key", key);
		session.setAttribute("id", id);
		return session;
	}

	// 쿠키를 등록하는 부분 id 와 key 를 받아 등록
	public Cookie[] cookie_enroll(String id, Key key) {
		String result = Jwts.builder().setSubject(id).signWith(SignatureAlgorithm.HS512, key).compact();
		Cookie cookie = new Cookie("result", result);
		response.addCookie(cookie);
		return request.getCookies();
	}

	public boolean TokenCheck(HttpSession session) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) { // 쿠키를 반복문으로 돌린다.
				if (cookies[i].getName().equals("result")) {
					try {
						Jwts.parser().requireSubject((String) session.getAttribute("id"))
								.setSigningKey((Key) session.getAttribute("key")).parseClaimsJws(cookies[i].getValue());
					} catch (InvalidClaimException ice) {
						return false;
					}
					return true;
				}
			}
		}
		return false;
	}

}
