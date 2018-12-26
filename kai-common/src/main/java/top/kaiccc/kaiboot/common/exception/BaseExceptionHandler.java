package top.kaiccc.kaiboot.common.exception;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import top.kaiccc.kaiboot.common.enums.CodeMessage;
import top.kaiccc.kaiboot.common.utils.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author kaiccc
 */
@RestControllerAdvice
public class BaseExceptionHandler {
    private static final Log log = LogFactory.get();

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e){
        log.error(e);
        return new ResponseEntity<RestResponse>(RestResponse.failed(CodeMessage.HTTP_INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(RestException.class)
    public ResponseEntity handleException(RestException e){
        log.error(e.getMessage(), e);
        return new ResponseEntity<RestResponse>(RestResponse.failed(e.getCode(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
