package com.official.noowenz.daggerrxjavamvp.BasicRx;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.official.noowenz.daggerrxjavamvp.R;

import java.util.concurrent.Callable;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class SchedulerActivity extends AppCompatActivity {

    @BindView(R.id.scheduleLongRunningOperation)
    Button scheduleLongRunningOperation;
    @BindView(R.id.messagearea)
    TextView messagearea;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private Disposable subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler);
        ButterKnife.bind(this);

        configureLayout();
    }

    private void configureLayout() {
        scheduleLongRunningOperation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createObservable();
            }
        });
    }

    private void createObservable() {
        //progressBar.setVisibility(View.VISIBLE);
        Observable.fromCallable(callable).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                doOnSubscribe(disposable ->
                        {
                            progressBar.setVisibility(View.VISIBLE);
                            scheduleLongRunningOperation.setEnabled(false);
                            messagearea.setText(messagearea.getText().toString() + "\n" + "Progressbar set visible");
                        }
                ).
                subscribe(getDisposableObserver());
    }

    Callable<String> callable = new Callable<String>() {
        @Override
        public String call() throws Exception {
            return doSomethingLong();
        }
    };

    public String doSomethingLong() {
        SystemClock.sleep(1000);
        return "Hello";
    }

    /**
     * Observer
     * Handles the stream of data:
     */
    private DisposableObserver<String> getDisposableObserver() {
        return new DisposableObserver<String>() {

            @Override
            public void onComplete() {
                messagearea.setText(messagearea.getText().toString() + "\n" + "OnComplete");
                progressBar.setVisibility(View.INVISIBLE);
                scheduleLongRunningOperation.setEnabled(true);
                messagearea.setText(messagearea.getText().toString() + "\n" + "Hidding Progressbar");
            }

            @Override
            public void onError(Throwable e) {
                messagearea.setText(messagearea.getText().toString() + "\n" + "OnError");
                progressBar.setVisibility(View.INVISIBLE);
                scheduleLongRunningOperation.setEnabled(true);
                messagearea.setText(messagearea.getText().toString() + "\n" + "Hidding Progressbar");
            }

            @Override
            public void onNext(String message) {
                messagearea.setText(messagearea.getText().toString() + "\n" + "onNext " + message);
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null && !subscription.isDisposed()) {
            subscription.dispose();
        }
    }

}
