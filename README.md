# 게시판 API
### 설명
👩‍👧게시글 작성을 통해 자신의 생각을 공유하고 타인과 소통해보세요👩‍👧
---- 
### 📃 API 문서
* http://localhost:8085/swagger-ui/index.html#/
---- 
### 💻 개발환경
<img src="https://img.shields.io/badge/Springboot-6DB33F?style=for-the-badge&logo=Springboot&logoColor=white">
<img src="https://img.shields.io/badge/springsecurity-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white">
<img src="https://img.shields.io/badge/intellijidea-000000?style=for-the-badge&logo=intellijidea&logoColor=white">
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">

---- 
### 📝 주요기능
1. 회원가입 / 로그인
  * Spring Security + JWT를 사용하여 회원가입 후 로그인 시 access-token을 받아온다
2. 게시판
  * header에 access-token을 넣고 게시글을 작성한다.
  * 게시글 id를 내림차순으로 최대 100개의 게시글을 볼 수 있다.(페이징)
  * header에 access-token을 넣고 게시글 id로 게시글 수정이 가능하다. (작성자 이외 수정불가)
  * header에 access-token을 넣고 게시글 id로 게시글 삭제가 가능하다. (작성자 이외 삭제불가)
  * 키워드로 게시글 검색 가능
3. 댓글 



