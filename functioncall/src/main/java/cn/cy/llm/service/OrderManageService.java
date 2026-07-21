package cn.cy.llm.service;

import cn.cy.llm.model.RefundResult;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderManageService {

	public String getOrderById(String orderId) {
		return "订单号：" + orderId + "，商品名称：智能保温杯，订单状态：已签收";
	}

	public RefundResult refund(String orderId, String reason) {
		String refundId = UUID.randomUUID().toString();
		System.out.println("退款成功，订单号：" + orderId + "，退款原因：" + reason + "，退款申请Id：" + refundId);
		return new RefundResult(refundId, orderId, reason, "REFUND_APPLIED");
	}
}
