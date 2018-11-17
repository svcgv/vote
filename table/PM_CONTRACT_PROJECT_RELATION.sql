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

Date: 2018-11-17 11:12:21
*/


-- ----------------------------
-- Table structure for PM_CONTRACT_PROJECT_RELATION
-- ----------------------------
DROP TABLE "VOTE"."PM_CONTRACT_PROJECT_RELATION";
CREATE TABLE "VOTE"."PM_CONTRACT_PROJECT_RELATION" (
"CONTRACT_PROJECT_RELATION_ID" NUMBER(10) NOT NULL ,
"CONTRACT_ID" NUMBER(10) NULL ,
"WBS" VARCHAR2(32 BYTE) NULL ,
"PROJECT_ID" NUMBER(10) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Indexes structure for table PM_CONTRACT_PROJECT_RELATION
-- ----------------------------

-- ----------------------------
-- Checks structure for table PM_CONTRACT_PROJECT_RELATION
-- ----------------------------
ALTER TABLE "VOTE"."PM_CONTRACT_PROJECT_RELATION" ADD CHECK ("CONTRACT_PROJECT_RELATION_ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table PM_CONTRACT_PROJECT_RELATION
-- ----------------------------
ALTER TABLE "VOTE"."PM_CONTRACT_PROJECT_RELATION" ADD PRIMARY KEY ("CONTRACT_PROJECT_RELATION_ID");
