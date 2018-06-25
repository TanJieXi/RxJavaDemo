package com.example.stu.rxjavademo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class TwoActivity extends RxAppCompatActivity {
    private final String TAG = "dsfdsafgsdf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
    }


    public void btnOne(View view) {
        /*.compose(this.<Integer>bindToLifecycle())*/
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onNext(4);
                e.onComplete();
            }
            //2. 使用Map变换操作符中的Function函数对被观察者发送的事件进行统一变换：整型变换成字符串类型
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return integer + "你好";
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "接受到的事件--onSubscribe->");
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "接受到的事件--onNext->" + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "接受到的事件--onError->" + e.toString());
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "接受到的事件--onComplete->");
            }
        });
    }

    public void btnTwo(View view) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onNext(4);
            }
            //2. 使用Map变换操作符中的Function函数对被观察者发送的事件进行统一变换：整型变换成字符串类型
        })
                .flatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        final List<String> list = new ArrayList<>();
                        for (int i = 0; i < 3; i++) {
                            list.add("我是事件 " + integer + "拆分后的子事件" + i);
                            // 通过flatMap中将被观察者生产的事件序列先进行拆分，再将每个事件转换为一个新的发送三个String事件
                            // 最终合并，再发送给被观察者
                        }
                        return Observable.fromIterable(list);
                    }
                }).subscribe(new Consumer<String>() {
                                 @Override
                                 public void accept(String s) throws Exception {
                                     Log.i(TAG, "accept--s->" + s);
                                 }
                             }
        );
        ;
    }
}
