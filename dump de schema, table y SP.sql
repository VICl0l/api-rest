--------------------------------------------------------
-- Archivo creado  - jueves-mayo-30-2024   
--------------------------------------------------------

ALTER SESSION SET "_ORACLE_SCRIPT"=true;
CREATE USER USER_DBA IDENTIFIED BY PASS;
GRANT DBA TO USER_DBA;
CONNECT USER_DBA/PASS;

--------------------------------------------------------
--  DDL for Table PRODUCTO
--------------------------------------------------------

  CREATE TABLE "USER_DBA"."PRODUCTO" 
   (	"ID_PRODUCTO" NUMBER, 
	"NOMBRE" VARCHAR2(100 BYTE), 
	"FEC_REGISTRO" DATE DEFAULT SYSDATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
REM INSERTING into USER_DBA.PRODUCTO
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index SYS_C007460
--------------------------------------------------------

  CREATE UNIQUE INDEX "USER_DBA"."SYS_C007460" ON "USER_DBA"."PRODUCTO" ("ID_PRODUCTO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

--------------------------------------------------------
--  DDL for Procedure SP_INSERTANDLISTPRODUCTS
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "USER_DBA"."SP_INSERTANDLISTPRODUCTS" (
    p_id_producto       IN  NUMBER,
    p_nombre            IN  VARCHAR2,
    p_fec_registro      IN  DATE,
    p_cursor            OUT SYS_REFCURSOR,
    p_codigo_respuesta  OUT NUMBER,
    p_mensaje_respuesta OUT VARCHAR2
) AS
BEGIN
    BEGIN
        -- Insertar nuevo producto
        INSERT INTO producto (id_producto, nombre, fec_registro)
        VALUES (p_id_producto, p_nombre, p_fec_registro);
        COMMIT;

        -- Si se ejecuta exitosamente...
        p_codigo_respuesta := 0;
        p_mensaje_respuesta := 'Ejecucion con exito';
    EXCEPTION
        -- En caso de error...
        WHEN OTHERS THEN
            p_codigo_respuesta := 1;
            p_mensaje_respuesta := 'Error: ' || SQLERRM;
    END;

    -- Cursor para obtener todos los productos registrados en el día
    OPEN p_cursor FOR
        SELECT id_producto, nombre, fec_registro
        FROM producto
        WHERE TRUNC(fec_registro) = TRUNC(SYSDATE);

END sp_insertAndListProducts;

/
