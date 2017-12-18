package com.official.noowenz.daggerrxjavamvp.BasicRx;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.official.noowenz.daggerrxjavamvp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class RxJavaSimpleActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.toastbutton)
    Button toastbutton;
    @BindView(R.id.resultView)
    TextView resultView;

    CompositeDisposable disposable = new CompositeDisposable();
    public int value = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_simple);
        ButterKnife.bind(this);

        button.setOnClickListener(v -> {
            v.setEnabled(false); // disables the button until execution has finished
            Disposable subscribe = serverDownloadObservable.
                    observeOn(AndroidSchedulers.mainThread()).
                    subscribeOn(Schedulers.io()).
                    subscribe(integer -> {
                        updateTheUserInterface(integer); // this methods updates the ui
                        v.setEnabled(true); // enables it again
                    });
            disposable.add(subscribe);
        });
    }

    final Observable<Integer> serverDownloadObservable = Observable.create(emitter -> {
        SystemClock.sleep(10000); // simulate 10 sec delay
        emitter.onNext(5);
        emitter.onComplete();
    });

    private void updateTheUserInterface(int integer) {
        TextView view = (TextView) findViewById(R.id.resultView);
        view.setText(String.valueOf(integer));
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public void onClick(View view) {
        Observable<Integer> observable = Observable.create(e -> {
            SystemClock.sleep(3000);
            e.onNext(10);
            e.onComplete();
        });
//        Observable<Integer> observable = Observable.just(100);
        observable.observeOn(AndroidSchedulers.mainThread()).
                subscribeOn(Schedulers.io()).
                subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Toast.makeText(RxJavaSimpleActivity.this, "Is dispose " + d.isDisposed(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Toast.makeText(RxJavaSimpleActivity.this, "onNext " + integer + "", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(RxJavaSimpleActivity.this, "onError " + e.getMessage() + "", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(RxJavaSimpleActivity.this, "onComplete " + "Completed", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
