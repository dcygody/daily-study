package cn.zing.hj212.api.enm;

import cn.zing.hj212.api.code.CodeMean;

/**
 * HJ212-2017 数据类型
 */
public enum DataType implements CodeMean {

    REAL("2011", "实时数据", "REAL"),
    MIN("2051", "分钟数据", "MIN"),
    HOUR("2061", "小时数据", "HOUR"),
    DAY("2031", "日数据", "DAY"),
    ;

    private String code;

    private String meaning;

    private String factor;

    public String getFactor() {
        return factor;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }

    DataType(String code, String meaning, String factor) {
        this.code = code;
        this.meaning = meaning;
        this.factor = factor;
    }

    DataType(String code, String meaning) {
        this.code = code;
        this.meaning = meaning;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String mean() {
        return meaning;
    }
}
