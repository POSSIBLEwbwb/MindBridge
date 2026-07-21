package cn.cy.llm.tools;

import cn.cy.llm.model.RefundResult;
import cn.cy.llm.service.OrderManageService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

@Component
public class OrderTools {

	private final OrderManageService orderManageService;

	public OrderTools(OrderManageService orderManageService) {
		this.orderManageService = orderManageService;
	}

	@Tool(name = "get_order", description = "根据订单号查询订单基本信息")
	public String getOrder(@ToolParam(description = "订单编号") String orderId) {
		return orderManageService.getOrderById(orderId);
	}

	@Tool(name = "apply_refund", description = "当用户确认商品存在严重质量问题后，为该订单发起退款申请")
	public RefundResult refund(
			@ToolParam(description = "订单编号") String orderId,
			@ToolParam(description = "商品名称") String productName,
			@ToolParam(description = "退款原因，必须是明确的商品质量问题") String reason) {
		System.out.println("已为商品：" + productName + "，订单号：" + orderId + "申请退款，退款原因：" + reason);
		return orderManageService.refund(orderId, reason);
	}
}
