# Token
spring FrameWork를 사용하여 Jwt(Tocken 인증 을 사용해보기 위해) 와 Riot api를 사용해 보았습니다. 
 1. 세션과 쿠키를 사용하여 Token 인증을 구현하였습니다. (JwtCreat.class)
 2. Riot api를 이용하여 사용자의 id를 이용한 코드 생성 및 첫번째 룬 페이지의 이름을 가져오는 부분을 구현하였습니다. 
 (Hidden.class / RiotApi.class)

	<h4>사용하기전 설정</4>
	
	  maven (pom.xml)(- dependencies) 에 다음과 같이 등록해 주세요
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
	
	<h4> 인증 부분 (JJWT)	</h4> 
	
	구현시 사용된 JwtCreat.class는 다음과 같은 함수를 갖고있습니다.
		
	<h5>생성자</h5> 
	
	객체를 생성시 request 와 response 를 인자로 받아 초기화합니다. 
		
	<h5>HttpSession id_enroll(HttpSession session, String id) </h5> 
		
	session와 id 값을 받아와 session에 id 와 key (jwt 에서 사용될 key) 값을 저장합니다.
	그 후, 세션을 반환합니다.
	```
		JwtCreat creat = new JwtCreat(request,response);
		ex ) session = creat.id_enroll(session, id);
	```
	<h5>Cookie[] cookie_enroll(String id, Key key)</h5> 
	
	id와 key 값을 받아와 jwt를 이용해 token 값을 생성합니다.
	그 후, token 값을 사용자의 cookie에 저장. 
	
	Cookie를 반환합니다.
		
	```
		ex ) Cookie[] cookies = creat.cookie_enroll(id,(Key)session.getAttribute("key"));
	```
		
	<h5>boolean TokenCheck(HttpSession session)</h5> 
	 
	session 값을 받은후 session에 저장된 id 와 key 값을
	이용하여 결과값을 만들고 cookie 에 저장된 값과 비교하여 같을 경우 true 다를 경우 false 를 반환합니다.
	
	```
	ex)
		JwtCreat jwt = new JwtCreat(request, response);
		if(jwt.TokenCheck(session)){
			return "check";
		}else{
			return "home";
		}
	```


+ <h4> 버전 </h4>

  1.0.0 (2016-01-16) - jwt 부분을 구현하였습니다. <br>
  1.1.0 (2016-01-16) - riot api를 추가하여 구현하였습니다.<br>
  1.1.1 (2016-01-18) - riot api 부분의 오류를 수정하였습니다.<br>
  

+ <h4> question </h4>

  lusiue@gmail.com
