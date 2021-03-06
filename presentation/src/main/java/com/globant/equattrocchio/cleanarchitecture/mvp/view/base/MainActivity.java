package com.globant.equattrocchio.cleanarchitecture.mvp.view.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.mvp.presenter.ImagesPresenter;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImagesView;
import com.globant.equattrocchio.data.ImagesServicesImpl;
import com.globant.equattrocchio.domain.GetJsonUseCase;
import com.globant.equattrocchio.domain.GetLatestImagesUseCase;

import butterknife.BindView;

public class MainActivity extends Activity {

    private ImagesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        GetLatestImagesUseCase getLatestImagesUseCase = new GetLatestImagesUseCase(new ImagesServicesImpl());
//        presenter = new ImagesPresenter(new ImagesView(this),getLatestImagesUseCase);
        GetJsonUseCase getJsonUseCase = new GetJsonUseCase(new ImagesServicesImpl());
        presenter = new ImagesPresenter(new ImagesView(this),getJsonUseCase);

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.register();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unregister();
    }
}