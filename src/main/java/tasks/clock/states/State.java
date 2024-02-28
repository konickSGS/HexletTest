package tasks.clock.states;

import tasks.clock.AlarmClock;

public interface State {
    void clickMode();

    void longClickMode();

    void clickH();

    void clickM();

    void tick();

    boolean isAlarmOn();

    boolean isAlarmTime();

    int getMinutes();

    int getHours();

    int getAlarmMinutes();

    int getAlarmHours();

    String getCurrentMode();
}

abstract class BaseState implements State {
    protected AlarmClock context;

    public BaseState(AlarmClock alarmClock) {
        this.context = alarmClock;
    }

    @Override
    public void longClickMode() {
        context.setState(new BellState(context));
    }

    @Override
    public boolean isAlarmOn() {
        return context.isAlarmOn();
    }

    @Override
    public void tick() {
        if (context.isAlarmOn() && context.isAlarmTime()) {
            context.setState(new BellState(context));
        }
    }

    @Override
    public boolean isAlarmTime() {
        return context.isAlarmTime();
    }

    @Override
    public int getMinutes() {
        return context.getMinutes();
    }

    @Override
    public int getHours() {
        return context.getHours();
    }

    @Override
    public int getAlarmMinutes() {
        return context.getAlarmMinutes();
    }

    @Override
    public int getAlarmHours() {
        return context.getAlarmHours();
    }
}
