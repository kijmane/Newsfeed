# Newsfeed
## 프로젝트 소개
사용자들이 자유롭게 게시글을 작성하고 댓글과 좋아요 기능을 통해 소통할 수 있는 SNS 뉴스피드 백엔드 프로젝트입니다.

## 개발 일정
- 2025.02.14 : 아이디어 회의 및 S.A 작성
- 2025.02.14 ~ 2025.02.18 : 웹페이지 제작 및 테스트
- 2025.02.19 : 기능 시연 및 영상 촬영

## 사용 기술
- Backend : Spring Boot , Spring Security , JPA , JWT
- Database : MySQL
- Build Tool : Gradle

## ERD 설계
<img width="739" alt="스크린샷 2025-02-18 오후 1 26 30" src="https://github.com/user-attachments/assets/a833214b-45eb-45ea-942e-800d8679cae3" />

## 주요 기능
### 회원 기능
- 회원가입 및 로그인 (JWT 인증)
- 회원정보 조회 및 수정
- 친구(팔로우) 관리

### 게시글 기능
- 게시글 작성 , 수정 , 삭제
- 게시글 좋아요 기능
- 전체 게시글 및 개별 게시글 조회

### 댓글 기능
- 게시글 별 댓글 목록 조회
- 댓글 작성 , 수정 , 삭제 (본인 혹은 게시물 소유자 가능)

## API 명세서
- 공통 예외 응답
|상태코드|설명|예시 응답|
|-------------|-----|------------|
|404 Not Found|요청 데이터 오류|{ "error": "해당 리소스가 존재하지 않습니다." }|
|403 Forbidden|권한없음|{ "error": "로그인하지 않았거나, 해당 작업을 수행할 권한이 없습니다." }|
|400 Bad Request|요청 데이터 오류|{ "error": "필수 값이 누락되었습니다." }|

- API 상세 명세 (게시글 API)
|기능|HTTP Method|URL|Request Body|Return Values|Exception/Error|
|-------------|-----|------------|
|404 Not Found|요청 데이터 오류|{ "error": "해당 리소스가 존재하지 않습니다." }|
|403 Forbidden|권한없음|{ "error": "로그인하지 않았거나, 해당 작업을 수행할 권한이 없습니다." }|
|400 Bad Request|요청 데이터 오류|{ "error": "필수 값이 누락되었습니다." }|


