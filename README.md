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

```
라고 넣어준다.
구글은 scope `spring.security.oauth2.client.registration.google.scope=profile,email` 안넣어주면 로그인 안됨.

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
