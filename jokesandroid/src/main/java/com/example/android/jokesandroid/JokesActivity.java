package com.example.android.jokesandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class JokesActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_jokes);

    getSupportFragmentManager().beginTransaction()
        .add(R.id.jokes_frame_layout, new JokesFragment())
        .commit();
  }
}
