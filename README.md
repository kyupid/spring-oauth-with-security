## 실행방법
1. application.properties 에서 디비설정을 해준다
2. spring.profiles.include=oauth 도 넣어준다
3. application-oauth.properties 생성한다
```
구글 
spring.security.oauth2.client.registration.google.client-id=
spring.security.oauth2.client.registration.google.client-secret=
spring.security.oauth2.client.registration.google.scope=profile,email


네이버
# registration
spring.security.oauth2.client.registration.naver.client-id=클라이언트아이디
spring.security.oauth2.client.registration.naver.client-secret=클라이언트시크릿
spring.security.oauth2.client.registration.naver.redirect-uri=http://localhost:8080/login/oauth/code/naver
spring.security.oauth2.client.registration.naver.authorization-grant-type=authorization_code
spring.security.oauth2.client.registration.naver.scope=name,email.profile_image
spring.security.oauth2.client.registration.naver.client-name=Naver

# provider
spring.security.oauth2.client.provider.naver.authorization-uri=https://nid.naver.com/oauth2.0/authorize
spring.security.oauth2.client.provider.naver.token-uri=https://nid.naver.com/oauth2.0/token
spring.security.oauth2.client.provider.naver.user-info-uri=https://openapi.naver.com/v1/nid/me
spring.security.oauth2.client.provider.naver.user-name-attribute=response

## 카카오 ##
# registration
spring.security.oauth2.client.registration.kakao.client-id=클라이언트아이디
spring.security.oauth2.client.registration.kakao.client-secret=클라이언트시크릿
spring.security.oauth2.client.registration.kakao.redirect-uri=http://localhost:8080/login/oauth2/code/kakao
spring.security.oauth2.client.registration.kakao.authorization-grant-type=authorization_code
spring.security.oauth2.client.registration.kakao.scope=profile_nickname,account_email,profile_image
spring.security.oauth2.client.registration.kakao.client-name=Kakao
spring.security.oauth2.client.registration.kakao.client-authentication-method=POST

# provider
spring.security.oauth2.client.provider.kakao.authorization-uri=https://kauth.kakao.com/oauth/authorize
spring.security.oauth2.client.provider.kakao.token-uri=https://kauth.kakao.com/oauth/token
spring.security.oauth2.client.provider.kakao.user-info-uri=https://kapi.kakao.com/v2/user/me
spring.security.oauth2.client.provider.kakao.user-name-attribute=id


```
라고 넣어준다.
구글은 scope `spring.security.oauth2.client.registration.google.scope=profile,email` 안넣어주면 로그인 안됨.
카카오는 `spring.security.oauth2.client.registration.kakao.client-authentication-method=POST` 이거 안넣어주면 안됨

## 디펜던시
```
dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'org.springframework.boot:spring-boot-starter-mustache'
	implementation 'org.springframework.boot:spring-boot-starter-security'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	runtimeOnly 'mysql:mysql-connector-java'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	testImplementation 'org.springframework.security:spring-security-test'
	compileOnly 'org.projectlombok:lombok'
	annotationProcessor 'org.projectlombok:lombok'
	implementation group: 'org.springframework.boot', name: 'spring-boot-starter-oauth2-client', version: '2.4.3'
}

```

## 기능

- CRUD
- 구글 OAuth 로그인


## 코드 출처
https://velog.io/@swchoi0329/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%8B%9C%ED%81%90%EB%A6%AC%ED%8B%B0%EC%99%80-OAuth-2.0%EC%9C%BC%EB%A1%9C-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EA%B8%B0%EB%8A%A5-%EA%B5%AC%ED%98%84


## 원리

기본적인 Authorization Code Grant 방식으로는 아래처럼 동작
1. Resource Owner -> Client 로그인해야 얻을 수 있는 제한된 자원 요청
2. Client -> Resource Server 권한 부여 승인 코드 요청 (GET | client_id, redirect_uri, response_type=code)
3. 각 Resource Server 의 로그인 화면이 나옴
4. 로그인 성공
5. Resource Server -> Client 권한 부여 승인 코드 전달
6. Client -> Resource Server 권한부여승인코드를 가지고 Access Token 요청 (POST | client_id, client_secret, redirect_uri, grant_type=authorization_code, code)
7. Resource Server -> Client 으로 Access Token 응답
8. Client -> Resource Server 로그인해야 얻을수있는 제한된 자원을 Access Token으로 요청
9. Resource Server -> Client 요청 자원 응답

### 스프링 시큐리티 & OAuth2
1. `http://client.example.com/oauth2/authorization/서비스명(google,kakao,naver)` 를통해서
Resource Owner는 Client로 자원을 요청한다(로그인)   
2. `/oauth2/authorization/서비스명`은 아마도 `spring-security-oauth2-client` 를 붙여주면   
      기본적으로 그 서비스에 대한 oauth2를 요청하는 것 같다.
3. Client `spring-security-oauth2-client`는 서비스 Authorization URL로 리다이렉트한다.
4. 이때, 요청에 필요한 정보들(client_id,client_secret 등)은 Client 서버에서 가지고 있는 상태임.    
5. 인증이 끝나면(로그인 성공) Client는 그 정보들을 가지고 Resource Server로 권한 부여 승인 코드를 요청한다.    
6. Client는 Resource Owner로부터 권한 부여 승인코드를 전달받는다
8. 이때 스프링시큐리티를 거쳐 `CustomOAuth2UserService`에서는 유저에 디비를 저장또는 업데이트한다.,
