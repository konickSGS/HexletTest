package tasks.clock;

import tasks.clock.states.ClockState;
import tasks.clock.states.State;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AlarmClock implements State {
    private Map<String, Integer> mins = new HashMap<>();
    private Map<String, Integer> hours = new HashMap<>();
    private State state;
    public AlarmClock() {
        state = new ClockState(this);
        mins.put("clockTime", 0);
        mins.put("alarmTime", 0);

        hours.put("clockTime", 12);
        hours.put("alarmTime", 6);
    }

    private boolean isAlarmOnBool = false;

    public void plusHours(String state) {
        int oldHours = hours.get(state);
        int newHours = (oldHours + 1) % 24;
        hours.put(state, newHours);
    }

    public void plusMinutes(String state) {
        int oldMinutes = mins.get(state);
        int newMinutes = (oldMinutes + 1) % 60;
        mins.put(state, newMinutes);
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public void clickMode() {
        state.clickMode();
    }

    @Override
    public void longClickMode() {
        isAlarmOnBool = !isAlarmOnBool;
    }

    @Override
    public void clickH() {
        state.clickH();
    }

    @Override
    public void clickM() {
        state.clickM();
    }

    @Override
    public void tick() {
        plusMinutes("clockTime");
        if (getMinutes() == 0) {
            plusHours("clockTime");
        }
        state.tick();
    }

    @Override
    public boolean isAlarmOn() {
        return isAlarmOnBool;
    }

    @Override
    public boolean isAlarmTime() {
        return Objects.equals(hours.get("alarmTime"), hours.get("clockTime")) &&
                Objects.equals(mins.get("alarmTime"), mins.get("clockTime"));
    }

    @Override
    public int getMinutes() {
        return mins.get("clockTime");
    }

    @Override
    public int getHours() {
        return hours.get("clockTime");
    }

    @Override
    public int getAlarmMinutes() {
        return mins.get("alarmTime");
    }

    @Override
    public int getAlarmHours() {
        return hours.get("alarmTime");
    }

    @Override
    public String getCurrentMode() {
        return state.getCurrentMode();
    }
}
