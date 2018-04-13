package com.example.stu.rxjavademo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "dsfdsafgsdf";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
    }

    public void btnOne(View view) {
        //3.4
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext(" Hello ");
                e.onNext(" J ");
                e.onNext(" A ");
                e.onNext(" V ");
                e.onNext(" A ");
                e.onComplete();
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG,"接受到的事件--onSubscribe->");
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG,"接受到的事件--onNext->" + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG,"接受到的事件--onError->" + e.toString());
            }

            @Override
            public void onComplete() {
                Log.i(TAG,"接受到的事件--onComplete->");
            }
        });
    }

    public void btnTwo(View view) {
        //just方法等同于btnOne里面的方法，相当于发射onNext
        Observable.just("Hello","J","A","V","A").subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG,"接受到的事件--onSubscribe->");
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG,"接受到的事件--onNext->" + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG,"接受到的事件--onError->" + e.toString());
            }

            @Override
            public void onComplete() {
                Log.i(TAG,"接受到的事件--onComplete->");
            }
        });
    }

    public void btnThree(View view) {
        String[] items = new String[]{"Hello","J","A","V","A"};
        //just方法等同于btnOne里面的方法，相当于发射onNext
        Observable.fromArray(items).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG,"接受到的事件--onSubscribe->");
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG,"接受到的事件--onNext->" + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG,"接受到的事件--onError->" + e.toString());
            }

            @Override
            public void onComplete() {
                Log.i(TAG,"接受到的事件--onComplete->");
            }
        });
    }
}
