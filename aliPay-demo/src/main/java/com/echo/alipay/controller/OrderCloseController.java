package com.echo.alipay.controller;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.echo.alipay.config.AlipayConfig;
import com.echo.alipay.model.QuickPayModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author: Created by zhangsf
 * @datetime: Created in 12:52 2020/7/31
 * @description:
 */
@Controller
public class OrderCloseController {


    @RequestMapping("closeOrder")
    public void closeOrder(QuickPayModel model, HttpServletResponse response) throws Exception {

        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradeCloseRequest alipayRequest = new AlipayTradeCloseRequest();
        //商户订单号，商户网站订单系统中唯一订单号
        //String out_trade_no = new String(request.getParameter("WIDTCout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
        //支付宝交易号
        //String trade_no = new String(request.getParameter("WIDTCtrade_no").getBytes("ISO-8859-1"),"UTF-8");
        //请二选一设置

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ model.getOrderNo() +"\"," +"\"trade_no\":\""+ model.getRelOrderNo() +"\"}");

        //请求
        String result = alipayClient.execute(alipayRequest).getBody();

        PrintWriter out = response.getWriter();
        //输出
        out.println(result);



    }



}
