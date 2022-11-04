package com.rzandjavagit.core.utils;


import android.os.Handler;
import android.os.Message;

public class CountDownTimerThreadHandler {
    private OnEventListener eventListener;
    public static final int TIMER_TICK_TIME = 1000;
    private int intervalSeconds = 31;
    private int remainingSeconds = 0;
    private int tickCounter = -1;

    private CountDownTimerThreadHandler() {
    }

    public CountDownTimerThreadHandler(OnEventListener argOnEventListener) {
        eventListener = argOnEventListener;
    }

    public CountDownTimerThreadHandler setIntervalSeconds(int argIntervalSeconds) {
        intervalSeconds = argIntervalSeconds;
        return this;
    }

    public void onStart() {
        //thread.start();
        handler.post(thread);
    }

    public void onRestart() {
        remainingSeconds = 0;
        tickCounter = -1;
    }

    public void onDestroy() {
        if (thread != null && handler != null) {
            handler.removeCallbacks(thread);
        }
    }

    private Thread thread = new Thread(new Runnable() {
        public void run() {
            //handler.sendMessage(null);
            Message msg = new Message();
            msg.obj = "Ali send message";
            handler.sendMessage(msg);
        }
    });
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message argMessage) {
            /*if (tickCounter < 0) {
                if (eventListener != null) {
                    eventListener.onStart(intervalSeconds);
                }
            }*/
            tickCounter++;
            remainingSeconds = intervalSeconds - tickCounter;
            if (tickCounter > 0) {
                /*String logTag = "DEBUG_LOG_COUNT_DOWN_TIMER: ";
                System.out.println(logTag + "timer called " + tickCounter + " - " + remainingSeconds);*/
                if (eventListener != null) {
                    eventListener.onTick(tickCounter, remainingSeconds);
                }
                if (remainingSeconds <= 0) {
                    //onDestroy();
                    if (eventListener != null) {
                        eventListener.onFinish(intervalSeconds);
                    }
                }
            } else {
                if (eventListener != null) {
                    eventListener.onStart(intervalSeconds);
                }
            }
            if (remainingSeconds > 0) {
                this.postDelayed(thread, TIMER_TICK_TIME);
            }
        }
    };

    public interface OnEventListener {
        public void onStart(int argTotalTimeInSecond);

        public void onTick(int argTickTimes, int argRemainingSeconds);

        public void onFinish(int argTotalTimeInSecond);
    }
}
