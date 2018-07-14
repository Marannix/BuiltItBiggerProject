package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class FreeMainActivity extends AppCompatActivity {

  private InterstitialAd interstitialAd;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.free_activity_main);
    interstitialAd = new InterstitialAd(this);
    interstitialAd.setAdUnitId("/6499/example/interstitial");
    AdRequest request = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
    interstitialAd.loadAd(request);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  public void freeTellJoke(View view) {
    if (interstitialAd.isLoaded()) {
      interstitialAd.show();
    }

    FreeEndpointsAsyncTask asyncTask = new FreeEndpointsAsyncTask(interstitialAd);
    asyncTask.execute(this);
  }
}
