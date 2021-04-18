package uk.ac.le.co2103.hw4;

import android.content.Context;
import android.os.IBinder;
import android.view.View;
import android.view.WindowManager;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.Root;
import androidx.test.espresso.ViewAssertion;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;

import androidx.test.uiautomator.UiObjectNotFoundException;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
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
    public void testAddProduct() throws InterruptedException{
        testAddNewList();
        TimeUnit.SECONDS.sleep(1);      //added otherwise directs the program to the gallery weirdly
        onView(withText("Birthday Party")).perform(click());
        onView(withId(R.id.fabAddProduct)).check(matches(isDisplayed()));
        onView(withId(R.id.fabAddProduct)).perform(click());
        onView(withId(R.id.add_product_name)).perform(typeText("Cake"),closeSoftKeyboard());
        onView(withId(R.id.add_product_quantity)).perform(typeText("1"), closeSoftKeyboard());
        onView(withId(R.id.add_text_unit)).perform(click());
        onView(withText("Unit")).perform(click());
        onView(withId(R.id.btnAddProduct)).perform(click());
        onView(withId(R.id.recycler_view_product)).check(hasItemsCount(1));


    }

    @Test
    public void testAddDuplicateProduct() throws InterruptedException{
        testAddProduct();
        TimeUnit.SECONDS.sleep(1);
        onView(withId(R.id.fabAddProduct)).check(matches(isDisplayed()));
        onView(withId(R.id.fabAddProduct)).perform(click());
        onView(withId(R.id.add_product_name)).perform(typeText("Cake"),closeSoftKeyboard());
        onView(withId(R.id.add_product_quantity)).perform(typeText("1"), closeSoftKeyboard());
        onView(withId(R.id.add_text_unit)).perform(click());
        onView(withText("Unit")).perform(click());
        onView(withId(R.id.btnAddProduct)).perform(click());

        onView(withText("Product already exists")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }

    @Test
    public void testEditProduct() throws InterruptedException{
        testAddProduct();
        TimeUnit.SECONDS.sleep(1);
        onView(withText("Cake")).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        TimeUnit.SECONDS.sleep(1);
        onView(withId(R.id.edit_product_moveUp)).perform(click());
        onView(withId(R.id.edit_product_moveUp)).perform(click());
        onView(withId(R.id.btnEditProduct)).perform(click());
    }

    @Test
    public void testDeleteProduct() throws InterruptedException{
        testAddProduct();
        TimeUnit.SECONDS.sleep(1);
        onView(withText("Cake")).perform(click());
        onView(withId(android.R.id.button2)).perform(click());
        onView(withId(R.id.recycler_view_product)).check(hasItemsCount(0));
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


    public class ToastMatcher extends TypeSafeMatcher<Root> {

        @Override
        public void describeTo(Description description) {
            description.appendText("is toast");
        }

        @Override
        public boolean matchesSafely(Root root) {
            int type = root.getWindowLayoutParams().get().type;
            if ((type == WindowManager.LayoutParams.TYPE_TOAST)) {
                IBinder windowToken = root.getDecorView().getWindowToken();
                IBinder appToken = root.getDecorView().getApplicationWindowToken();
                if (windowToken == appToken) {
                    //means this window isn't contained by any other windows.
                    return true;
                }
            }
            return false;
        }
    }

}