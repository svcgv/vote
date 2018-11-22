/*
Navicat Oracle Data Transfer
Oracle Client Version : 10.2.0.5.0

Source Server         : test
Source Server Version : 110200
Source Host           : localhost:1521
Source Schema         : VOTE

Target Server Type    : ORACLE
Target Server Version : 110200
File Encoding         : 65001

Date: 2018-11-22 17:44:08
*/


-- ----------------------------
-- Table structure for PM_PAYMENT_POINT
-- ----------------------------
DROP TABLE "VOTE"."PM_PAYMENT_POINT";
CREATE TABLE "VOTE"."PM_PAYMENT_POINT" (
"PAYMENT_ID" NUMBER(10) NOT NULL ,
"PAYMENT_FOREIGN_ID" NUMBER(10) DEFAULT NULL  NULL ,
"PAYMENT_FOREIGN_CODE" VARCHAR2(32 BYTE) DEFAULT NULL  NULL ,
"PAYMENT_TYPE" CHAR(2 BYTE) DEFAULT NULL  NULL ,
"PAYMENT_DATE" VARCHAR2(32 BYTE) DEFAULT NULL  NULL ,
"PAYMENT_TERM" NUMBER(10,2) DEFAULT NULL  NULL ,
"PAYMENT_RATE" NUMBER(10,4) DEFAULT NULL  NULL ,
"PAYMENT_AMOUNT" NUMBER(10,2) DEFAULT NULL  NULL ,
"REMARK" VARCHAR2(256 BYTE) DEFAULT NULL  NULL ,
"CREATOR_ID" NUMBER(10) DEFAULT NULL  NULL ,
"CREATE_TIME" VARCHAR2(32 BYTE) DEFAULT NULL  NULL ,
"MODIFIER" NUMBER(10) DEFAULT NULL  NULL ,
"MODIFY_TIME" VARCHAR2(32 BYTE) DEFAULT NULL  NULL ,
"IS_DELETE" CHAR(2 BYTE) DEFAULT NULL  NULL ,
"PAYMENT_CODE" VARCHAR2(40 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Indexes structure for table PM_PAYMENT_POINT
-- ----------------------------

-- ----------------------------
-- Checks structure for table PM_PAYMENT_POINT
-- ----------------------------
ALTER TABLE "VOTE"."PM_PAYMENT_POINT" ADD CHECK ("PAYMENT_ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table PM_PAYMENT_POINT
-- ----------------------------
ALTER TABLE "VOTE"."PM_PAYMENT_POINT" ADD PRIMARY KEY ("PAYMENT_ID");
