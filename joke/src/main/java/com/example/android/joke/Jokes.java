package com.example.android.joke;

import java.util.List;
import java.util.Random;

import static java.util.Arrays.asList;

public class Jokes {

  private List<String> myJokeList = asList("Does anyone need an ark? I Noah guy",
      "I went to buy some camouflage trousers the other day but I couldn't find any.",
      "Deja Moo: The feeling that you've heard this bull before.",
      "I went to a seafood disco last week... and pulled a mussel.",
      "What do you call a fish with no eyes? A fsh.");

  public String getRandomJoke() {
    Random random = new Random();
    return myJokeList.get(random.nextInt(myJokeList.size()));
  }
}
