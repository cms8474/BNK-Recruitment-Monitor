# BNK Recruitment Monitor

## 📝 프로젝트 개요 (Project Overview)
BNK 채용 정보를 주기적으로 모니터링하여 새로운 공고가 올라오면 자동으로 감지하고 이메일로 알림을 보내주는 서비스입니다.  
대상 URL으로부터 JSON형식으로 데이터를 받아 데이터베이스에 저장하고, 이전 기록과 비교하여 신규 공고 여부를 판단합니다.  

**프로젝트 시작**: 2025.12.30  
**1차 배포**: 2025.12.30  

## 🛠 기술 스택 (Tech Stack)

### Environment
- **Language**: Java 21
- **Framework**: Spring Boot 3.4.1
- **Build Tool**: Gradle

### Database
- **Database**: Oracle Database (XE)
- **ORM**: Spring Data JPA

### Web & Template
- **Template Engine**: JSP (JavaServer Pages), JSTL

### Utilities
- **Crawling**: Jsoup 1.17.2
- **Mail**: Spring Boot Starter Mail (Gmail SMTP)
- **Library**: Lombok

## ✨ 주요 기능 (Key Features)
1. **채용 공고 크롤링**: BNK 채용 사이트의 공고 목록을 5분 간격으로 스크래핑합니다.  
2. **신규 공고 알림**: 새로운 채용 공고가 감지되면 등록된 이메일로 즉시 알림을 발송합니다.  
3. **공고 이력 관리**: 크롤링된 데이터를 Oracle DB에 저장하여 중복 및 상태를 관리합니다.  

## 🚀 설치 및 실행 (Installation & Run)

### 1. 사전 요구사항 (Prerequisites)
- Java JDK 21 이상  
- Oracle Database (11g/19c XE)

### 2. 환경 설정 (Configuration)
이 프로젝트는 보안상 중요한 설정 내용을 (`application.yml`)을 담았으므로 저장소에 포함하지 않습니다.  
(Google app Password, DB 정보)
