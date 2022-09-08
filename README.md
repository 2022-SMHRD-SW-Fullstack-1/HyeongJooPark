# 미니프로젝트/노래맞추기게임
* 조이름 : 데이터 구조

## 1. 프로젝트목적
* 게임을 만들어보자
  * MVC패턴 사용
  * JDBC를 사용


## 2. 개발환경
![스택 아이콘](https://user-images.githubusercontent.com/112370961/189016899-641f3807-6019-4e83-a605-eaf47ebe2626.png)
* 사용언어 : Java 1.8
* 개발도구 : Eclipse
* 데이터베이스 : Oracle, Dbeaver

## 3. 게임 설명
- 274개의 노래 중 랜덤으로 재생되는 열 가지 노래의 제목을 맞추는 게임
- 들리는 노래를 듣고 노래 제목을 맞춰주세요!

## 4. 대표 이미지
![캡처](https://user-images.githubusercontent.com/112370767/189011856-05ab8d8b-f985-4c0b-b34c-38f5e25d117e.PNG)

## 5. ERD
![image](https://user-images.githubusercontent.com/112370961/189013278-fe219766-10b5-42d7-ba10-4c203cb2aec8.png)
```sql
//회원관리 테이블 생성
CREATE TABLE user_info( 
id varchar2(20),
pw varchar2(20) NOT NULL,
nick varchar2(20) NOT NULL,
CONSTRAINT user_info_id_pk PRIMARY KEY(id),
CONSTRAINT user_info_nick_uk UNIQUE(nick));
```
```sql
//점수 테이블 생성
CREATE TABLE user_score(
id varchar2(20) NOT NULL,
score number(5) NOT NULL,
res_date date default sysdate NOT NULL,
CONSTRAINT score_nick_fk FOREIGN KEY(id) REFERENCES user_info(id) ON DELETE CASCADE);
```
```sql
//음악목록 테이블 생성
CREATE TABLE musiclist(
path VARCHAR2(80),
singer VARCHAR2(30) NOT NULL,
title VARCHAR2(50) NOT NULL,
head VARCHAR2(50) NOT NULL,
CONSTRAINT musiclist_path_pk PRIMARY KEY(path));
```

## 6. UCD, 기능흐름도
![image](https://user-images.githubusercontent.com/112370961/189018126-17cf7c19-9d7a-4497-8b4b-a0bb9d08e142.png)


## 7. 실행화면예시
https://user-images.githubusercontent.com/112370767/189015973-eaa3e751-f55c-4050-b4dd-b69d3c25cd2b.mp4

## 8. 제작일정
일정|상세내용
---|---
22.09.05|아이디어 기획 및 DB 설계
22.09.06|데이터 수집 및 추가, 기능 구현
22.09.07|발표 자료 및 시연 영상 준비
