package org.upiita.spring.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Logging {
	
	@Before("execution(* org.upiita.spring.dao.iUsuarioDAO.getUsuario(..))")
	public void antesDeInvocar(JoinPoint jp){
		
	 System.out.println("Antes D:");
	 System.out.println("Argumentos" + Arrays.asList(jp.getArgs()).get(0));
	 
	}
	public void despuesDeInvocar(){
		 System.out.println("Despues D: D: D:");
		}
	public Object alrededor(ProceedingJoinPoint joinPoint) throws Throwable {
		Object resultado;				
		resultado = joinPoint.proceed();		
		return resultado;
	}


}
