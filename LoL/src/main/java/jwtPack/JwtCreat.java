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
	 * ����
	 * 
	 * 1. maven �� ������ ���� ����� �ּ���
	 * 
	 * <dependency> <groupId>io.jsonwebtoken</groupId>
	 * <artifactId>jjwt</artifactId> <version>0.6.0</version> </dependency>
	 * 
	 * 2. ����
	 * 
	 * ��ü�� ������ request �� response �� �޽��ϴ�.
	 * 
	 * id_enroll(HttpSession session, String id) �� session ���� id ���� �޾� key�� id ��
	 * ������� session�� ��ȯ�մϴ�.
	 * 
	 * ��� �� ) session = creat.id_enroll(session, id);
	 * 
	 * cookie_enroll(String id, Key key) �� id �� key ���� �޾� ������ �������� cookie�� ���� .
	 * ��Ű ����� ��ȯ�մϴ�.
	 *
	 * ��� �� ) Cookie[] cookies =
	 * creat.cookie_enroll(id,(Key)session.getAttribute("key"));
	 * 
	 * TokenCheck(HttpSession session)�� session ���� ������ session�� ����� id �� key ����
	 * �̿��Ͽ� ������� ����� cookie �� ����� ���� ���Ͽ� ���� ��� true �ٸ� ��� false �� ��ȯ�մϴ�.
	 * 
	 */

	// requset���� �޾Ƽ� ������ ����� ����
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

	// ������ ����ϴ� �κ�
	public HttpSession id_enroll(HttpSession session, String id) {
		Key key = initKey();
		session.setAttribute("key", key);
		session.setAttribute("id", id);
		return session;
	}

	// ��Ű�� ����ϴ� �κ� id �� key �� �޾� ���
	public Cookie[] cookie_enroll(String id, Key key) {
		String result = Jwts.builder().setSubject(id).signWith(SignatureAlgorithm.HS512, key).compact();
		Cookie cookie = new Cookie("result", result);
		response.addCookie(cookie);
		return request.getCookies();
	}

	public boolean TokenCheck(HttpSession session) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) { // ��Ű�� �ݺ������� ������.
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
