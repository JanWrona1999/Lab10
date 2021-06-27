package pollub.ism.lab10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import pollub.ism.lab10.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding layoutBinding   = null;
    private AsyncTask2 task2Hook                = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layoutBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(layoutBinding.getRoot());

        layoutBinding.task2Progress.setProgress(0);
        layoutBinding.task2Progress.setMax(100);

        layoutBinding.task2Start.setEnabled(true);
        layoutBinding.task2Stop.setEnabled(false);
    }


    public void startTask11(View view) {
        new AsyncTask1(layoutBinding.task11Result).execute();

        layoutBinding.task11Result.setText(R.string.task_working);
    }


    public void startTask12(View view) {
        new AsyncTask1(layoutBinding.task12Result).execute();

        layoutBinding.task12Result.setText(R.string.task_working);
    }

    public void startTask2(View view) {
        layoutBinding.task2Start.setEnabled(false);

        int iterations  = Integer.valueOf(layoutBinding.task2Iterations.getText().toString());
        int delay       = Integer.valueOf(layoutBinding.task2Delay.getText().toString());

        task2Hook       = new AsyncTask2(layoutBinding.task2Progress, layoutBinding.task2Start, layoutBinding.task2Stop);
        task2Hook.execute(iterations, delay);

        layoutBinding.task2Stop.setEnabled(true);
    }

    public void stopTask2(View view) {
        task2Hook.cancel(true);
    }
}