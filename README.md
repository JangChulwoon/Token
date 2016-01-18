# Token
spring FrameWork를 사용하여 Jwt(Tocken 인증 을 사용해보기 위해) 와 Riot api를 사용해 보았습니다. 
 1. 세션과 쿠키를 사용하여 Token 인증을 구현하였습니다. (JwtCreat.class)
 2. Riot api를 이용하여 사용자의 id를 이용한 코드 생성 및 첫번째 룬 페이지의 이름을 가져오는 부분을 구현하였습니다. 
 (Hidden.class / RiotApi.class)

	 + 사용하기전 설정
	
	  <h4>maven (pom.xml)(- dependencies) 에 다음과 같이 등록해 주세요</h4>
	  ```
		<!-- jjwt  -->
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt</artifactId>
			<version>0.6.0</version>
		</dependency>
		<!-- simple -->
		<dependency>
			<groupId>com.googlecode.json-simple</groupId>
			<artifactId>json-simple</artifactId>
			<version>1.1</version>
		</dependency>
	
	 ```
	 + 1. Token 인증 부분 (JJWT)
		구현시 사용된 JwtCreat.class는 다음과 같은 함수를 갖고있습니다.
	+생성자 
		객체를 생성시 request 와 response 를 인자로 받아 초기화합니다. 
	+id_enroll
		
	+cookie_enroll
	
	+TokenCheck
	 
	 * id_enroll(HttpSession session, String id) 는 session 값과 id 값을 받아 key와 id 를
	 * 등록한후 session을 반환합니다.
	 
	 * 사용 예 ) session = creat.id_enroll(session, id);
	 
	 * cookie_enroll(String id, Key key) 는 id 와 key 값을 받아 난수를 생성한후 cookie에 저장 .
	 * 쿠키 목록을 반환합니다.
	 
	 * 사용 예 ) Cookie[] cookies =
	 * creat.cookie_enroll(id,(Key)session.getAttribute("key"));
	 
	 * TokenCheck(HttpSession session)는 session 값을 받은후 session에 저장된 id 와 key 값을
	 * 이용하여 결과값을 만들고 cookie 에 저장된 값과 비교하여 같을 경우 true 다를 경우 false 를 반환합니다.
	 


+ <h4> 버전 </h4>

  1.0.0 (2016-01-16)
  1.1.0 (2016-01-16)
  1.1.1 (2016-01-18)
  

+ <h4> question </h4>

  lusiue@gmail.com
