package ro.pub.cs.systems.eim.colocviu1_13;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;

public class ProcessingThread extends Thread {

    private Context context = null;
    private boolean isRunning = true;
    private String s;


    public ProcessingThread(Context context, String s) {
        this.context = context;
        this.s = s;
    }

    @Override
    public void run() {
        Log.d("service", "Thread has started!");
        while (isRunning) {
            sleep();
            sendMessage();
            stopThread();
        }
        Log.d("service", "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction("10");
        intent.putExtra("service",
                new Date(System.currentTimeMillis()) + " " + s);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}