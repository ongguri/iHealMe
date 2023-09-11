-- INSERT INTO users (user_id, answer, birth_date, business_num, email, gender, hpt_address, hpt_name, hpt_phone_num, name, password,phone_num, question, user_role) VALUES (1, 'dd', '991231', null, 'longlee@daum.net', '남', null, null, null, '이성구', '1234', '01012341234', '질문1','USER');
-- INSERT INTO users (user_id, answer, birth_date, business_num, email, gender, hpt_address, hpt_name, hpt_phone_num, name, password,phone_num, question, user_role) VALUES (2, 'dd', '991231', null, 'bang@naver.com', '남', null, null, null, '방기웅', '1234', '01012341234', '질문1','USER');
-- INSERT INTO users (user_id, answer, birth_date, business_num, email, gender, hpt_address, hpt_name, hpt_phone_num, name, password,phone_num, question, user_role) VALUES (3, 'dd', '991231', null, 'sebom@hospital.co.kr', '여', null, null, null, '안유진', '1234', '01012341234', '질문1','HOSPITAL');

-- USERRESERVATION TABLE(20)
INSERT INTO USERRESERVATION(RESNO, PATIENTNAME, TXLIST) VALUES (userreservation_no_seq.nextval, '새롬소아청년과의원1', '영유아 검진1');
INSERT INTO USERRESERVATION(RESNO, PATIENTNAME, TXLIST) VALUES (userreservation_no_seq.nextval, '새롬소아청년과의원2', '영유아 검진2');
INSERT INTO USERRESERVATION(RESNO, PATIENTNAME, TXLIST) VALUES (userreservation_no_seq.nextval, '새롬소아청년과의원3', '영유아 검진3');
INSERT INTO USERRESERVATION(RESNO, PATIENTNAME, TXLIST) VALUES (userreservation_no_seq.nextval, '새롬소아청년과의원4', '영유아 검진4');
INSERT INTO USERRESERVATION(RESNO, PATIENTNAME, TXLIST) VALUES (userreservation_no_seq.nextval, '새롬소아청년과의원5', '영유아 검진5');

INSERT INTO USERRESERVATION(RESNO, PATIENTNAME, TXLIST) VALUES (userreservation_no_seq.nextval, '새롬소아청년과의원6', '영유아 검진6');
INSERT INTO USERRESERVATION(RESNO, PATIENTNAME, TXLIST) VALUES (userreservation_no_seq.nextval, '새롬소아청년과의원7', '영유아 검진7');
INSERT INTO USERRESERVATION(RESNO, PATIENTNAME, TXLIST) VALUES (userreservation_no_seq.nextval, '새롬소아청년과의원8', '영유아 검진8');
INSERT INTO USERRESERVATION(RESNO, PATIENTNAME, TXLIST) VALUES (userreservation_no_seq.nextval, '새롬소아청년과의원9', '영유아 검진9');
INSERT INTO USERRESERVATION(RESNO, PATIENTNAME, TXLIST) VALUES (userreservation_no_seq.nextval, '새롬소아청년과의원10', '영유아 검진10');

INSERT INTO USERRESERVATION(RESNO, PATIENTNAME, TXLIST) VALUES (userreservation_no_seq.nextval, '새롬소아청년과의원11', '영유아 검진11');
INSERT INTO USERRESERVATION(RESNO, PATIENTNAME, TXLIST) VALUES (userreservation_no_seq.nextval, '새롬소아청년과의원12', '영유아 검진12');
INSERT INTO USERRESERVATION(RESNO, PATIENTNAME, TXLIST) VALUES (userreservation_no_seq.nextval, '새롬소아청년과의원13', '영유아 검진13');
INSERT INTO USERRESERVATION(RESNO, PATIENTNAME, TXLIST) VALUES (userreservation_no_seq.nextval, '새롬소아청년과의원14', '영유아 검진14');
INSERT INTO USERRESERVATION(RESNO, PATIENTNAME, TXLIST) VALUES (userreservation_no_seq.nextval, '새롬소아청년과의원15', '영유아 검진15');

INSERT INTO USERRESERVATION(RESNO, PATIENTNAME, TXLIST) VALUES (userreservation_no_seq.nextval, '새롬소아청년과의원16', '영유아 검진16');
INSERT INTO USERRESERVATION(RESNO, PATIENTNAME, TXLIST) VALUES (userreservation_no_seq.nextval, '새롬소아청년과의원17', '영유아 검진17');
INSERT INTO USERRESERVATION(RESNO, PATIENTNAME, TXLIST) VALUES (userreservation_no_seq.nextval, '새롬소아청년과의원18', '영유아 검진18');
INSERT INTO USERRESERVATION(RESNO, PATIENTNAME, TXLIST) VALUES (userreservation_no_seq.nextval, '새롬소아청년과의원19', '영유아 검진19');
INSERT INTO USERRESERVATION(RESNO, PATIENTNAME, TXLIST) VALUES (userreservation_no_seq.nextval, '새롬소아청년과의원20', '영유아 검진20');

-- HPTRECEPTION TABLE(15)
INSERT INTO HPTRECEPTION(RECNO, USER_RESERVATION_RESNO) VALUES (HPTRECEPTION_NO_SEQ.nextval, 1);
INSERT INTO HPTRECEPTION(RECNO, USER_RESERVATION_RESNO) VALUES (HPTRECEPTION_NO_SEQ.nextval, 4);
INSERT INTO HPTRECEPTION(RECNO, USER_RESERVATION_RESNO) VALUES (HPTRECEPTION_NO_SEQ.nextval, 6);
INSERT INTO HPTRECEPTION(RECNO, USER_RESERVATION_RESNO) VALUES (HPTRECEPTION_NO_SEQ.nextval, 8);
INSERT INTO HPTRECEPTION(RECNO, USER_RESERVATION_RESNO) VALUES (HPTRECEPTION_NO_SEQ.nextval, 3);

INSERT INTO HPTRECEPTION(RECNO, USER_RESERVATION_RESNO) VALUES (HPTRECEPTION_NO_SEQ.nextval, 10);
INSERT INTO HPTRECEPTION(RECNO, USER_RESERVATION_RESNO) VALUES (HPTRECEPTION_NO_SEQ.nextval, 13);
INSERT INTO HPTRECEPTION(RECNO, USER_RESERVATION_RESNO) VALUES (HPTRECEPTION_NO_SEQ.nextval, 20);
INSERT INTO HPTRECEPTION(RECNO, USER_RESERVATION_RESNO) VALUES (HPTRECEPTION_NO_SEQ.nextval, 17);
INSERT INTO HPTRECEPTION(RECNO, USER_RESERVATION_RESNO) VALUES (HPTRECEPTION_NO_SEQ.nextval, 5);

INSERT INTO HPTRECEPTION(RECNO, USER_RESERVATION_RESNO) VALUES (HPTRECEPTION_NO_SEQ.nextval, 14);
INSERT INTO HPTRECEPTION(RECNO, USER_RESERVATION_RESNO) VALUES (HPTRECEPTION_NO_SEQ.nextval, 19);
INSERT INTO HPTRECEPTION(RECNO, USER_RESERVATION_RESNO) VALUES (HPTRECEPTION_NO_SEQ.nextval, 2);
INSERT INTO HPTRECEPTION(RECNO, USER_RESERVATION_RESNO) VALUES (HPTRECEPTION_NO_SEQ.nextval, 7);
INSERT INTO HPTRECEPTION(RECNO, USER_RESERVATION_RESNO) VALUES (HPTRECEPTION_NO_SEQ.nextval, 11);
