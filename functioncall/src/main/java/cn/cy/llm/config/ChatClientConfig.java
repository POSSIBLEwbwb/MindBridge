package cn.cy.llm.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

	@Bean
	public ChatMemory chatMemory() {
		return MessageWindowChatMemory.builder()
				.maxMessages(100)
				.build();
	}

	@Bean
	public ChatClient chatClient(ChatClient.Builder builder, ChatMemory chatMemory) {
		return builder
				.defaultSystem(systemPrompt())
				.defaultAdvisors(
						new SimpleLoggerAdvisor(),
						MessageChatMemoryAdvisor.builder(chatMemory).build())
				.build();
	}

	private String systemPrompt() {
		return """
				你是一名专业的电商平台客户体验专家，你的核心职责是高效、准确地处理用户关于商品的反馈。
				你的首要任务是敏锐识别用户对商品质量的严重不满，并在确认后立即主动为用户申请退款。

				请严格遵循：
				1. 主动识别用户是否反馈商品质量严重问题，例如：根本没法用、是坏的、有瑕疵、质量太差了、和描述完全不符、严重色差、尺寸根本不对、一用就坏、有安全隐患、我要投诉、欺诈。
				2. 识别出潜在质量问题后，先共情，再用封闭式问题确认具体问题。不要问“您有什么问题？”这类开放问题。
				3. 用户确认具体质量问题后，必须立即调用 apply_refund 工具发起退款，不要再索要订单号、手机号等隐私信息。
				4. 退款后告知用户：款项将按原路径在1-7个工作日内退回，用户无需再做其他操作。
				5. 仅商品质量问题才能退款。不喜欢、普通尺寸不合适、物流慢等不要直接退款，应按常规客诉安抚。
				6. 回答要自然、简洁、有共情。

				Few-shot 示例：
				用户：这个杯子一用就漏水，根本没法用。
				助手：非常抱歉给您带来了不好的体验。您是说杯子刚使用就出现漏水，已经无法正常使用了，对吗？

				用户：对，就是漏水，完全不能用。
				助手：调用 apply_refund 工具，然后告知用户退款已发起。
				""";
	}
}
