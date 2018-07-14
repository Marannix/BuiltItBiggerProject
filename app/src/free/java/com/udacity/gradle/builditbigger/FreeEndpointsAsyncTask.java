package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import com.example.android.jokesandroid.JokesActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import java.io.IOException;

public class FreeEndpointsAsyncTask extends AsyncTask<Context, Void, String> {
  private InterstitialAd interstitialAd;

  public FreeEndpointsAsyncTask(InterstitialAd interstitialAd) {
    this.interstitialAd = interstitialAd;
  }

  public interface JsonGetTaskListener {
    void onComplete(String jsonString, Exception e);
  }

  private MyApi myApiService = null;
  private Context context;
  private JsonGetTaskListener listener;

  public FreeEndpointsAsyncTask setListener(JsonGetTaskListener listener) {
    this.listener = listener;
    return this;
  }

  @Override protected String doInBackground(Context... params) {
    if (myApiService == null) {
      MyApi.Builder builder =
          new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(),
              null).setRootUrl("http://10.0.2.2:8080/_ah/api/")
              .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                @Override
                public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) {
                  abstractGoogleClientRequest.setDisableGZipContent(true);
                }
              });

      myApiService = builder.build();
    }

    context = params[0];

    try {
      return myApiService.getData().execute().getData();
    } catch (IOException e) {
      return e.getMessage();
    }
  }

  @Override protected void onPostExecute(final String result) {
    if (listener != null) {
      listener.onComplete(result, null);
    }

    if (!interstitialAd.isLoaded()) {
      showJokeActivity(result);
      return;
    }

    interstitialAd.setAdListener(new AdListener() {
      @Override public void onAdClosed() {
        showJokeActivity(result);
      }
    });
  }

  private void showJokeActivity(String result) {
    final Intent intent = new Intent(context, JokesActivity.class);
    intent.putExtra("jokes", result);
    context.startActivity(intent);
  }
}