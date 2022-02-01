package main.java.hello.hellospring.aop;
/*
AOP:어디에 적용할지 지정하면 가짜memberService(프록시)를 만들어서 끝나면
joinPoint.proceed()가 끝나면 진짜 불러와 */
@Aspect
@Component
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))")//타겟팅
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();//Object result=joinPOint.proceed();+return result;의 인라인
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString()+ " " + timeMs + "ms");
        }
    }
}
