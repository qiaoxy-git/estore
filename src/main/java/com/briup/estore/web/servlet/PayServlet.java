package com.briup.estore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.briup.estore.bean.OrderForm;
import com.briup.estore.service.impl.OrderFormServiceImpl;
import com.briup.estore.util.AlipayClientUntil;

public class PayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PayServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String orderId = request.getParameter("id");
		
		// 調用service去得到訂單
		OrderFormServiceImpl formService = new OrderFormServiceImpl();
		OrderForm order = formService.findOrderFormById(Integer.parseInt(orderId));
		// 浏览器动态产生一个付钱的二维码
		// 將項目接入支付寶 進入蚂蚁金福平台
		
		//实例化客户端
		AlipayClient alipayClient = AlipayClientUntil.getAlipayClient();
		//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeAppPayRequest req = new AlipayTradeAppPayRequest();
		//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		model.setBody("訂單数据");
		// 訂單標題必須要寫
		model.setSubject("書籍訂單");
		// 設置訂單號 必須要寫
		model.setOutTradeNo(System.currentTimeMillis() + "");
		// 設置超時時間
		model.setTimeoutExpress("30m");
		// 設置訂單金額
		model.setTotalAmount(order.getCost() + "");
		// 產品嗎
		model.setProductCode("QUICK_MSECURITY_PAY");
		// 設置參數
		req.setBizModel(model);
		// 付款結束以後需要跳轉到什麽頁面
		req.setNotifyUrl("http://localhost:8099/estore/");
		try {
		        //这里和普通的接口调用不同，使用的是sdkExecute
		        AlipayTradeAppPayResponse resp = alipayClient.pageExecute(req);
		        response.setContentType("text/html;charset=utf-8");
		        response.getWriter().println(resp.getBody());
		    } catch (AlipayApiException e) {
		        e.printStackTrace();
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
