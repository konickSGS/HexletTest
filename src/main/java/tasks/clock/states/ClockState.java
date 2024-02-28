package tasks.clock.states;

import tasks.clock.AlarmClock;

public class ClockState extends BaseState {
    public ClockState(AlarmClock alarmClock) {
        super(alarmClock);
    }

    @Override
    public void clickH() {
        context.plusHours("clockTime");
    }

    @Override
    public void clickM() {
        context.plusMinutes("clockTime");
    }

    @Override
    public void clickMode() {
        context.setState(new AlarmState(context));
    }


    @Override
    public String getCurrentMode() {
        return "clock";
    }
}
