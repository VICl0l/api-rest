-- CREACIÓN DE LA TABLA producto
CREATE TABLE producto (
    id_producto NUMBER PRIMARY KEY,
    nombre VARCHAR2(100) NOT NULL,
    fec_registro DATE DEFAULT SYSDATE
);

-- CREACIÓN DE STORED PROCEDURE sp_insertAndListProducts
CREATE OR REPLACE PROCEDURE sp_insertAndListProducts (
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