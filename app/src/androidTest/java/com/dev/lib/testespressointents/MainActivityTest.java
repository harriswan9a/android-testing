package com.dev.lib.testespressointents;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.matcher.IntentMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by eric.w on 2016/11/25.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainActivityTest {


    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class, false, false);


    @Before
    public void before() throws Exception {
        Intents.init();
    }

    @After
    public void afterActivityFinished() throws Exception {
        Intents.release();
    }


    /**
     * 案例說明：<br>
     * 輸入參數：<br>
     * 斷言結果：<br>
     */
    @Test
    public void testCase01() throws Exception {
        mActivityTestRule.launchActivity(new Intent());

        Intents.intending(IntentMatchers.hasComponent(SecondActivity.class.getName())).respondWith(new Instrumentation.ActivityResult(0, new Intent()));

        onView(allOf(withId(R.id.btnStartActivity),
                withText("Start Activity For Activity"))).perform(click());

        Intents.intended(IntentMatchers.hasComponent(SecondActivity.class.getName()));
    }

    /**
     * 案例說明：<br>
     * 輸入參數：<br>
     * 斷言結果：<br>
     */
    @Test
    public void testCase02() throws Exception {
        mActivityTestRule.launchActivity(new Intent());

        Intents.intending(IntentMatchers.hasComponent(SecondActivity.class.getName())).respondWith(new Instrumentation.ActivityResult(0, new Intent()));

        onView(allOf(withId(R.id.btnStartActivity),
                withText("Start Activity From Fragment"))).perform(click());

        Intents.intended(IntentMatchers.hasComponent(SecondActivity.class.getName()));
    }

}
