package ro.pub.cs.systems.eim.colocviu1_13;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class Colocviu1_13Service extends Service {

    private ProcessingThread processingThread = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String s = intent.getStringExtra("directions");
        processingThread = new ProcessingThread(this, s);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        processingThread.stopThread();
    }

}
