-- session_table
DROP TABLE session_table;
CREATE TABLE session_table (
  sno number,
  sdate date,
  reason varchar2(500),
  member_id varchar2(50),
  primary key(sno)
);

-- member
DROP TABLE member_table;
CREATE TABLE member_table (
  id varchar2(50),
  passwd varchar2(100),
  name varchar2(45),
  nick varchar2(50),
  email varchar2(100),
  primary key(id)
);

-- review_table
DROP TABLE review_table;
CREATE TABLE review_table (
  review_content varchar2(2000),
  date date,
  rno number,
  Board_table_bno number,
  member_id varchar2(50),
  primary key(rno)
);

-- like_table
DROP TABLE like_table;
CREATE TABLE like_table (
  review_table_rno number,
  member_id varchar2(50),
  primary key(review_table_rno, member_id)
);

-- hate_table
DROP TABLE hate_table;
CREATE TABLE hate_table (
  review_table_rno number,
  member_id varchar2(50),
  primary key(review_table_rno, member_id)
);

-- star_table
DROP TABLE star_table;
CREATE TABLE star_table (
  star_no number,
  Board_table_bno number,
  member_id varchar2(50)
);

-- heart_table
DROP TABLE heart_table;
CREATE TABLE heart_table (
  Board_table_bno number,
  member_id varchar2(50),
  primary key(Board_table_bno, member_id)
);

-- Board_table
DROP TABLE Board_table;
CREATE TABLE Board_table (
  bno number,
  title varchar2(90),
  pd varchar2(50),
  casting varchar2(200),
  content varchar2(2000),
  poster varchar2(100),
  new_date date,
  expire_date date,
  genre number,
  poster_video varchar2(100),
  ott_no number,
  primary key(bno)
);

-- main_banner_table
DROP TABLE main_banner_table;
CREATE TABLE main_banner_table (
  banner_no number,
  banner_img varchar2(50),
  primary key(banner_no)
);

-- manager_table
DROP TABLE manager_table;
CREATE TABLE manager_table (
  mid varchar2(50),
  mpasswd varchar2(100),
  mname varchar2(45),
  primary key(mid)
);

-- ott_table
DROP TABLE ott_table;
CREATE TABLE ott_table (
  ott_no number,
  ott_name varchar2(50),
  primary key(ott_no)
);

-- inquiry_table
DROP TABLE inquiry_table;
CREATE TABLE inquiry_table (
  qa_no number,
  qa_date date,
  qa_type varchar2(50),
  qa_email varchar2(100),
  qa_title varchar2(500),
  qa_content varchar2(2000),
  Board_table_bno number,
  member_id varchar2(50),
  primary key(qa_no)
)

-- board_file
DROP TABLE board_file;
CREATE TABLE board_file (
  path varchar2(200),
  filename varchar2(100),
  type varchar2(50),
  bno number,
  fno number,
  newDate date,
  primary key(fno)
);

-- board_image
DROP TABLE board_image;
CREATE TABLE board_image (
  fno number,
  path varchar2(200)
);

-- board_bno_seq
DROP SEQUENCE board_bno_seq;
CREATE SEQUENCE board_bno_seq
MINVALUE 1
MAXVALUE 999999999
INCREMENT BY 1
START WITH 202700
NOCACHE
NOCYCLE;