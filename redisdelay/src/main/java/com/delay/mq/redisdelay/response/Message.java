package com.delay.mq.redisdelay.response;


import com.delay.mq.redisdelay.RedisdelayApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据返回对象
 *
 * @author Leaves
 * @version 1.0.0
 * @date 2018/8/6
 */
public class Message {

    private static final Logger logger = LoggerFactory.getLogger(Message.class);

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
        return new Message(CODE_SUCCESS,MSG_SUCCESS, null);
    }

    public static Message getError() {
        return new Message(CODE_ERROR, MSG_ERROR, null);

    }

    public Message() {
    }

    public Message(String code) {
        this.code = code;
//        this.msg = messageSource.getMessage(BusinessException.getMsgNameByCode(code), null, BusinessException.getMsgByCode(code), LocaleContextHolder.getLocale());
        this.data = null;
    }

    public Message(String code, Object data) {
        this.code = code;
//        this.msg = messageSource.getMessage(BusinessException.getMsgNameByCode(code), null, BusinessException.getMsgByCode(code), LocaleContextHolder.getLocale());
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
