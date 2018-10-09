package top.kaiccc.kai4boot.common.utils;


import top.kaiccc.kai4boot.common.enums.CodeMessage;

public class RestResponse<T> {
    private int code;
    private String message;
    private T content;

    public static RestResponse<String> success() {
        return success("");
    }

    public static <T> RestResponse<T> success(T content) {
        return new RestResponse<>(CodeMessage.SUCCESS.getCode(), CodeMessage.SUCCESS.getMessage(), content);
    }

    public static RestResponse<String> failed(CodeMessage codeMessage) {
        return failed(codeMessage.getCode(), codeMessage.getMessage());
    }

    public static RestResponse<String> failed(int code,String message) {
        RestResponse<String> resp = new RestResponse<>();
        resp.setCode(code);
        resp.setMessage(message);
        return resp;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public RestResponse(int code, String message, T content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }

    public RestResponse() {
    }
}
