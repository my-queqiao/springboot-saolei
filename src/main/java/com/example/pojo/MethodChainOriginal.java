package com.example.pojo;

public class MethodChainOriginal {
    private Integer id;

    private String packageUrl;

    private String javabeanName;

    private String methodName;

    private String paramType;

    private String callTime;

    private Long callTimeLong;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPackageUrl() {
        return packageUrl;
    }

    public void setPackageUrl(String packageUrl) {
        this.packageUrl = packageUrl == null ? null : packageUrl.trim();
    }

    public String getJavabeanName() {
        return javabeanName;
    }

    public void setJavabeanName(String javabeanName) {
        this.javabeanName = javabeanName == null ? null : javabeanName.trim();
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName == null ? null : methodName.trim();
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType == null ? null : paramType.trim();
    }

    public String getCallTime() {
        return callTime;
    }

    public void setCallTime(String callTime) {
        this.callTime = callTime == null ? null : callTime.trim();
    }

    public Long getCallTimeLong() {
        return callTimeLong;
    }

    public void setCallTimeLong(Long callTimeLong) {
        this.callTimeLong = callTimeLong;
    }
}