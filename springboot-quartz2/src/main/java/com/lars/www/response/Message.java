package com.lars.www.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * 数据返回对象
 * 因国际化需求，一些方法和成员已过时，不能在国际化中使用
 * Version: 1.0.0
 * Update: 2017/5/24
 * Created by Leaves on 2017/5/24.
 */
public class Message {
    Logger logger = LoggerFactory.getLogger(Message.class);

    //国际化
//    private static MessageSource messageSource = ColumbiaApplication.getBean(MessageSource.class);

    private String code;
    private String msg;
    private Object data;

    public static final String CODE_SUCCESS = "8001";
    public static final String MSG_SUCCESS = "操作成功";

    public static final String CODE_ERROR = "4001";
    public static final String MSG_ERROR = "操作失败";

    @Deprecated
    public static final Message SUCCESS = new Message(CODE_SUCCESS, MSG_SUCCESS, null);
    @Deprecated
    public static final Message ERROR = new Message(CODE_ERROR, MSG_ERROR, null);

    public static Map<String, String> map = new HashMap<>();

    static {
        map.put(CODE_SUCCESS, MSG_SUCCESS);
        map.put(CODE_ERROR, MSG_ERROR);
    }

    @Deprecated
    private String getMsgByCode(String code) {
        return map.get(code);
    }

    public static Message getSuccess() {
        return new Message(CODE_SUCCESS, MSG_SUCCESS, null);
    }

    public static Message getError() {
        return new Message(CODE_ERROR, MSG_ERROR, null);
    }

    public Message() {
    }

    public Message(String code) {
        this.code = code;
        this.msg = BusinessException.getMsgByCode(code);
        this.data = null;
    }

    public Message(String code, Object data) {
        this.code = code;
        this.msg = BusinessException.getMsgByCode(code);
        this.data = data;
    }

    public Message(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
