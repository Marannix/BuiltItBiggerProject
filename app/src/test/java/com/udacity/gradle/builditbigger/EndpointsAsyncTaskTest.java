package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.test.ApplicationTestCase;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class EndpointsAsyncTaskTest extends ApplicationTestCase<Application> {

  private String joke;
  private Exception error = null;
  private CountDownLatch countDownLatch = null;

  public EndpointsAsyncTaskTest() {
    super(Application.class);
  }

  @Override protected void setUp() throws Exception {
    countDownLatch = new CountDownLatch(1);
    super.setUp();
  }

  @Override protected void tearDown() throws Exception {
    countDownLatch.countDown();
    super.tearDown();
  }

  public void testJoke() {
    EndpointsAsyncTask asyncTask = new EndpointsAsyncTask();
    asyncTask.setListener(new EndpointsAsyncTask.JsonGetTaskListener() {
      @Override public void onComplete(String jsonString, Exception e) {
        joke = jsonString;
        error = e;
        countDownLatch.countDown();
      }
    }).execute();

    try {
      countDownLatch.await(10, TimeUnit.SECONDS);
      assertNull(error);
      assertTrue(!joke.isEmpty());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}