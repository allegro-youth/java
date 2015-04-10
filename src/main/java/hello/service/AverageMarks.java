package hello.service;

import hello.model.Average;
import hello.model.Marks;
import org.springframework.stereotype.Component;

@Component
public class AverageMarks {
    private Integer sum;

    public Average average(Marks marks) {
        sum = 0;
        sum += marks.getEnglish();
        sum += marks.getPolish();
        sum += marks.getMath();
        sum += marks.getHistory();

        return new Average(sum / 4);
    }
}
