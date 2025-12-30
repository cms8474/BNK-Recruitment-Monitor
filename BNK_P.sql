/* ==============================================
   BNK 채용 모니터링 시스템 DB 초기화 스크립트
   ============================================== */

-- 1. 채용 공고 테이블 생성 (RECRUIT_POST)
CREATE TABLE RECRUIT_POST (
    ID           VARCHAR2(255)  NOT NULL,                   -- URL 기반 고유 ID
    TITLE        VARCHAR2(500)  NOT NULL,                   -- 공고 제목
    URL          VARCHAR2(1000) NOT NULL,                   -- 공고 링크 (핵심 식별자)
    START_DATE   DATE,                                      -- 접수 시작일
    END_DATE     DATE,                                      -- 접수 마감일
    LAST_UPDATED TIMESTAMP      DEFAULT CURRENT_TIMESTAMP,  -- 정보 갱신 시각
    STATUS       VARCHAR2(20)   DEFAULT '접수중',           -- 공고 상태 (접수중/종료)
    CONSTRAINT PK_RECRUIT_POST PRIMARY KEY (ID)
);

-- 2. 크롤링 로그 테이블 생성 (CRAWL_LOG)
CREATE TABLE CRAWL_LOG (
    LOG_ID        NUMBER         NOT NULL,                  -- 로그 고유 번호
    CRAWL_TIME    TIMESTAMP      DEFAULT CURRENT_TIMESTAMP, -- 실행 시간
    ACTIVE_COUNT  NUMBER         DEFAULT 0,                 -- 발견된 공고 수
    ACTIVE_TITLES CLOB,                                     -- 당시 공고 제목들 (긴 문자열)
    CONSTRAINT PK_CRAWL_LOG PRIMARY KEY (LOG_ID)
);

-- 3. 로그 ID용 시퀀스 생성 (SEQ_CRAWL_LOG_ID)
CREATE SEQUENCE SEQ_CRAWL_LOG_ID
    START WITH 1
    INCREMENT BY 1
    NOCACHE;

-- 4. 변경사항 반영
COMMIT;

/* ==============================================
   데이터 확인용 쿼리
   ============================================== */
SELECT * FROM CRAWL_LOG ORDER BY LOG_ID DESC;
SELECT * FROM RECRUIT_POST;