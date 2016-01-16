# Token
spring으로 임의 token을 구현해 보았습니다.


	 * 사용법
	 jwtPack 이라는 package에 JwtCreat 라는 class 를 이용합니다
	 * 1. maven 에 다음과 같이 등록해 주세요
	  
	 * <dependency> 
	 * 		<groupId>io.jsonwebtoken</groupId>
	 * 		<artifactId>jjwt</artifactId> 
	 * 		<version>0.6.0</version> 
	 * </dependency>
	 
	 * 2. 설명
	 
	 * 객체를 생성시 request 와 response 를 받습니다.
	 
	 * id_enroll(HttpSession session, String id) 는 session 값과 id 값을 받아 key와 id 를
	 * 등록한후 session을 반환합니다.
	 
	 * 사용 예 ) session = creat.id_enroll(session, id);
	 
	 * cookie_enroll(String id, Key key) 는 id 와 key 값을 받아 난수를 생성한후 cookie에 저장 .
	 * 쿠키 목록을 반환합니다.
	 
	 * 사용 예 ) Cookie[] cookies =
	 * creat.cookie_enroll(id,(Key)session.getAttribute("key"));
	 
	 * TokenCheck(HttpSession session)는 session 값을 받은후 session에 저장된 id 와 key 값을
	 * 이용하여 결과값을 만들고 cookie 에 저장된 값과 비교하여 같을 경우 true 다를 경우 false 를 반환합니다.
	 
	 
