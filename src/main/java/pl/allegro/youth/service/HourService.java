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

    public Hour getCurrentHour() {
        Integer time = LocalDateTime.now().getHour() * 100 + LocalDateTime.now().getMinute();
        // Integer time = 906;
        Hour hour;
        Integer count = 0;
        do {
            hour = hourRepository.findByStartBeforeAndEndAfter(time, time);
            if (time % 100 >= 60) {
                time += 100;
                time -= 60;
            }
            time += 5;
            count++;
        } while (hour == null && count <= 10);
        if (hour == null) hour = hourRepository.findOne(1);
        return hour;
    }

    public Hour getNextHour() {
        Hour currentHour = getCurrentHour();
        if (currentHour != null) {
            return hourRepository.findOne(currentHour.getNumber() + 1);
        }
        return null;
    }

    public Integer countHours(Hour hour, Integer workingHours) {
        return hour.getEnd() - hour.getStart() / (workingHours - hour.getNumber());
    }
}
