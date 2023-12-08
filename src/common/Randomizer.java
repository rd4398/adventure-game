package common;

import java.util.Random;

/**
 * This class represents the random number generation functionality which will be used in creating
 * dungeons.
 */
public class Randomizer {
  private long seed;
  private final Random random;

  /**
   * Construct a Randomizer object to generate truly random numbers.
   */
  public Randomizer() {
    random = new Random();
  }

  /**
   * Construct a Randomizer object to generate predicted random numbers with seed.
   *
   * @param seed seed to generate random numbers
   */
  public Randomizer(long seed) {
    random = new Random(seed);
  }

  /**
   * This method is used to generate the random number based on upper and lower bound.
   *
   * @param lower the lower bound for random number generation
   * @param upper the upper bound for random number generation
   * @return the random number
   */
  public int generateRandomNumber(int lower, int upper) {
    if (lower > upper) {
      throw new IllegalArgumentException("The lower bound cannot be greater than upper");
    }
    return random.nextInt(upper - lower) + lower;
  }
}
