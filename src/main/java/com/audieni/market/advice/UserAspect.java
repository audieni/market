package com.audieni.market.advice;

import com.audieni.market.annotations.Authorized;
import com.audieni.market.dtos.UserDto;
import com.audieni.market.exceptions.NotLoggedInException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserAspect {
    private final HttpServletRequest request;

    public UserAspect(HttpServletRequest request) {
        this.request = request;
    }

    @Around("@annotation(authorized)")
    public Object authenticate(ProceedingJoinPoint pjp, Authorized authorized) throws Throwable {
        HttpSession session = this.request.getSession();
        UserDto user = (UserDto) session.getAttribute("user");

        if (user == null) {
            throw new NotLoggedInException("You are not logged in.");
        }

        return pjp.proceed(pjp.getArgs());
    }
}
