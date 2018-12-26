package top.kaiccc.kaiboot.common.enums;


public enum CodeMessage {
    SYS_INDEX(-1, "欢迎访问系统首页"),
    SUCCESS(0, "操作成功"),
    //Error code for Http
    HTTP_NOT_FOUND(404, "访问资源不存在"),
    HTTP_UNAUTHORIZED(401, "认证失败"),
    HTTP_INTERNAL_SERVER_ERROR(500, "服务器内部错误，请联系管理员"),
    HTTP_READ_RESP_ERROR(501, "HTTP读取response失败"),
    HTTP_BAD_REQUEST(400, "请求参数错误"),


    PARAM_VALIDATOR_ERROR(801, "参数校验失败"),
    BIZ_UNKNOWN_ERROR(600, "非预期错误");

    private final int code;
    private final String message;

    private CodeMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static CodeMessage fromCode(int code) {
        for(CodeMessage e : CodeMessage.values()){
            if(e.getCode() == code){
                return e;
            }
        }
        return CodeMessage.BIZ_UNKNOWN_ERROR;
    }
}
