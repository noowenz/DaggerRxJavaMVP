package com.official.noowenz.daggerrxjavamvp.RegisterModule.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.official.noowenz.daggerrxjavamvp.MyApplication;
import com.official.noowenz.daggerrxjavamvp.R;
import com.official.noowenz.daggerrxjavamvp.RegisterModule.injection.DaggerRegisterComponent;
import com.official.noowenz.daggerrxjavamvp.RegisterModule.injection.RegisterModule;
import com.official.noowenz.daggerrxjavamvp.RegisterModule.presenter.RegisterPresenterImpl;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.subscribers.DisposableSubscriber;
import rx.Observable;

import static android.text.TextUtils.isEmpty;
import static android.util.Patterns.EMAIL_ADDRESS;

public class RegisterActivity extends AppCompatActivity implements IRegisterView {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_password)
    EditText etPwd;
    @BindView(R.id.et_conform_password)
    EditText etConformPwd;
    @BindView(R.id.et_auto_address)
    EditText etAddress;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.tv_display)
    TextView tvDisplay;

    private DisposableSubscriber<Boolean> disposableObserver = null;
    private Flowable<CharSequence> nameObservable;
    private Flowable<CharSequence> emailObservable;
    private Flowable<CharSequence> passwordObservable;
    private Flowable<CharSequence> conformPasswordObservable;
    private Flowable<CharSequence> addressObservable;

    @Inject
    RegisterPresenterImpl registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        DaggerRegisterComponent.builder()
                .netComponent(((MyApplication) getApplicationContext()).getNetComponent())
                .registerModule(new RegisterModule(this))
                .build()
                .inject(this);

        initialize();
    }

    private void initialize() {
        nameObservable = RxTextView.textChanges(etName).skip(2).toFlowable(BackpressureStrategy.LATEST);
        emailObservable = RxTextView.textChanges(etEmail).skip(2).toFlowable(BackpressureStrategy.LATEST);
        passwordObservable = RxTextView.textChanges(etPwd).skip(2).toFlowable(BackpressureStrategy.LATEST);
        conformPasswordObservable = RxTextView.textChanges(etConformPwd).skip(2).toFlowable(BackpressureStrategy.LATEST);
        addressObservable = RxTextView.textChanges(etAddress).skip(2).toFlowable(BackpressureStrategy.LATEST);

        combineLatestEvents();

        btnSubmit.setEnabled(false);
        btnSubmit.setOnClickListener(v -> doSomeWork());
    }

    private void combineLatestEvents() {

        disposableObserver =
                new DisposableSubscriber<Boolean>() {
                    @Override
                    public void onNext(Boolean formValid) {
                        if (formValid) {
                            btnSubmit.setBackgroundColor(getResources().getColor(R.color.blue));
                            btnSubmit.setEnabled(true);
                        } else {
                            btnSubmit.setBackgroundColor(getResources().getColor(R.color.red));
                            btnSubmit.setEnabled(false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                };

        Flowable.combineLatest(
                nameObservable,
                emailObservable,
                passwordObservable,
                conformPasswordObservable,
                addressObservable,
                (name, email, pwd, conformPwd, address) -> {

                    boolean nameValid = !isEmpty(name) && name.length() > 3;
                    if (!nameValid) {
//                        etName.setError("Invalid Name!");
                        etName.setTextColor(getResources().getColor(R.color.red));
                    } else {
                        etName.setTextColor(getResources().getColor(R.color.black));
                    }

                    boolean emailValid = !isEmpty(email) && EMAIL_ADDRESS.matcher(email).matches();
                    if (!emailValid) {
//                        etEmail.setError("Invalid Email!");
                        etEmail.setTextColor(getResources().getColor(R.color.red));
                    } else {
                        etEmail.setTextColor(getResources().getColor(R.color.black));
                    }

                    boolean passValid = !isEmpty(pwd) && pwd.length() > 8;
                    if (!passValid) {
//                        etPwd.setError("Invalid Password!");
                        etPwd.setTextColor(getResources().getColor(R.color.red));
                    } else {
                        etPwd.setTextColor(getResources().getColor(R.color.black));
                    }

                    boolean conformPassValid = !isEmpty(conformPwd) && conformPwd.length() > 8;
                    if (!conformPassValid) {
//                        etConformPwd.setError("Invalid Password!");
                        etConformPwd.setTextColor(getResources().getColor(R.color.red));
                    } else {
                        etConformPwd.setTextColor(getResources().getColor(R.color.black));
                    }

                    boolean addressValid = !isEmpty(address) && address.length() > 5;
                    if (!addressValid) {
//                        etAddress.setError("Invalid address!");
                        etAddress.setTextColor(getResources().getColor(R.color.red));
                    } else {
                        etAddress.setTextColor(getResources().getColor(R.color.black));
                    }

                    return nameValid && emailValid && passValid && conformPassValid && addressValid;
                })
                .subscribe(disposableObserver);
    }

    private void doSomeWork() {
        Single.just("nabin")
                .subscribe(getSingleObserver());
    }

    private SingleObserver<String> getSingleObserver() {
        return new SingleObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onSuccess(String value) {
                tvDisplay.append(" onNext : value : " + value);
            }

            @Override
            public void onError(Throwable e) {
                tvDisplay.append(" onError : " + e.getMessage());
            }
        };
    }

    @Override
    public void onValidationSuccess(String name, String email, String password, String conform_password, String address) {

    }

    @Override
    public void onValidationError(String error) {

    }

    @Override
    public void onRegisterSuccess() {

    }

    @Override
    public void onError(String error) {

    }
}
