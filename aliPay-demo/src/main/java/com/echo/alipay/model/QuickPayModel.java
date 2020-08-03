package com.echo.alipay.model;

/**
 * @author: Created by zhangsf
 * @datetime: Created in 11:57 2020/7/31
 * @description:
 */
public class QuickPayModel {

    private String orderNo;
    private String orderSubject;
    private String orderAmt;
    private String businessDesc;
    private String outOrderNo;
    private String relOrderNo;
    private String refundAmt;
    private String refundReason;
    private String refundOrderNo;

    public QuickPayModel() {
    }

    public QuickPayModel(String orderNo, String orderSubject, String orderAmt, String businessDesc, String outOrderNo
            , String relOrderNo, String refundAmt, String refundReason, String refundOrderNo) {
        this.orderNo = orderNo;
        this.orderSubject = orderSubject;
        this.orderAmt = orderAmt;
        this.businessDesc = businessDesc;
        this.outOrderNo = outOrderNo;
        this.relOrderNo = relOrderNo;
        this.refundAmt = refundAmt;
        this.refundReason = refundReason;
        this.refundOrderNo = refundOrderNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderSubject() {
        return orderSubject;
    }

    public void setOrderSubject(String orderSubject) {
        this.orderSubject = orderSubject;
    }

    public String getOrderAmt() {
        return orderAmt;
    }

    public void setOrderAmt(String orderAmt) {
        this.orderAmt = orderAmt;
    }

    public String getBusinessDesc() {
        return businessDesc;
    }

    public void setBusinessDesc(String businessDesc) {
        this.businessDesc = businessDesc;
    }

    public String getOutOrderNo() {
        return outOrderNo;
    }

    public void setOutOrderNo(String outOrderNo) {
        this.outOrderNo = outOrderNo;
    }

    public String getRelOrderNo() {
        return relOrderNo;
    }

    public void setRelOrderNo(String relOrderNo) {
        this.relOrderNo = relOrderNo;
    }

    public String getRefundAmt() {
        return refundAmt;
    }

    public void setRefundAmt(String refundAmt) {
        this.refundAmt = refundAmt;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    public String getRefundOrderNo() {
        return refundOrderNo;
    }

    public void setRefundOrderNo(String refundOrderNo) {
        this.refundOrderNo = refundOrderNo;
    }

    @Override
    public String toString() {
        return "QuickPayModel{" +
                "orderNo='" + orderNo + '\'' +
                ", orderSubject='" + orderSubject + '\'' +
                ", orderAmt='" + orderAmt + '\'' +
                ", businessDesc='" + businessDesc + '\'' +
                ", outOrderNo='" + outOrderNo + '\'' +
                ", relOrderNo='" + relOrderNo + '\'' +
                ", refundAmt='" + refundAmt + '\'' +
                ", refundReason='" + refundReason + '\'' +
                ", refundOrderNo='" + refundOrderNo + '\'' +
                '}';
    }
}
