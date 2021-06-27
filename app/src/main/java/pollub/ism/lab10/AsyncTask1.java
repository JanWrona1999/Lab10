package pollub.ism.lab10;

import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class AsyncTask1 extends AsyncTask<Void, Void, String> {
    private WeakReference<TextView> resultView;

    public AsyncTask1(TextView textView) {
        resultView  = new WeakReference<>(textView);
    }

    @Override
    protected String doInBackground(Void... voids) {
        Random r        = new Random();
        int sleepTime   = r.nextInt(11) * 200;

        try {
            Thread.sleep(sleepTime);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        return "Zadanie zakończone po " + sleepTime + " ms";
    }

    protected void onPostExecute(String result) {
        resultView.get().setText(result);
    }
}