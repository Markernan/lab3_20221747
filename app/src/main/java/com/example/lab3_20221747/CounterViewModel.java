package com.example.lab3_20221747;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CounterViewModel extends ViewModel {

    private final MutableLiveData<Integer> countLiveData = new MutableLiveData<>(0);
    private boolean isRunning = false;
    private Thread workerThread;

    public LiveData<Integer> getCount() {
        return countLiveData;
    }

    public void startCounting() {
        if (isRunning) return;
        isRunning = true;
        countLiveData.postValue(0);

        workerThread = new Thread(() -> {
            for (int i = 1; i <= 20; i++) {
                try {
                    Thread.sleep(1000);
                    countLiveData.postValue(i);
                } catch (InterruptedException e) {
                    break;
                }
            }
            isRunning = false;
        });
        workerThread.start();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (workerThread != null && workerThread.isAlive()) {
            workerThread.interrupt();
        }
    }
}