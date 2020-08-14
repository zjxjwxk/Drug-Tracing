package cn.edu.zju.drugtracing.common;

/**
 * 响应码枚举类
 * @author Xinkang Wu
 * @date 2020/8/12 9:35 下午
 */
public enum ResponseCode {

    // 响应码
    SUCCESS(0, "SUCCESS"),
    ERROR(1, "ERROR"),
    ILLEGAL_ARGUMENT(2, "ILLEGAL_ARGUMENT"),
    NEED_LOGIN(10, "NEED_LOGIN");

    private final int code;
    private final String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
