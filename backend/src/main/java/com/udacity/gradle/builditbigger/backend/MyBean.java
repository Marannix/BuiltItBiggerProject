package com.udacity.gradle.builditbigger.backend;

import com.example.android.joke.Jokes;

/** The object model for the data we are sending through endpoints */
public class MyBean {
  public String getData() {
    return new Jokes().getRandomJoke();
  }
}
