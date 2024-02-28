package tasks.clock.states;

import tasks.clock.AlarmClock;

public class AlarmState extends BaseState {
    public AlarmState(AlarmClock alarmClock) {
        super(alarmClock);
    }

    @Override
    public void clickMode() {
        context.setState(new ClockState(context));
    }

    @Override
    public void clickH() {
        context.plusHours("alarmTime");
    }

    @Override
    public void clickM() {
        context.plusMinutes("alarmTime");
    }

    @Override
    public String getCurrentMode() {
        return "alarm";
    }
}
