package pl.allegro.youth.service;

import org.junit.Before;
import org.junit.Test;
import pl.allegro.youth.model.Average;
import pl.allegro.youth.model.Marks;

import static org.junit.Assert.*;

public class AverageMarksTest {

    private AverageMarks averageMarks;

    @Before
    public void setUp() {
        averageMarks = new AverageMarks();
    }

    @Test
    public void testAverage() throws Exception {
        Marks marks = new Marks();
        marks.setEnglish(5);
        marks.setHistory(3);
        marks.setMath(3);
        marks.setPolish(1);

        assertEquals(1, marks.getPolish());
        Integer average = averageMarks.average(marks).getAverage();
        Integer mark = 3;
        assertEquals(average, mark);
        assertTrue(true);
        assertFalse(false);
        assertNull(null);
    }
}