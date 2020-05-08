package com.marand.espressouitest.util;

import androidx.test.espresso.idling.CountingIdlingResource;
import kotlin.jvm.JvmField;

public class EspressoIdlingResource {

    private static final String RESOURCE = "GLOBAL";

    @JvmField
    public static CountingIdlingResource countingIdlingResource = new CountingIdlingResource(RESOURCE);

    public static void increment() {
        countingIdlingResource.increment();
    }

    public static void decrement() {
        if (!countingIdlingResource.isIdleNow())
            countingIdlingResource.decrement();
    }
}
