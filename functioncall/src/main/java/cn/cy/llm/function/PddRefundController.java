package cn.cy.llm.function;

import cn.cy.llm.model.ChatStatus;
import cn.cy.llm.model.OrderChat;
import cn.cy.llm.tools.OrderTools;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.UUID;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

@RestController
@RequestMapping("/ai/refund")
public class PddRefundController {

	private final ChatClient chatClient;

	private final OrderTools orderTools;

	public PddRefundController(ChatClient chatClient, OrderTools orderTools) {
		this.chatClient = chatClient;
		this.orderTools = orderTools;
	}

	@GetMapping("/newChat")
	public OrderChat newChat(String userId, String orderId, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		String chatId = UUID.randomUUID().toString();

		return chatClient
				.prompt()
				.user(String.format("我要咨询订单相关的售后问题。我的用户id是%s，我的订单号是%s，本地对话id是%s，当前状态是%s。请只返回结构化对话信息。",
						userId, orderId, chatId, ChatStatus.CHAT_START.name()))
				.advisors(spec -> spec.param(CONVERSATION_ID, chatId))
				.call()
				.entity(OrderChat.class);
	}

	@GetMapping(value = "/ask", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<String> ask(String question, String chatId, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");

		return chatClient
				.prompt()
				.user(question)
				.tools(orderTools)
				.advisors(spec -> spec.param(CONVERSATION_ID, chatId))
				.stream()
				.content();
	}
}
