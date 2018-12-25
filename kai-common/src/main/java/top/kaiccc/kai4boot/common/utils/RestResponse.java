package top.kaiccc.kai4boot.common.utils;


import top.kaiccc.kai4boot.common.enums.CodeMessage;

public class RestResponse<T> {
    private int code;
    private String msg;
    private T body;

    public static RestResponse<String> success() {
        return success("");
    }

    public static <T> RestResponse<T> success(T body) {
        return new RestResponse<>(CodeMessage.SUCCESS.getCode(), CodeMessage.SUCCESS.getMessage(), body);
    }

    public static RestResponse<String> failed(CodeMessage codeMessage) {
        return failed(codeMessage.getCode(), codeMessage.getMessage());
    }

    public static RestResponse<String> failed(int code,String msg) {
        RestResponse<String> resp = new RestResponse<>();
        resp.setCode(code);
        resp.setMsg(msg);
        return resp;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public RestResponse(int code, String msg, T body) {
        this.code = code;
        this.msg = msg;
        this.body = body;
    }

    public RestResponse() {
    }
}
