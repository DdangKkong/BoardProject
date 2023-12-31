# 게시판 프로젝트

## 프로젝트 개발 환경

- IDE(IntelliJ)
- JDK 11
- Tomcat 9.0.54
- Spring 2.5.6
- MySQL 8
- JPA

## 프로젝트 기능 및 설계

- 게시글 작성 기능
  - 로그인한 멤버가 글을 작성할 수 있다.
  - 멤버는 게시글 제목(텍스트), 게시글 내용(텍스트)를 작성할 수 있다.

- 게시글 수정 기능
  - 게시글을 작성한 멤버만 해당 게시글을 수정할 수 있다.
  - 게시글의 제목과 내용을 수정할 수 있다.

- 게시글 삭제 기능
    - 게시글을 작성한 멤버만 해당 게시글을 삭제할 수 있다.

- 게시글 목록 조회 기능
  - 로그인하지 않은 사용자도 게시글을 검색할 수 있다.
  - 게시글은 종류가 많을수 있으므로 paging 처리를 한다.

- 게시글 검색 기능
  - 게시글 제목, 게시글 내용, 작성자로 검색한다.

- 회원가입 기능
  - 회원가입시 MEMBER가 된다.
  - 회원가입시 아이디는 유일해야한다.

- 로그인 기능
  - 로그인시 아이디와 패스워드가 일치해야한다.

- 댓글 작성 기능
  - 로그인한 모든 멤버는 댓글을 작성할 수 있다.

## ERD

![ERD](https://github.com/DdangKkong/BoardProject/assets/131670203/aae21fd2-40ee-4ce1-a563-4f4cfe536754)

## 주차별 개발 계획

#### 2주차 : 게시글 작성, 수정, 삭제, 검색, 조회

#### 3주차 : 회원가입 및 로그인

#### 4주차 : 댓글 작성

#### 5주차 : 개발하며 미흡한 점 보완 및 추가


## Tech Stack
<div align=center> 
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> 
  <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> 
  <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> 
  <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
</div>