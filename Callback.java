package com.example.linebot;

import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@LineMessageHandler
public class Callback {

    private static final String URL = "jdbc:h2:file:~/h2db/nittei-database;Mode=PostgreSQL;AUTO_SERVER=TRUE;;MV_STORE=false";
    private static final String USER_NAME = "b2180950";
    private static final String USER_PASS = "b2180950";
    // フォローイベントに対応する
    @EventMapping
    public List<getCode> handleMessage(MessageEvent<TextMessageContent> event) throws SQLException {
        TextMessageContent tmc = event.getMessage();
        String text = tmc.getText();
        switch (text) {
            case "月曜日の日程を教えて":
                return replyNittei("月");
            case "火曜日の日程を教えて":
                return replyNittei("火");
            case "水曜日の日程を教えて":
                return replyNittei("水");
            case "木曜日の日程を教えて":
                return replyNittei("木");
            case "金曜日の日程を教えて":
                return replyNittei("金");
        }
        return null;
    }
    //月～金の授業日程を返す
    private List<getCode> replyNittei(String week) throws SQLException{
        List<getCode> list = new ArrayList<getCode>();
        String sql = "SELECT * FROM GAKUSEI_SCHEDULE where WEEK = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASS);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, week);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                String col1 = result.getString("WEEK");
                String col2 = result.getString("STARTTIME");
                String col3 = result.getString("SUBJECT");
                getCode preExam = new getCode(col1,col2,col3);
                list.add(preExam);
            }
        }
        return list;
    }
//    // 返答メッセージを作る
//    private TextMessage reply(String text) {
//        return new TextMessage(text);
//    }
}