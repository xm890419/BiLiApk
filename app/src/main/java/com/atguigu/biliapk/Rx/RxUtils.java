package com.atguigu.biliapk.Rx;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by 熊猛 on 2017/4/6.
 */

public class RxUtils {
    private OkHttpClient client;
    public RxUtils(){
        client = new OkHttpClient();
    }
    public Observable<String> getNet(final String path){
        final OkHttpClient client = new OkHttpClient();
        return Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(final Subscriber<? super String> subscriber) {
                if(!subscriber.isUnsubscribed()) {
                    Request request = new Request.Builder().url(path).build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.e("TAG", "onFailure"+e.getMessage());
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String data = response.body().string();
                            subscriber.onNext(data);
                            subscriber.onCompleted();
                        }
                    });
                }
            }
        });
    }
}
