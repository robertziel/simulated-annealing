package edu.pjatk.nai.algorithm;

import edu.pjatk.nai.tour.Tour;

/**
 * An interface for Algorithms. Each implementation returns the {@link Tour}.
 */
public interface Algorithm {
    Tour run();
}
