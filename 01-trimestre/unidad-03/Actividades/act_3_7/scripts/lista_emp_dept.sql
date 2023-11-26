CREATE OR REPLACE FUNCTION lista_emp_dept(p_dept VARCHAR)
RETURNS SETOF empleados
LANGUAGE 'plpgsql'
AS $BODY$
DECLARE 
    mi_empleado empleados;
BEGIN
    FOR mi_empleado IN
        SELECT e.* FROM public.empleados e 
        JOIN public.departamentos d ON e.depno = d.depno 
        WHERE lower(d.nombre) LIKE lower(p_dept)
    LOOP
        RETURN NEXT mi_empleado;
    END LOOP;

    RETURN;
END;
$BODY$;
