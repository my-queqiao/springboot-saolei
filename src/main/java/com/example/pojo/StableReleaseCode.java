package com.example.pojo;

public class StableReleaseCode {
    private Integer id;

    private Integer testingExampleId;

    private String packageName;

    private String javabeanName;

    private String methodName;

    private String paramType;

    private Byte callChainOrder;

    private Byte changeOrNot;

    private Byte testingOrNot;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTestingExampleId() {
        return testingExampleId;
    }

    public void setTestingExampleId(Integer testingExampleId) {
        this.testingExampleId = testingExampleId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName == null ? null : packageName.trim();
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

    public Byte getCallChainOrder() {
        return callChainOrder;
    }

    public void setCallChainOrder(Byte callChainOrder) {
        this.callChainOrder = callChainOrder;
    }

    public Byte getChangeOrNot() {
        return changeOrNot;
    }

    public void setChangeOrNot(Byte changeOrNot) {
        this.changeOrNot = changeOrNot;
    }

    public Byte getTestingOrNot() {
        return testingOrNot;
    }

    public void setTestingOrNot(Byte testingOrNot) {
        this.testingOrNot = testingOrNot;
    }
}