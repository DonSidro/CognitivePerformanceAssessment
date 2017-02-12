package com.atidon.cognitiveperformanceassessment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by SidonKK on 12/02/2017.
 */

public class LoginActivity extends Fragment {


    View LoginView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LoginView = inflater.inflate(R.layout.login_layout, container, false);
        return LoginView;
    }
}
