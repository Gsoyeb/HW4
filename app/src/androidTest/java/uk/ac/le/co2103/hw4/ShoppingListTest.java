package uk.ac.le.co2103.hw4;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class ShoppingListTest {

    @Rule
    public ActivityScenarioRule rule = new ActivityScenarioRule<>(MainActivity.class);

    @BeforeClass
    public static void clearDb(){
        InstrumentationRegistry.getInstrumentation().getTargetContext().deleteDatabase("Shopping_database");
    }

    @Test
    public void useAppContext(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("uk.ac.le.co2103.hw4", appContext.getPackageName());
    }

    @Test
    public void testAddNewList() {
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.shoppingList_name)).check(matches(isDisplayed()));
        onView(withId(R.id.shoppingList_name)).perform(typeText("Birthday Party"), closeSoftKeyboard());
        onView(withId(R.id.btn_create_ShoppingList)).perform(click());
        onView(withText("Birthday Party")).check(matches(isDisplayed()));
        onView(withId(R.id.recycler_view_shoppingList)).check(hasItemsCount(1));
    }

    @Test
    public void testDeleteList() throws UiObjectNotFoundException {
        testAddNewList();
        onView(withText("Birthday Party")).perform(longClick());
        onView(withId(android.R.id.button1)).perform(click());  //button2 is for no
        onView(withId(R.id.recycler_view_shoppingList)).check(hasItemsCount(0));
    }

    @Test
    public void testAddProduct() {
        fail("Not implemented yet.");
    }

    @Test
    public void testAddDuplicateProduct() {
        fail("Not implemented yet.");
    }

    @Test
    public void testEditProduct() {
        fail("Not implemented yet.");
    }

    @Test
    public void testDeleteProduct() {
        fail("Not implemented yet.");
    }


    public static ViewAssertion hasItemsCount(final int count) {
        return new ViewAssertion() {
            @Override public void check(View view, NoMatchingViewException e) {
                if (!(view instanceof RecyclerView)) {
                    throw e;
                }
                RecyclerView rv = (RecyclerView) view;
                Assert.assertEquals(rv.getAdapter().getItemCount(), count);
            }
        };
    }
}