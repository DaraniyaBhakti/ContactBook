package com.tatvasoft.tatvasoftassignment11.BackgroundProcess;

import android.os.Handler;
import android.os.Looper;

import com.tatvasoft.tatvasoftassignment11.Model.ContactsModel;

import java.util.ArrayList;

public class BackgroundTask {

    protected Handler localHandler;
    protected Thread localThread;

    public void execute(){
        this.localHandler = new Handler(Looper.getMainLooper());
        this.onPreExecute();

        this.localThread =new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<ContactsModel> arrayList = BackgroundTask.this.doInBackground();
                BackgroundTask.this.localHandler.post(() -> BackgroundTask.this.onPostExecute(arrayList));
            }
        });
        this.localThread.start();
    }

    protected void onPostExecute(ArrayList<ContactsModel> arrayList) {
    }

    protected ArrayList<ContactsModel> doInBackground() {
        return null;
    }

    protected void onPreExecute() {
    }

    public void cancel(){
        if(this.localThread.isAlive()){
            this.localThread.interrupt();
        }
    }

}
