package top.kaiccc.kai4boot.common.exception;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import top.kaiccc.kai4boot.common.enums.CodeMessage;
import top.kaiccc.kai4boot.common.utils.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年10月27日 下午10:16:19
 */
@RestControllerAdvice
public class BaseExceptionHandler {
    private static final Log log = LogFactory.get();

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e){
        log.error(e);
        return new ResponseEntity<RestResponse>(RestResponse.failed(CodeMessage.HTTP_INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
