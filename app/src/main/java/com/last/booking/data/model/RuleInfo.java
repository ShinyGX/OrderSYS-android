package com.last.booking.data.model;

public class RuleInfo {

    private Integer com_rule_id;
    private String  com_rule_name;
    private String com_rule_text;

    public RuleInfo() {
    }

    public RuleInfo(Integer com_rule_id, String com_rule_name, String com_rule_text) {
        this.com_rule_id = com_rule_id;
        this.com_rule_name = com_rule_name;
        this.com_rule_text = com_rule_text;
    }

    public Integer getCom_rule_id() {
        return com_rule_id;
    }

    public void setCom_rule_id(Integer com_rule_id) {
        this.com_rule_id = com_rule_id;
    }

    public String getCom_rule_name() {
        return com_rule_name;
    }

    public void setCom_rule_name(String com_rule_name) {
        this.com_rule_name = com_rule_name;
    }

    public String getCom_rule_text() {
        return com_rule_text;
    }

    public void setCom_rule_text(String com_rule_text) {
        this.com_rule_text = com_rule_text;
    }
}
