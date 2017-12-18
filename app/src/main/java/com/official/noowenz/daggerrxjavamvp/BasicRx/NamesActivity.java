package com.official.noowenz.daggerrxjavamvp.BasicRx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.official.noowenz.daggerrxjavamvp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class NamesActivity extends AppCompatActivity {

    @BindView(R.id.item_list)
    RecyclerView colorList;

    RecyclerView nameListView;
    SimpleStringAdapter simpleStringAdapter;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_names);
        ButterKnife.bind(this);

        configureLayout();
        createObservable();
    }

    private void createObservable() {
        Observable<List<String>> listObservable = Observable.just(getNameList());
        disposable = listObservable.subscribe(names -> simpleStringAdapter.setStrings(names));

    }

    private void configureLayout() {
        nameListView = (RecyclerView) findViewById(R.id.item_list);
        nameListView.setLayoutManager(new LinearLayoutManager(this));
        simpleStringAdapter = new SimpleStringAdapter(this);
        nameListView.setAdapter(simpleStringAdapter);
    }

    private static List<String> getNameList() {
        ArrayList<String> names = new ArrayList<>();
        names.add("Amir");
        names.add("Nabin");
        names.add("Nischal");
        names.add("Prageet");
        names.add("Rabin");
        names.add("Samir");
        names.add("Sanjay");
        names.add("Shiva");
        names.add("Zohvin");
        return names;
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (disposable!=null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

}
