package com.lars.www.response;

import java.util.HashMap;
import java.util.Map;


public class BusinessException extends Exception {

    private static final long serialVersionUID = -238091758285157331L;

    private String errCode;
    private String errMsg;

    public BusinessException() {
        super();
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String _errCode) {
        this.errCode = _errCode;
        this.errMsg = getMsgByCode(_errCode);
    }

    public BusinessException(String errCode, String errMsg) {
        super(errCode + ":" + errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return this.errCode;
    }

    public String getErrMsg() {
        return this.errMsg;
    }


    // region 公共返回码
    public static final String CODE_SUCCESS = "8001";
    public static final String MSG_SUCCESS = "操作成功";
    public static final String CODE_ERROR = "4001";
    public static final String MSG_ERROR = "操作失败";
    public static final String CODE_AUTH_ERROR = "4000";
    public static final String MSG_AUTH_ERROR = "权限不足";
    public static final String CODE_IDEMPOTENT_ERROR = "4002";
    public static final String MSG_IDEMPOTENT_ERROR = "Header缺少参数: 'Idempotent'";
    public static final String CODE_UNKNOWN_ERROR = "4003";
    public static final String MSG_UNKNOWN_ERROR = "系统小哥不知发生了什么，请与我们的工程师联系";
    // endregion

    // region 数据相关
    public static final String CODE_SIGN_ERROR = "4101";
    public static final String MSG_SIGN_ERROR = "数据签名错误";
    public static final String CODE_PARAM_NULL = "4102";
    public static final String MSG_PARAM_NULL = "请求参数有误";
    public static final String CODE_SQL_ERROR = "4103";
    public static final String MSG_SQL_ERROR = "SQL异常";
    public static final String CODE_INSERT_ERROR = "4104";
    public static final String MSG_INSERT_ERROR = "添加失败";
    public static final String CODE_DELETE_ERROR = "4105";
    public static final String MSG_DELETE_ERROR = "删除失败";
    public static final String CODE_UPDATE_ERROR = "4106";
    public static final String MSG_UPDATE_ERROR = "更新失败";
    public static final String CODE_NULL_ERROR = "4107";
    public static final String MSG_NULL_ERROR = "数据不存在";
    public static final String CODE_DATE_REPEAT_ERROR = "4108";
    public static final String MSG_DATE_REPEAT_ERROR = "数据已存在或正被使用";
    public static final String CODE_DATE_TOO_LONG_ERROR = "4109";
    public static final String MSG_DATE_TOO_LONG_ERROR = "数据长度过长";
    // endregion

    // region 其他常见错误
    public static final String CODE_LOGIN_OUT_OF_TIME = "4201";
    public static final String MSG_LOGIN_OUT_OF_TIME = "登录过期";
    public static final String CODE_ERROR_REMOTE = "4202";
    public static final String MSG_ERROR_REMOTE = "远程调用失败";
    public static final String CODE_IMG_UPLOAD_ERROR = "4203";
    public static final String MSG_IMG_UPLOAD_ERROR = "图片关联失败";
    public static final String CODE_INTERFACE_EXCEPTION = "4204";
    public static final String MSG_INTERFACE_EXCEPTION = "接口调用异常";
    public static final String CODE_ERROR_DATE_FORMAT = "4205";
    public static final String MSG_ERROR_DATE_FORMAT = "日期格式错误";
    public static final String CODE_ERROR_DATE_RANGE = "4206";
    public static final String MSG_ERROR_DATE_RANGE = "超出搜索时间范围";
    // endregion


    //region 租赁业务 30

    //endregion


    //根据code获取msg
    private static Map<String, String> msgMap = new HashMap<>();

    public static String getMsgByCode(String code) {
        return msgMap.get(code);
    }

    static {
        // region 公共返回码
        msgMap.put(CODE_SUCCESS, MSG_SUCCESS);
        msgMap.put(CODE_ERROR, MSG_ERROR);
        msgMap.put(CODE_AUTH_ERROR, MSG_AUTH_ERROR);
        msgMap.put(CODE_IDEMPOTENT_ERROR, MSG_IDEMPOTENT_ERROR);
        msgMap.put(CODE_UNKNOWN_ERROR, MSG_UNKNOWN_ERROR);
        // endregion

        // region 数据相关
        msgMap.put(CODE_SIGN_ERROR, MSG_SIGN_ERROR);
        msgMap.put(CODE_PARAM_NULL, MSG_PARAM_NULL);
        msgMap.put(CODE_SQL_ERROR, MSG_SQL_ERROR);
        msgMap.put(CODE_INSERT_ERROR, MSG_INSERT_ERROR);
        msgMap.put(CODE_DELETE_ERROR, MSG_DELETE_ERROR);
        msgMap.put(CODE_UPDATE_ERROR, MSG_UPDATE_ERROR);
        msgMap.put(CODE_NULL_ERROR, MSG_NULL_ERROR);
        msgMap.put(CODE_DATE_REPEAT_ERROR, MSG_DATE_REPEAT_ERROR);
        msgMap.put(CODE_DATE_TOO_LONG_ERROR, MSG_DATE_TOO_LONG_ERROR);
        // endregion

        // region 其他常见错误
        msgMap.put(CODE_LOGIN_OUT_OF_TIME, MSG_LOGIN_OUT_OF_TIME);
        msgMap.put(CODE_ERROR_REMOTE, MSG_ERROR_REMOTE);
        msgMap.put(CODE_IMG_UPLOAD_ERROR, MSG_IMG_UPLOAD_ERROR);
        msgMap.put(CODE_INTERFACE_EXCEPTION, MSG_INTERFACE_EXCEPTION);
        msgMap.put(CODE_ERROR_DATE_FORMAT, MSG_ERROR_DATE_FORMAT);
        msgMap.put(CODE_ERROR_DATE_RANGE, MSG_ERROR_DATE_RANGE);
        // endregion

        //region 业务 30

        //endregion
    }


    //根据code获取msg名字，国际化对应字段名
    private static Map<String, String> msgNameMap = new HashMap<>();

    public static String getMsgNameByCode(String code) {
        return msgNameMap.get(code);
    }

    static {
        //region 基础
        msgNameMap.put(CODE_SUCCESS, "MSG_SUCCESS");
        msgNameMap.put(CODE_ERROR, "MSG_ERROR");
        msgNameMap.put(CODE_AUTH_ERROR, "MSG_AUTH_ERROR");
        msgNameMap.put(CODE_UNKNOWN_ERROR, "MSG_UNKNOWN_ERROR");
        //endregion
    }

    @Override
    public String toString() {
        return "错误码：" + errCode + "，错误信息：" + errMsg;
    }
}
