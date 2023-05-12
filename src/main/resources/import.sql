INSERT INTO users (user_id, answer, birth_date, business_num, email, gender, hpt_address, hpt_name, hpt_phone_num, name, password,phone_num, question, user_role) VALUES (1, 'dd', '991231', null, 'longlee@daum.net', '남', null, null, null, '이성구', '1234', '01012341234', '질문1','USER')
INSERT INTO users (user_id, answer, birth_date, business_num, email, gender, hpt_address, hpt_name, hpt_phone_num, name, password,phone_num, question, user_role) VALUES (2, 'dd', '991231', null, 'bang@naver.com', '남', null, null, null, '방기웅', '1234', '01012341234', '질문1','USER')
INSERT INTO users (user_id, answer, birth_date, business_num, email, gender, hpt_address, hpt_name, hpt_phone_num, name, password,phone_num, question, user_role) VALUES (2, 'dd', '991231', null, 'sebom@hospital.co.kr', '여', null, null, null, '안유진', '1234', '01012341234', '질문1','HOSPITAL')

commit;

INSERT INTO HPTRECEPTION (RESNO, EMAIL, PNAME, TXLIST, CURRENTSTATUS) VALUES (HIBERNATE_SEQUENCE.NEXTVAL, 'sebom@hospital.co.kr', '안유진', '소아진료', '접수대기');
INSERT INTO userreservation(resno, email, name, list) VALUES (userreservation_no_seq.nextval, 'longlee@daum.net', '새롬소아청년과의원', '영유아 검진');
commit;
/*
INSERT INTO userreservation(resno, email, name, list, regdate) VALUES (userreservation_no_seq.nextval, 'longlee@daum.net', '새롬소아청년과의원', '영유아 검진', sysdate);
INSERT INTO userreservation(resno, email, name, list, currentStatus, regdate) VALUES (userreservation_no_seq.nextval, 'longlee@daum.net', '이지소아청년과의원', '영유아 검진', '진료완료', sysdate);
INSERT INTO userreservation(resno, email, name, list, currentStatus, regdate) VALUES (userreservation_no_seq.nextval, 'longlee@daum.net', '새롬소아청년과의원', '영유아 검진', '진료완료', sysdate);
INSERT INTO userreservation(resno, email, name, list, currentStatus, regdate) VALUES (userreservation_no_seq.nextval, 'longlee@daum.net', '새롬소아청년과의원', '영유아 검진', '진료완료', sysdate);
INSERT INTO userreservation(resno, email, name, list, currentStatus, regdate) VALUES (userreservation_no_seq.nextval, 'longlee@daum.net', '이지소아청년과의원', '영유아 검진', '진료완료', sysdate);
INSERT INTO userreservation(resno, email, name, list, currentStatus, regdate) VALUES (userreservation_no_seq.nextval, 'longlee@daum.net', '이지소아청년과의원', '영유아 검진', '진료완료', sysdate);
INSERT INTO userreservation(resno, email, name, list, currentStatus, regdate) VALUES (userreservation_no_seq.nextval, 'longlee@daum.net', '새롬소아청년과의원', '영유아 검진', '진료완료', sysdate);
INSERT INTO userreservation(resno, email, name, list, currentStatus, regdate) VALUES (userreservation_no_seq.nextval, 'longlee@daum.net', '새롬소아청년과의원', '영유아 검진', '진료완료', sysdate);
INSERT INTO userreservation(resno, email, name, list, currentStatus, regdate) VALUES (userreservation_no_seq.nextval, 'longlee@daum.net', '이지소아청년과의원', '영유아 검진', '진료완료', sysdate);
INSERT INTO userreservation(resno, email, name, list, currentStatus, regdate) VALUES (userreservation_no_seq.nextval, 'longlee@daum.net', '이지소아청년과의원', '영유아 검진', '진료완료', sysdate);
INSERT INTO userreservation(resno, email, name, list, currentStatus, regdate) VALUES (userreservation_no_seq.nextval, 'longlee@daum.net', '이지소아청년과의원', '영유아 검진', '진료완료', sysdate);
INSERT INTO userreservation(resno, email, name, list, currentStatus, regdate) VALUES (userreservation_no_seq.nextval, 'longlee@daum.net', '새롬소아청년과의원', '영유아 검진', '진료완료', sysdate);
commit;*/