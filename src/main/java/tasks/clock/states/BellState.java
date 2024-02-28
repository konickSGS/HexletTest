package tasks.clock.states;

import tasks.clock.AlarmClock;

public class BellState extends BaseState {
    public BellState(AlarmClock alarmClock) {
        super(alarmClock);
    }

    @Override
    public void clickMode() {
        context.setState(new ClockState(context));
    }

    @Override
    public void clickH() {
    }

    @Override
    public void clickM() {
    }

    @Override
    public String getCurrentMode() {
        return "bell";
    }

    @Override
    public void tick() {
        context.setState(new ClockState(context));
    }
}
