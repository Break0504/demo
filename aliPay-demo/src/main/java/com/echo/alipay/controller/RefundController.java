package com.echo.alipay.controller;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.echo.alipay.config.AlipayConfig;
import com.echo.alipay.model.QuickPayModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: Created by zhangsf
 * @datetime: Created in 12:51 2020/7/31
 * @description:
 */
@Controller
public class RefundController {


    @RequestMapping("refund")
    public void refund(QuickPayModel model, HttpServletResponse response) throws Exception{

        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();

        //商户订单号，商户网站订单系统中唯一订单号
        //String out_trade_no = new String(request.getParameter("WIDTRout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
        //支付宝交易号
        //String trade_no = new String(request.getParameter("WIDTRtrade_no").getBytes("ISO-8859-1"),"UTF-8");
        //请二选一设置
        //需要退款的金额，该金额不能大于订单金额，必填
        //String refund_amount = new String(request.getParameter("WIDTRrefund_amount").getBytes("ISO-8859-1"),"UTF-8");
        //退款的原因说明
        //String refund_reason = new String(request.getParameter("WIDTRrefund_reason").getBytes("ISO-8859-1"),"UTF-8");
        //标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
        //String out_request_no = new String(request.getParameter("WIDTRout_request_no").getBytes("ISO-8859-1"),"UTF-8");

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ model.getOrderNo() +"\","
                + "\"trade_no\":\""+ model.getRelOrderNo() +"\","
                + "\"refund_amount\":\""+ model.getRefundAmt() +"\","
                + "\"refund_reason\":\""+ model.getRefundReason() +"\","
                + "\"out_request_no\":\""+ model.getRefundOrderNo() +"\"}");

        //请求
        String result = alipayClient.execute(alipayRequest).getBody();

        response.setContentType("text/html;charset=" + "UTF-8");
        response.getWriter().write(result);// 直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();


    }




    @RequestMapping("qryRefund")
    public void qryRefund(QuickPayModel model, HttpServletResponse response) throws Exception {

        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradeFastpayRefundQueryRequest alipayRequest = new AlipayTradeFastpayRefundQueryRequest();

        /*//商户订单号，商户网站订单系统中唯一订单号
        String out_trade_no = new String(request.getParameter("WIDRQout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
        //支付宝交易号
        String trade_no = new String(request.getParameter("WIDRQtrade_no").getBytes("ISO-8859-1"),"UTF-8");
        //请二选一设置
        //请求退款接口时，传入的退款请求号，如果在退款请求时未传入，则该值为创建交易时的外部交易号，必填
        String out_request_no = new String(request.getParameter("WIDRQout_request_no").getBytes("ISO-8859-1"),"UTF-8");*/

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ model.getOrderNo() +"\","
                +"\"trade_no\":\""+ model.getRelOrderNo() +"\","
                +"\"out_request_no\":\""+ model.getRefundOrderNo() +"\"}");

        //请求
        String result = alipayClient.execute(alipayRequest).getBody();

        response.setContentType("text/html;charset=" + "UTF-8");
        response.getWriter().write(result);// 直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();

    }




}
