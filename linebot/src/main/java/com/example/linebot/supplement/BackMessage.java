package com.example.linebot.supplement;

import com.example.linebot.Service.ScheduleService;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineBotMessages;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.slf4j.LoggerFactory;

import java.util.logging.Logger;

@LineMessageHandler
public class BackMessage {

    private static final Logger log = (Logger) LoggerFactory.getLogger(BackMessage.class);

    @EventMapping
    public Message weekMessage(MessageEvent<TextMessageContent> event){
        TextMessageContent tmc = event.getMessage();
        String week = tmc.getText();
        ScheduleService scheduleService = new ScheduleService();
        return (Message) scheduleService.getSchedule(week);
    }

}
