package com.example.android.jokesandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class JokesFragment extends Fragment {

  public JokesFragment() {
    // Required empty public constructor
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_jokes_activity, container, false);
    Intent intent = getActivity().getIntent();
    String randomJokes = intent.getStringExtra("jokes");
    TextView jokes_TV = view.findViewById(R.id.jokes_text_view);
    jokes_TV.setText(randomJokes);

    return view;
  }
}
