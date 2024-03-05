package com.example.easyshopper;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import android.os.SystemClock;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.easyshopper.objects.Product;

import com.example.easyshopper.objects.RequestList;
import com.example.easyshopper.objects.User;
import com.example.easyshopper.presentation.TestStubDBMainActivity;
import com.example.easyshopper.utils.TestUtils;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.List;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UserRequestFragmentTest {
    // sleepTime used to add a delay any time we need to wait for the GUI
    private final int sleepTime = 1000;

    @Rule
    public ActivityScenarioRule<TestStubDBMainActivity> activityScenarioRule = new ActivityScenarioRule<>(TestStubDBMainActivity.class);
    private TestUtils testUtils;

    @Before
    public void setUp() {
        testUtils = new TestUtils(false);
    }

    @Test
    public void testUserRequestFragmentUI() {
        // click on the user request icon
        onView(withId(R.id.userRequest)).perform(click());

        SystemClock.sleep(sleepTime);

        // verify that the user request fragment is displayed after clicking the user request icon
        onView(withId(R.id.RequestListFragment)).check(matches(isDisplayed()));

        // checks if request list toolbar is in the right place
        onView(withId(R.id.requestListToolBar)).check(matches(isDisplayed()));

        // check if the add button display
        onView(withId(R.id.addButton)).check(matches(isDisplayed()));

        // check if the expandable list view for user request display
        onView(withId(R.id.requestListView)).check(matches(isDisplayed()));

        // TEST THE EXPANDABLE REQUEST LIST VIEW
        checkContentOfExpandableListView(R.id.requestListView);

        // check if the add button display
        onView(withId(R.id.addButton)).check(matches(isDisplayed()));

        // click on the addButton
        onView(withId(R.id.addButton)).perform(click());

        SystemClock.sleep(sleepTime);

        // check if the menu and all items pop up
        onView(withText("Create User")).check(matches(isDisplayed()));
        onView(withText("Delete User")).check(matches(isDisplayed()));
        onView(withText("Add Products to Request")).check(matches(isDisplayed()));
    }

    @Test
    public void testUserRequestFragmentDeleteUser() {
        // click on the user request icon
        onView(withId(R.id.userRequest)).perform(click());

        SystemClock.sleep(sleepTime);

        // verify that the user request fragment is displayed after clicking the user request icon
        onView(withId(R.id.RequestListFragment)).check(matches(isDisplayed()));

        // click on the addButton
        onView(withId(R.id.addButton)).perform(click());

        SystemClock.sleep(sleepTime);

        // check if the menu and all items pop up
        onView(withText("Create User")).check(matches(isDisplayed()));
        onView(withText("Delete User")).check(matches(isDisplayed()));
        onView(withText("Add Products to Request")).check(matches(isDisplayed()));

        // TEST DELETE USER
        onView(withText("Delete User")).perform(click());

        // check if the delete user pop up appears
        onView(withId(R.id.addProductPopUp)).check(matches(isDisplayed()));

        // title
        onView(withId(R.id.input_dialog_title)).check(matches(isDisplayed()));

        onView(withId(R.id.input_dialog_title)).check(matches(withText("Choose Shopping Lists to Delete:")));

        // list of users
        onView(withId(R.id.list_view)).check(matches(isDisplayed()));

        // cancel and submit button
        onView(withId(R.id.cancel_btn)).check(matches(isDisplayed()));
        onView(withId(R.id.submit_btn)).check(matches(isDisplayed()));

        // get the list of all users
        List<User> allUsers = testUtils.getExistingUsers();

        checkContentOfListView(R.id.list_view, allUsers);

        // click on the cancel button and check if it goes back to the User Request Fragment
        onView(withId(R.id.cancel_btn)).perform(click());

        SystemClock.sleep(sleepTime);

        onView(withId(R.id.RequestListFragment)).check(matches(isDisplayed()));

        // GOES BACK TO DELETE USER POP UP

        // click on the addButton
        onView(withId(R.id.addButton)).perform(click());

        SystemClock.sleep(sleepTime);

        onView(withText("Delete User")).perform(click());

        SystemClock.sleep(sleepTime);

        // click on the first user
        onData(anything())
                .inAdapterView(withId(R.id.list_view))
                .atPosition(0)
                .perform(click());

        SystemClock.sleep(sleepTime);

        // click confirm button
        onView(withId(R.id.submit_btn)).perform(click());

        SystemClock.sleep(sleepTime);

        onView(withId(R.id.RequestListFragment)).check(matches(isDisplayed()));

        // check content of expandable list view for requests again
        checkContentOfExpandableListView(R.id.requestListView);
    }

    @Test
    public void testUserRequestFragmentCreateUser() {
        // click on the user request icon
        onView(withId(R.id.userRequest)).perform(click());

        SystemClock.sleep(sleepTime);

        // verify that the user request fragment is displayed after clicking the user request icon
        onView(withId(R.id.RequestListFragment)).check(matches(isDisplayed()));

        // click on the addButton
        onView(withId(R.id.addButton)).perform(click());

        SystemClock.sleep(sleepTime);

        // check if the menu and all items pop up
        onView(withText("Create User")).check(matches(isDisplayed()));
        onView(withText("Delete User")).check(matches(isDisplayed()));
        onView(withText("Add Products to Request")).check(matches(isDisplayed()));

        // TEST CREATE USER
        onView(withText("Create User")).perform(click());

        SystemClock.sleep(sleepTime);

        // check if the pop up appears
        onView(withId(R.id.addUserPopUp)).check(matches(isDisplayed()));

        // check the content inside the pop up

        // title
        onView(withId(R.id.create_user_dialog)).check(matches(isDisplayed()));

        onView(withId(R.id.create_user_dialog)).check(matches(withText("Enter User Name")));

        // input box
        onView(withId(R.id.user_name_input)).check(matches(isDisplayed()));

        // cancel and confirm button
        onView(withId(R.id.cancel_btn)).check(matches(isDisplayed()));
        onView(withId(R.id.confirm_btn)).check(matches(isDisplayed()));

        // click on the cancel button and check if it goes back to the User Request Fragment
        onView(withId(R.id.cancel_btn)).perform(click());

        SystemClock.sleep(sleepTime);

        onView(withId(R.id.RequestListFragment)).check(matches(isDisplayed()));

        // GOES BACK TO CREATE USER POP UP

        // click on the addButton
        onView(withId(R.id.addButton)).perform(click());

        SystemClock.sleep(sleepTime);

        onView(withText("Create User")).perform(click());

        SystemClock.sleep(sleepTime);

        // type into the input box the value "test"
        onView(withId(R.id.user_name_input)).perform(typeText("test"));

        // check value of the input box after typing
        onView(withId(R.id.user_name_input)).check(matches(withText("test")));

        // click confirm button
        onView(withId(R.id.confirm_btn)).perform(click());

        SystemClock.sleep(sleepTime);

        onView(withId(R.id.RequestListFragment)).check(matches(isDisplayed()));

        // check content of expandable list view for requests again
        checkContentOfExpandableListView(R.id.requestListView);
    }

    @Test
    public void testUserRequestFragmentAddProductToRequest() {
        // click on the user request icon
        onView(withId(R.id.userRequest)).perform(click());

        SystemClock.sleep(sleepTime);

        // verify that the user request fragment is displayed after clicking the user request icon
        onView(withId(R.id.RequestListFragment)).check(matches(isDisplayed()));

        // click on the addButton
        onView(withId(R.id.addButton)).perform(click());

        SystemClock.sleep(sleepTime);

        // check if the menu and all items pop up
        onView(withText("Create User")).check(matches(isDisplayed()));
        onView(withText("Delete User")).check(matches(isDisplayed()));
        onView(withText("Add Products to Request")).check(matches(isDisplayed()));

        // TEST ADD PRODUCT TO REQUEST
        onView(withText("Add Products to Request")).perform(click());

        SystemClock.sleep(sleepTime);

        // check if the pop up appears
        onView(withId(R.id.addProductPopUp)).check(matches(isDisplayed()));

        // title
        onView(withId(R.id.input_dialog_title)).check(matches(isDisplayed()));

        onView(withId(R.id.input_dialog_title)).check(matches(withText("Choose Products to Add:")));

        // list of products
        onView(withId(R.id.list_view)).check(matches(isDisplayed()));

        // cancel and submit button
        onView(withId(R.id.cancel_btn)).check(matches(isDisplayed()));
        onView(withId(R.id.submit_btn)).check(matches(isDisplayed()));

        // get the list of all products
        List<Product> allProducts = testUtils.getAllProducts();

        checkContentOfListView(R.id.list_view, allProducts);

        // click on the cancel button and check if it goes back to the User Request Fragment
        onView(withId(R.id.cancel_btn)).perform(click());

        SystemClock.sleep(sleepTime);

        onView(withId(R.id.RequestListFragment)).check(matches(isDisplayed()));

        // GOES BACK TO ADD PRODUCT TO REQUEST USER POP UP

        // click on the addButton
        onView(withId(R.id.addButton)).perform(click());

        SystemClock.sleep(sleepTime);

        onView(withText("Add Products to Request")).perform(click());

        SystemClock.sleep(sleepTime);

        // click on the first product
        onData(anything())
                .inAdapterView(withId(R.id.list_view))
                .atPosition(0)
                .perform(click());

        // click confirm button
        onView(withId(R.id.submit_btn)).perform(click());

        SystemClock.sleep(sleepTime);

        // check if the new pop up shows asking which shopping list to add product to
        onView(withId(R.id.addProductPopUp)).check(matches(isDisplayed()));

        // title
        onView(withId(R.id.input_dialog_title)).check(matches(isDisplayed()));

        onView(withId(R.id.input_dialog_title)).check(matches(withText("Choose Shopping Lists to Add Products Onto:")));

        // list of users
        onView(withId(R.id.list_view)).check(matches(isDisplayed()));

        // cancel and submit button
        onView(withId(R.id.cancel_btn)).check(matches(isDisplayed()));
        onView(withId(R.id.submit_btn)).check(matches(isDisplayed()));

        // get the list of all users
        List<User> allUsers = testUtils.getExistingUsers();

        checkContentOfListView(R.id.list_view, allUsers);

        // click on the first user
        onData(anything())
                .inAdapterView(withId(R.id.list_view))
                .atPosition(0)
                .perform(click());

        // click confirm button
        onView(withId(R.id.submit_btn)).perform(click());

        SystemClock.sleep(sleepTime);

        // check if UserRequestFragment is shown again

        onView(withId(R.id.RequestListFragment)).check(matches(isDisplayed()));

        // check content of expandable list view for requests again
        checkContentOfExpandableListView(R.id.requestListView);
    }

    @Test
    public void testUserRequestFragmentDeclineRequest() {
        // click on the user request icon
        onView(withId(R.id.userRequest)).perform(click());

        SystemClock.sleep(sleepTime);

        // verify that the user request fragment is displayed after clicking the user request icon
        onView(withId(R.id.RequestListFragment)).check(matches(isDisplayed()));

        // check if the expandable list view for user request display
        onView(withId(R.id.requestListView)).check(matches(isDisplayed()));

        // decline the first request
        onView(withId(R.id.requestListView)).perform(clickButtonInGroupItem(0, R.id.clearRequest));

        SystemClock.sleep(sleepTime);

        // check if the delete prompt popup shows
        onView(withId(R.id.deletePromptDialog)).check(matches(isDisplayed()));

        // check content of popup
        onView(withId(R.id.input_dialog_title)).check(matches(isDisplayed()));

        onView(withId(R.id.input_dialog_title)).check(matches(withText("Clear list?")));

        // buttons
        onView(withId(R.id.yes_btn)).check(matches(isDisplayed()));
        onView(withId(R.id.no_btn)).check(matches(isDisplayed()));

        // click no button and check if it goes back to the fragment
        onView(withId(R.id.no_btn)).perform(click());

        SystemClock.sleep(sleepTime);

        onView(withId(R.id.RequestListFragment)).check(matches(isDisplayed()));
        checkContentOfExpandableListView(R.id.requestListView);

        // perform decline the first request again
        onView(withId(R.id.requestListView)).perform(clickButtonInGroupItem(0, R.id.clearRequest));

        SystemClock.sleep(sleepTime);

        // this time, click yes button and check if it goes back to the fragment
        onView(withId(R.id.yes_btn)).perform(click());

        SystemClock.sleep(sleepTime);

        onView(withId(R.id.RequestListFragment)).check(matches(isDisplayed()));
        checkContentOfExpandableListView(R.id.requestListView);
    }

    @Test
    public void testUserRequestFragmentAcceptRequest() {
        // click on the user request icon
        onView(withId(R.id.userRequest)).perform(click());

        SystemClock.sleep(sleepTime);

        // verify that the user request fragment is displayed after clicking the user request icon
        onView(withId(R.id.RequestListFragment)).check(matches(isDisplayed()));

        // check if the expandable list view for user request display
        onView(withId(R.id.requestListView)).check(matches(isDisplayed()));

        // accept the first request
        onView(withId(R.id.requestListView)).perform(clickButtonInGroupItem(0, R.id.approveRequest));

        SystemClock.sleep(sleepTime);

        // check if the popup shows
        onView(withId(R.id.addProductPopUp)).check(matches(isDisplayed()));

        // check content of popup
        onView(withId(R.id.input_dialog_title)).check(matches(isDisplayed()));

        onView(withId(R.id.input_dialog_title)).check(matches(withText("Choose Products to Approve:")));

        // list of users
        onView(withId(R.id.list_view)).check(matches(isDisplayed()));

        // cancel and submit button
        onView(withId(R.id.cancel_btn)).check(matches(isDisplayed()));
        onView(withId(R.id.submit_btn)).check(matches(isDisplayed()));

        // click no button and check if it goes back to the fragment
        onView(withId(R.id.cancel_btn)).perform(click());

        SystemClock.sleep(sleepTime);

        onView(withId(R.id.RequestListFragment)).check(matches(isDisplayed()));
        checkContentOfExpandableListView(R.id.requestListView);

        SystemClock.sleep(sleepTime);

        // perform accept the first request again
        onView(withId(R.id.requestListView)).perform(clickButtonInGroupItem(0, R.id.approveRequest));

        SystemClock.sleep(sleepTime);

        // click on the first product
        onData(anything())
                .inAdapterView(withId(R.id.list_view))
                .atPosition(0)
                .perform(click());

        SystemClock.sleep(sleepTime);

        // click submit button
        onView(withId(R.id.submit_btn)).perform(click());

        SystemClock.sleep(sleepTime);

        // check if pop up to choose store displays
        onView(withId(R.id.addProductPopUp)).check(matches(isDisplayed()));

        // check content of popup
        onView(withId(R.id.input_dialog_title)).check(matches(isDisplayed()));

        // onView(withId(R.id.input_dialog_title)).check(matches(withText("Choose Shopping Lists to Add Products Onto:")));

        // list of users
        onView(withId(R.id.list_view)).check(matches(isDisplayed()));

        // cancel and submit button
        onView(withId(R.id.cancel_btn)).check(matches(isDisplayed()));
        onView(withId(R.id.submit_btn)).check(matches(isDisplayed()));

        // click on the first store
        onData(anything())
                .inAdapterView(withId(R.id.list_view))
                .atPosition(0)
                .perform(click());

        SystemClock.sleep(sleepTime);

        // click yes button and check if it goes back to the fragment
        onView(withId(R.id.submit_btn)).perform(click());

        SystemClock.sleep(sleepTime);

        onView(withId(R.id.RequestListFragment)).check(matches(isDisplayed()));
        checkContentOfExpandableListView(R.id.requestListView);
    }

    // HELPER METHODS

    public static ViewAction clickButtonInGroupItem(final int groupPosition, final int buttonId) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isDisplayed(), isAssignableFrom(ExpandableListView.class));
            }

            @Override
            public String getDescription() {
                return "Click on a button inside a group item";
            }

            @Override
            public void perform(UiController uiController, View view) {
                ExpandableListView expandableListView = (ExpandableListView) view;
                ExpandableListAdapter adapter = expandableListView.getExpandableListAdapter();

                View groupItemView = adapter.getGroupView(groupPosition, false, null, expandableListView);
                ImageButton button = groupItemView.findViewById(buttonId);
                button.performClick();
            }
        };
    }

    private void checkContentOfListView (int listViewID, List<?> list) {
        onView(withId(listViewID)).check((view, noViewFoundException) -> {
            assertNotNull(view);

            // get the ListView
            ListView listView = (ListView) view;

            // get the adapter associated with the RecyclerView
            ListAdapter adapter = listView.getAdapter();

            // check if the adapter is not null
            assertNotNull(adapter);

            // check the number of users in the list
            int userCount = adapter.getCount();
            int expectedCount = list.size();

            // assert that the list length is as expected
            assertEquals(expectedCount, userCount);
        });

        SystemClock.sleep(sleepTime);
    }

    private void checkContentOfExpandableListView(int expandableListViewID){
        onView(withId(expandableListViewID)).check((view, noViewFoundException) -> {
            // check if requestListView is not null
            assertNotNull(view);

            // get the ExpandableListView
            ExpandableListView expandableListView = (ExpandableListView) view;

            // get the adapter associated with the ExpandableListView
            ExpandableListAdapter adapter = expandableListView.getExpandableListAdapter();

            // check if the adapter is not null
            assertNotNull(adapter);

            // get all request list
            List<RequestList> allRequestLists = testUtils.getAllRequestLists();

            // check the length for the list view
            assertEquals(allRequestLists.size(), adapter.getGroupCount());

            for (int i = 0; i < adapter.getGroupCount(); i++) {
                // get the product cart for request list
                List<Product> requestListCart = allRequestLists.get(i).getCart();

                // get the request cart view
                View parentView = adapter.getGroupView(i, false, null, expandableListView);

                // check if it is not null
                assertNotNull(parentView);

                // check if the name for shopping cart is correct
                TextView requestListNameTextView = parentView.findViewById(R.id.request_list_header_name);
                String requestListName = requestListNameTextView.getText().toString();
                String expectedRequestListName = allRequestLists.get(i).getListName();
                assertEquals(expectedRequestListName, requestListName);

                // expand the group
                expandableListView.expandGroup(i);

                SystemClock.sleep(sleepTime);

                int childCount = adapter.getChildrenCount(i);
                int expectedChildCount = requestListCart.size();

                // test if the length of each request list cart is correct
                assertEquals(expectedChildCount, childCount);

                // iterate through children of the group
                for (int j = 0; j < adapter.getChildrenCount(i); j++) {
                    View childView = adapter.getChildView(i, j, false, null, expandableListView);
                    assertNotNull(childView);

                    // check if product name is correct
                    TextView productInCartName = childView.findViewById(R.id.productNameView);
                    String cartProductName = productInCartName.getText().toString();
                    String expectedCartProductName = requestListCart.get(j).getProductName();

                    assertEquals(expectedCartProductName, cartProductName);
                }

                // close the group
                expandableListView.collapseGroup(i);

                SystemClock.sleep(sleepTime);
            }
        });

        SystemClock.sleep(sleepTime);
    }
}
