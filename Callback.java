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
    public List<PreExam> handleMessage(MessageEvent<TextMessageContent> event) throws SQLException {
        TextMessageContent tmc = event.getMessage();
        String text = tmc.getText();
        switch (text) {
            case "日程を教えて":
                return replyNittei(10);
            default:
                return reply(text);
        }
    }


    //月～金の授業日程を返す
    private List<PreExam> replyNittei(int lessThan) throws SQLException{
        List<PreExam> list = new ArrayList<PreExam>();
        String sql = "SELECT * FROM GAKUSEI_SCHEDULE";
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASS);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, lessThan);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                String col1 = result.getString("WEEK");
                String col2 = result.getString("STARTTIME");
                String col3 = result.getString("SUBJECT");
                PreExam preExam = new PreExam(col1,col2,col3);
                list.add(preExam);
            }
        }
        return list;
    }


    // 返答メッセージを作る
    private TextMessage reply(String text) {
        return new TextMessage(text);
    }

    // 画像メッセージを作る
//    private ImageMessage replyImage(String url) {
//        // 本来は、第一引数が実際の画像URL、第二画像がサムネイルのurl
//        return new ImageMessage(url, url);
//    }





}