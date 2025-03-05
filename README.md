![image](https://github.com/user-attachments/assets/43606224-ef69-4c0a-9128-c937324b4fcd)

왓챠피디아(WatchaPedia) 클론코딩 관련 제가 담당한 파트를 백업하고 관리하는 프로젝트입니다.  
Vue.js와 Spring Boot를 활용하여 검색 기능과 로그인 기능을 구현하였으며,  
실시간 검색 추천, 세션 기반 로그인, 컨텐츠 등록 시스템 등을 포함하고 있습니다.

---

## 📌 프로젝트 개요

**- 프로젝트명:** WatchaPediaBackUp  
**- 주요 기능:**  
  - 실시간 검색 추천 (Vue.js & Ajax)
  - 회원가입 및 로그인 기능 (JPA, Spring Security 활용)
  - 세션을 활용한 사용자 정보 관리
  - 컨텐츠(영화) 등록 시 감독 및 배우 등록 기능

---

## 📂 프로젝트 구조

```plaintext
watchaPediaBackUp/
├── .idea/                  # 프로젝트 설정 파일
├── .jpb/                   # (설명이 필요합니다)
└── watchPedia_User/        # 사용자 관련 파일
    ├── src/
    │   ├── main/
    │   │   ├── java/
    │   │   │   └── com/
    │   │   │       └── watchPedia/
    │   │   │           ├── controller/    # 컨트롤러 계층
    │   │   │           ├── model/         # 도메인 모델
    │   │   │           ├── repository/    # 데이터 액세스 레이어
    │   │   │           └── service/       # 비즈니스 로직
    │   │   └── resources/                 # 설정 및 정적 리소스
    │   └── test/                          # 테스트 코드
    ├── build.gradle                       # Gradle 빌드 파일
    └── settings.gradle                    # Gradle 설정 파일
```
---

## 🚀 주요 기능

### 🔹 회원가입 & 로그인 기능
- Spring Security & JPA를 활용한 로그인 로직
- 로그인된 사용자 세션을 유지하고, 사용자 정보를 페이지에 전달
- 로그인하지 않은 경우 인기 검색어 제공, 로그인 시 최근 검색어 기능 제공

### 🔹 실시간 검색 추천
- Vue.js와 Ajax를 활용한 실시간 입력값 검증 및 검색어 추천
- JPA의 `Containing`을 활용한 연관 검색어 구현

### 🔹 컨텐츠 등록 기능
- 검색어 입력 시 해당 인물을 검색하여 감독 및 배우로 등록 가능
- 등록된 컨텐츠는 최신 컨텐츠 목록에 추가됨

---

## 🛠 기술 스택

### 📌 Frontend
- **Vue.js**: 화면 UI 개발
- **Ajax**: 백엔드 API와 통신

### 📌 Backend
- **Spring Boot**: REST API 서버
- **JPA (Hibernate)**: ORM 기반 데이터베이스 관리
- **MySQL**: 데이터 저장

### 📌 DevOps
- **Gradle**: 빌드 및 의존성 관리
- **AWS EC2 서비스** : 배포

---
