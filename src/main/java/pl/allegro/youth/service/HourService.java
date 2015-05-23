package pl.allegro.youth.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.allegro.youth.model.Hour;
import pl.allegro.youth.repository.HourRepository;

import java.time.LocalDateTime;

@Service
public class HourService {

    @Autowired
    private HourRepository hourRepository;

    public Hour getCurrentHour(){
       // Integer time = LocalDateTime.now().getHour() * 100 + LocalDateTime.now().getMinute();
        Integer time = 906;
        return  hourRepository.findByStartBeforeAndEndAfter(time, time);
    }

    public Hour getNextHour(){
        return hourRepository.findOne(getCurrentHour().getNumber()+1);
    }
}
