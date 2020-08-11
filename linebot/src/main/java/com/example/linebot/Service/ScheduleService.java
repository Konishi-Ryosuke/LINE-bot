package com.example.linebot.Service;

import com.example.linebot.Been.ScheduleBeen;
import com.example.linebot.Repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    public List<ScheduleBeen> getSchedule(String week){
        return scheduleRepository.select(week);
    }
}
