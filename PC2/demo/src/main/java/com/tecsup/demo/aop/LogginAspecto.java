package com.tecsup.demo.aop;

import com.tecsup.demo.modelo.entidades.Tipo;
import com.tecsup.demo.modelo.entidades.Sede;
import com.tecsup.demo.modelo.entidades.Auditoria;
import com.tecsup.demo.modelo.daos.AuditoriaRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;
import java.util.Calendar;

@Component
@Aspect
public class LogginAspecto {

    private Long tx;

    @Autowired
    private AuditoriaRepository auditoriaRepository;

    @Around("execution(* com.tecsup.demo.servicios.*ServiceImpl.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        Long currTime = System.currentTimeMillis();
        tx = System.currentTimeMillis();
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        String metodo = "tx[" + tx + "] - " + joinPoint.getSignature().getName();

        if (joinPoint.getArgs().length > 0) {
            logger.info(metodo + "() INPUT:" + Arrays.toString(joinPoint.getArgs()));
        }
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            logger.error(e.getMessage());
        }
        logger.info(metodo + "(): tiempo transcurrido " + (System.currentTimeMillis() - currTime) + " ms.");
        return result;
    }

    @After("execution(* com.tecsup.demo.controladores.*Controller.guardar*(..)) ||" +
            "execution(* com.tecsup.demo.controladores.*Controller.editar*(..)) ||" +
            "execution(* com.tecsup.demo.controladores.*Controller.eliminar*(..))")
    public void auditoria(JoinPoint joinPoint) {
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        String metodo = joinPoint.getSignature().getName();
        Integer id = null; // Cambiado a Integer

        Object[] parametros = joinPoint.getArgs();
        if (parametros.length == 0) {
            logger.error("No se proporcionaron argumentos para el método.");
            return;
        }

        Object entidad = parametros[0];

        try {
            if (metodo.startsWith("guardar") && entidad instanceof Tipo) {
                Tipo tipo = (Tipo) entidad;
                id = tipo.getId(); // Asigna directamente el id como Integer
            } else if (metodo.startsWith("guardar") && entidad instanceof Sede) {
                Sede sede = (Sede) entidad;
                id = sede.getId(); // Asigna directamente el id como Integer
            } else if (metodo.startsWith("editar") || metodo.startsWith("eliminar")) {
                if (entidad instanceof Integer) { // Verifica si es Integer
                    id = (Integer) entidad; // Asigna directamente el id como Integer
                } else {
                    logger.error("El parámetro no es de tipo Integer para editar o eliminar.");
                    return;
                }
            } else {
                logger.error("Tipo de entidad no soportado para la auditoría.");
                return;
            }

            if (id != null) {
                String traza = "tx[" + tx + "] - " + metodo;
                logger.info(traza + "(): registrando auditoria...");
                auditoriaRepository.save(new Auditoria(entidad.getClass().getSimpleName().toLowerCase(), id, Calendar.getInstance().getTime(), "usuario", metodo));
            }
        } catch (Exception e) {
            logger.error("Error al registrar la auditoría: " + e.getMessage());
        }
    }
}
