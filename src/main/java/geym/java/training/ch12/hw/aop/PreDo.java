package geym.java.training.ch12.hw.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;


@Component
public class PreDo{

	public void toPre(){
		System.out.println("===============aop:config的日志处理================");
	}

}
