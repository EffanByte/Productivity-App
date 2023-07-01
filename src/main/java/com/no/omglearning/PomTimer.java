package com.no.omglearning;
public class PomTimer extends java.lang.Thread {
    private static int minute;

    PomTimer(){

    }
    private static int second;
    @Override
    public void run() {
        for (; minute >= 0; minute--){
            for (second = 59; second > 0; second--){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }
}
