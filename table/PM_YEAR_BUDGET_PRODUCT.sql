--------------------------------------------------------
--  文件已创建 - 星期三-十一月-21-2018   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table PM_YEAR_BUDGET_PRODUCT
--------------------------------------------------------

  CREATE TABLE "VOTE"."PM_YEAR_BUDGET_PRODUCT" 
   (	"PAYMENT_ID" NUMBER(10,0), 
	"PRODUCT_ID" NUMBER(10,0), 
	"YEAR_BUDGET_CODE" VARCHAR2(32 BYTE)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "VOTE_USER" ;
REM INSERTING into VOTE.PM_YEAR_BUDGET_PRODUCT
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index PM_YEAR_BUDGET_PRODUCT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "VOTE"."PM_YEAR_BUDGET_PRODUCT_PK" ON "VOTE"."PM_YEAR_BUDGET_PRODUCT" ("PAYMENT_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS NOCOMPRESS LOGGING
  TABLESPACE "VOTE_USER" ;
--------------------------------------------------------
--  Constraints for Table PM_YEAR_BUDGET_PRODUCT
--------------------------------------------------------

  ALTER TABLE "VOTE"."PM_YEAR_BUDGET_PRODUCT" ADD CONSTRAINT "PM_YEAR_BUDGET_PRODUCT_PK" PRIMARY KEY ("PAYMENT_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS NOCOMPRESS LOGGING
  TABLESPACE "VOTE_USER"  ENABLE;
 
  ALTER TABLE "VOTE"."PM_YEAR_BUDGET_PRODUCT" MODIFY ("PAYMENT_ID" NOT NULL ENABLE);
