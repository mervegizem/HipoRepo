package com.android.mervegk.hipotask.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.android.mervegk.hipotask.fragment.PhotoFragment;
import com.android.mervegk.hipotask.R;

/**
 *  Author : Merve Gizem KABAOĞLU
 */

public class PhotoActivity extends AppCompatActivity {

    public static final String TAG = PhotoFragment.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

        if (fragment == null) { // fragment null ise yeni bir fragment yükleyelim
            fragment = new PhotoFragment(); // bunun için bir photofragment nesnesi oluşturmalıyız.
            fm.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
        }
    }
}
