-- INSERT INTO users (user_id, answer, birth_date, business_num, email, gender, hpt_address, hpt_name, hpt_phone_num, name, password,phone_num, question, user_role) VALUES (1, 'dd', '991231', null, 'longlee@daum.net', '남', null, null, null, '이성구', '1234', '01012341234', '질문1','USER');
-- INSERT INTO users (user_id, answer, birth_date, business_num, email, gender, hpt_address, hpt_name, hpt_phone_num, name, password,phone_num, question, user_role) VALUES (2, 'dd', '991231', null, 'bang@naver.com', '남', null, null, null, '방기웅', '1234', '01012341234', '질문1','USER');
-- INSERT INTO users (user_id, answer, birth_date, business_num, email, gender, hpt_address, hpt_name, hpt_phone_num, name, password,phone_num, question, user_role) VALUES (3, 'dd', '991231', null, 'sebom@hospital.co.kr', '여', null, null, null, '안유진', '1234', '01012341234', '질문1','HOSPITAL');

-- TODO: 데이터가 없으면 해당 페이지에 오류가 발생하므로 추후 수정작업이 필요하다.
INSERT INTO HPTRECEPTION (RESNO, EMAIL, PNAME, TXLIST, CURRENTSTATUS) VALUES (HIBERNATE_SEQUENCE.NEXTVAL, 'sebom@hospital.co.kr', '안유진', '소아진료', '접수대기');

INSERT INTO userreservation(RESNO, PATIENTNAME, TXLIST) VALUES (userreservation_no_seq.nextval, '새롬소아청년과의원', '영유아 검진');
