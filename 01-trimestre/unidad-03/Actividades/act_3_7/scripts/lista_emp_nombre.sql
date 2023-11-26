CREATE OR REPLACE FUNCTION lista_emp_nombre(p_nombre VARCHAR)
RETURNS SETOF empleados
LANGUAGE 'plpgsql'
AS $BODY$
DECLARE 
    mi_empleado empleados;
    patron VARCHAR := '%' || p_nombre || '%';
BEGIN
    FOR mi_empleado IN
        SELECT * FROM empleados WHERE lower(nombre) LIKE lower(patron)
    LOOP
        RETURN NEXT mi_empleado;
    END LOOP;

    RETURN;
END;
$BODY$;
