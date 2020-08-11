package com.example.linebot.Been;

public class ScheduleBeen {
    private String week;
    private String starttime;
    private String subject;

    public ScheduleBeen(String week, String starttime, String subject) {
        this.week = week;
        this.starttime = starttime;
        this.subject = subject;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
