package pollub.ism.lab10;

import android.os.AsyncTask;
import android.widget.Button;
import android.widget.ProgressBar;

import java.lang.ref.WeakReference;

public class AsyncTask2 extends AsyncTask<Integer, Integer, Void> {
    WeakReference<Button> startButton, stopButton;
    WeakReference<ProgressBar> progressBar;
    int iterations, sleepTime, taskProgress;

    public AsyncTask2(ProgressBar progressBar, Button startButton, Button stopButton) {
        this.progressBar    = new WeakReference<>(progressBar);
        this.startButton    = new WeakReference<>(startButton);
        this.stopButton     = new WeakReference<>(stopButton);
    }

    @Override
    protected Void doInBackground(Integer... integers) {

        iterations  = integers[0];
        sleepTime   = integers[1];

        for(int i = 0; i < iterations; i++) {
            if (isCancelled()) break;

            try {
                Thread.sleep(sleepTime);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }

            taskProgress = 100 * (i + 1) / iterations;
            publishProgress(taskProgress);
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        progressBar.get().setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        startButton.get().setEnabled(true);
        stopButton.get().setEnabled(false);
    }

    @Override
    protected void onCancelled() {
        startButton.get().setEnabled(true);
        stopButton.get().setEnabled(false);
    }
}