-- 删除数据库
-- DROP DATABASE mldn ;
-- 创建数据库
-- CREATE DATABASE mldn CHARACTER SET UTF8 ;
-- 使用数据库
USE mldn ;
-- 删除数据表
DROP TABLE IF EXISTS news ;
DROP TABLE IF EXISTS item ;
-- 创建数据表
CREATE TABLE item(
  iid       INT     AUTO_INCREMENT ,
  title     VARCHAR(50) ,
  CONSTRAINT pk_iid PRIMARY KEY(iid)
) ;
CREATE TABLE news(
  nid       INT     AUTO_INCREMENT  ,
  title     VARCHAR(50) ,
  pubdate   DATETIME ,
  content   TEXT ,
  iid       INT ,
  CONSTRAINT pk_nid PRIMARY KEY(nid) ,
  CONSTRAINT fk_iid FOREIGN KEY(iid) REFERENCES item(iid) ON DELETE SET NULL
) ;
-- 增加测试数据
INSERT INTO item(title) VALUES ('娱乐新闻');
INSERT INTO item(title) VALUES ('科技新闻');
INSERT INTO item(title) VALUES ('体育新闻');
INSERT INTO item(title) VALUES ('今日头条');
INSERT INTO item(title) VALUES ('财经新闻');