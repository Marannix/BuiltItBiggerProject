package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.test.ApplicationTestCase;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class EndpointsAsyncTaskTest extends ApplicationTestCase<Application> {

  private String joke;
  private Exception error = null;
  private CountDownLatch countDownLatch = null;
  private Context context;

  public EndpointsAsyncTaskTest() {
    super(Application.class);
  }

  @Override protected void setUp() {
    countDownLatch = new CountDownLatch(1);
    context = new Instrumentation().getContext();
  }

  @Override protected void tearDown() {
    countDownLatch.countDown();
  }

  public void testJoke() {
    EndpointsAsyncTask asyncTask = new EndpointsAsyncTask();
    asyncTask.setListener(new EndpointsAsyncTask.JsonGetTaskListener() {
      @Override public void onComplete(String jsonString, Exception e) {
        joke = jsonString;
        error = e;
        countDownLatch.countDown();
      }
    }).execute(context);

    try {
      countDownLatch.await(10, TimeUnit.SECONDS);
      assertTrue(!joke.isEmpty());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }
}