package com.panshare.client.config;

import com.github.pagehelper.util.StringUtil;
import com.panshare.client.common.Code;
import com.panshare.client.common.PanShareException;
import com.panshare.client.common.R;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler({PanShareException.class})
    public R handler(PanShareException e) {
        log.error(e.getMessage());
        return R.error().message(e.getMessage());
    }

    @ExceptionHandler({Exception.class})
    public R handler(Exception e) {
        e.printStackTrace();
        return R.fatal();
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public R validException(MethodArgumentNotValidException e) {
        String message = (String) e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        log.error(e.getMessage());
        return R.error().code(Code.ERROR_Valid).message(message);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public R validException(ConstraintViolationException e) {
        String message = e.getLocalizedMessage().split(": ")[1];
        String message2 = StringUtil.isEmpty(message) ? Code.ERROR_Valid_MESSAGE : message;
        log.error(e.getMessage());
        return R.error().code(Code.ERROR_Valid).message(message2);
    }
}
