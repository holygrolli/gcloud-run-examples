package org.acme.spring.di;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;

public class JSONMatcher extends TypeSafeMatcher<String> {

    private String json;

    public JSONMatcher(String json) {
        this.json = json;
    }

    @Override
    protected boolean matchesSafely(String actual) {
        if (actual == null) {
            return false;
        }
        JSONCompareResult result = null;
        try {
            result = JSONCompare.compareJSON(json, actual, JSONCompareMode.LENIENT);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result != null && result.passed() ? true : false;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("matches " + json);
    }

    public static Matcher<String> matchesJSON(String json) {
        return new JSONMatcher(json);
    }
}
