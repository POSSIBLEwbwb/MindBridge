package cn.cy.llm.llmentor.controller;

import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/call")
public class CallController {

    @Autowired
    private DashScopeChatModel dashScopeChatModel;

    @RequestMapping("/string")
    public String callString(String message) {
        return dashScopeChatModel.call(message);
    }

    @RequestMapping("/messages")
    public String callMessages(String message) {
        SystemMessage systemMessage = new SystemMessage("你是个翻译工具，请把用户的消息翻译为英文");
        Message userMsg = new UserMessage(message);
        return dashScopeChatModel.call(systemMessage, userMsg);
    }

}
