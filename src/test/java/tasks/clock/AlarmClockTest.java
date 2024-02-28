package tasks.clock;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AlarmClockTest {

    @Test
    void testDefaultState() {
        AlarmClock clock = new AlarmClock();

        Assertions.assertEquals(clock.getMinutes(), 0);
        Assertions.assertEquals(clock.getHours(), 12);
        Assertions.assertEquals(clock.getAlarmHours(), 6);
        Assertions.assertEquals(clock.getAlarmMinutes(), 0);
    }

    @Test
    void testChangeStateWhenClickMode() {
        AlarmClock clock = new AlarmClock();

        Assertions.assertFalse(clock.isAlarmOn());
        Assertions.assertEquals(clock.getCurrentMode(), "clock");

        clock.clickMode();
        clock.tick();
        Assertions.assertFalse(clock.isAlarmOn());
        Assertions.assertEquals(clock.getCurrentMode(), "alarm");

        clock.clickMode();
        clock.tick();
        Assertions.assertFalse(clock.isAlarmOn());
        Assertions.assertEquals(clock.getCurrentMode(), "clock");

        clock.longClickMode();
        clock.tick();
        Assertions.assertTrue(clock.isAlarmOn());
        Assertions.assertEquals(clock.getCurrentMode(), "clock");

        clock.clickMode();
        clock.tick();
        Assertions.assertTrue(clock.isAlarmOn());
        Assertions.assertEquals(clock.getCurrentMode(), "alarm");

        clock.clickMode();
        clock.tick();
        Assertions.assertTrue(clock.isAlarmOn());
        Assertions.assertEquals(clock.getCurrentMode(), "clock");

        clock.longClickMode();
        Assertions.assertFalse(clock.isAlarmOn());
        Assertions.assertEquals(clock.getCurrentMode(), "clock");
    }

    @Test
    void testChangingHoursAndMinutes() {
        AlarmClock clock = new AlarmClock();

        clock.clickH();
        Assertions.assertEquals(clock.getMinutes(), 0);
        Assertions.assertEquals(clock.getHours(), 13);
        Assertions.assertEquals(clock.getAlarmMinutes(), 0);
        Assertions.assertEquals(clock.getAlarmHours(), 6);

        clock.clickM();
        Assertions.assertEquals(clock.getMinutes(), 1);
        Assertions.assertEquals(clock.getHours(), 13);
        Assertions.assertEquals(clock.getAlarmMinutes(), 0);
        Assertions.assertEquals(clock.getAlarmHours(), 6);

        clock.clickMode();

        clock.clickH();
        Assertions.assertEquals(clock.getMinutes(), 1);
        Assertions.assertEquals(clock.getHours(), 13);
        Assertions.assertEquals(clock.getAlarmMinutes(), 0);
        Assertions.assertEquals(clock.getAlarmHours(), 7);

        clock.clickM();
        Assertions.assertEquals(clock.getMinutes(), 1);
        Assertions.assertEquals(clock.getHours(), 13);
        Assertions.assertEquals(clock.getAlarmMinutes(), 1);
        Assertions.assertEquals(clock.getAlarmHours(), 7);

        for (int i = 0; i < 60; i++) {
            clock.clickM();
        }

        Assertions.assertEquals(clock.getAlarmMinutes(), 1);
        Assertions.assertEquals(clock.getAlarmHours(), 7);

        for (int i = 0; i < 17; i++) {
            clock.clickH();
        }

        Assertions.assertEquals(clock.getAlarmHours(), 0);
    }

    @Test
    void testNoBellingIfAlarmOff() {
        AlarmClock clock = new AlarmClock();

        for (int i = 0; i < 18 * 60; i++) {
            clock.tick();
        }

        Assertions.assertTrue(clock.isAlarmTime());
        Assertions.assertEquals(clock.getCurrentMode(), "clock");
        Assertions.assertEquals(clock.getMinutes(), 0);
        Assertions.assertEquals(clock.getHours(), 6);
        clock.tick();
        Assertions.assertEquals(clock.getCurrentMode(), "clock");
    }

    @Test
    void testStartingBellIfAlarmOn() {
        AlarmClock clock = new AlarmClock();
        clock.longClickMode();

        for (int i = 0; i < 18 * 60; i++) {
            clock.tick();
        }

        Assertions.assertTrue(clock.isAlarmTime());
        Assertions.assertEquals(clock.getCurrentMode(), "bell");
        Assertions.assertEquals(clock.getMinutes(), 0);
        Assertions.assertEquals(clock.getHours(), 6);
        clock.tick();
        Assertions.assertEquals(clock.getCurrentMode(), "clock");
    }

    @Test
    void testStartingBellIfAlarmOn1() {
        AlarmClock clock = new AlarmClock();
        clock.longClickMode();

        for (int i = 0; i < 18 * 60; i++) {
            clock.tick();
        }

        Assertions.assertTrue(clock.isAlarmTime());
        Assertions.assertEquals(clock.getCurrentMode(), "bell");
        clock.clickMode();
        Assertions.assertEquals(clock.getCurrentMode(), "clock");
    }

    @Test
    void testStartingBellIfAlarmOn3() {
        AlarmClock clock = new AlarmClock();
        clock.longClickMode();
        clock.clickMode();
        Assertions.assertEquals(clock.getCurrentMode(), "alarm");

        for (int i = 0; i < 18 * 60; i++) {
            clock.tick();
        }

        Assertions.assertTrue(clock.isAlarmTime());
        Assertions.assertTrue(clock.isAlarmOn());

        Assertions.assertEquals(clock.getCurrentMode(), "bell");
        clock.clickMode();
        Assertions.assertEquals(clock.getCurrentMode(), "clock");
    }

    @Test
    void testNoBellForAlarmModeIfAlarmOff() {
        AlarmClock clock = new AlarmClock();
        clock.clickMode();
        Assertions.assertEquals(clock.getCurrentMode(), "alarm");

        for (int i = 0; i < 18 * 60; i++) {
            clock.tick();
        }

        Assertions.assertTrue(clock.isAlarmTime());
        Assertions.assertFalse(clock.isAlarmOn());
        Assertions.assertEquals(clock.getCurrentMode(), "alarm");

        clock.clickMode();
        clock.tick();
        Assertions.assertEquals(clock.getCurrentMode(), "clock");
    }

    @Test
    void testIncrementMinutesAfterAlarm() {
        AlarmClock clock = new AlarmClock();
        clock.longClickMode();

        for (int i = 0; i < 18 * 60; i++) {
            clock.tick();
        }

        Assertions.assertTrue(clock.isAlarmTime());
        Assertions.assertEquals(clock.getCurrentMode(), "bell");

        clock.tick();

        Assertions.assertEquals(clock.getCurrentMode(), "clock");
        Assertions.assertEquals(clock.getMinutes(), 1);
    }
}
