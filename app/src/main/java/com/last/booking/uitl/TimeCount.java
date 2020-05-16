package com.last.booking.uitl;

import android.os.CountDownTimer;

public class TimeCount extends CountDownTimer {

    private ITimer iTimer;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public TimeCount(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        iTimer.onTick(millisUntilFinished);
    }

    @Override
    public void onFinish() {
        iTimer.onFinish();
    }

    public void setiTimer(ITimer iTimer) {
        this.iTimer = iTimer;
    }
}
