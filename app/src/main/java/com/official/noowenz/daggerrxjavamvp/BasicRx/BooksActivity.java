package com.official.noowenz.daggerrxjavamvp.BasicRx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.official.noowenz.daggerrxjavamvp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BooksActivity extends AppCompatActivity {

    @BindView(R.id.loader)
    ProgressBar loader;
    @BindView(R.id.books_list)
    RecyclerView booksList;

    private Disposable bookSubscription;
    private RecyclerView booksRecyclerView;
    private ProgressBar progressBar;
    private SimpleStringAdapter stringAdapter;
    private RestClient restClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        ButterKnife.bind(this);

        configureLayout();
        createObservable();
    }

    private void createObservable() {
        Observable<List<String>> booksObservable =
                Observable.fromCallable(() -> restClient.getFavoriteBooks());
        bookSubscription = booksObservable.
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(strings -> displayBooks(strings));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bookSubscription != null && !bookSubscription.isDisposed()) {
            bookSubscription.dispose();
        }
    }

    private void displayBooks(List<String> books) {
        stringAdapter.setStrings(books);
        progressBar.setVisibility(View.GONE);
        booksRecyclerView.setVisibility(View.VISIBLE);
    }

    private void configureLayout() {
        progressBar = (ProgressBar) findViewById(R.id.loader);
        booksRecyclerView = (RecyclerView) findViewById(R.id.books_list);
        booksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        stringAdapter = new SimpleStringAdapter(this);
        booksRecyclerView.setAdapter(stringAdapter);
        restClient = new RestClient(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (bookSubscription != null && !bookSubscription.isDisposed()) {
            bookSubscription.dispose();
        }
    }
}
