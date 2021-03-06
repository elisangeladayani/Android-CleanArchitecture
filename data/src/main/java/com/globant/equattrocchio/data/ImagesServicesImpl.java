package com.globant.equattrocchio.data;

import com.globant.equattrocchio.data.response.Result;
import com.globant.equattrocchio.data.service.api.SplashbaseApi;
import com.globant.equattrocchio.domain.service.ImagesServices;

import java.io.IOException;

import io.reactivex.Observer;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImagesServicesImpl implements ImagesServices {

    private static final String URL= "http://splashbase.co/";

    @Override
    public void getLatestImages(Observer<Boolean> observer) {
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(URL).
                addConverterFactory(GsonConverterFactory.create())
                .build();

        SplashbaseApi api  = retrofit.create(SplashbaseApi.class);

        Call<Result> call = api.getImages();

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                //todo: show the response.body() on the ui
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                //todo: update the UI with a connection error message
            }
        });


    }

    @Override
    public void getJSON(final Observer<String> observer) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .build();

        SplashbaseApi api  = retrofit.create(SplashbaseApi.class);

        Call<ResponseBody> call = api.getJSON();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    observer.onNext(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                    onFailure(call, e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                observer.onError(t);
            }
        });
    }
}
