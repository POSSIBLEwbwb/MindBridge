package cn.cy.llm.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record RefundResult(
		@JsonPropertyDescription("退款申请Id") String refundId,
		@JsonPropertyDescription("订单号") String orderId,
		@JsonPropertyDescription("退款原因") String reason,
		@JsonPropertyDescription("退款状态") String status) {
}
