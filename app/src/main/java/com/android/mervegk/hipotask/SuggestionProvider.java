package com.android.mervegk.hipotask;

import android.content.SearchRecentSuggestionsProvider;

/**
 * Author : Merve Gizem KABAOĞLU
 */
public class SuggestionProvider extends SearchRecentSuggestionsProvider {

    // Android manifestte tanımlı olabilmesi content kısmı
    public static final String AUTHORITY = "com.android.mervegk.hipotask" +
            ".SuggestionProvider";

    public static final int MODE = DATABASE_MODE_QUERIES;

    public SuggestionProvider() {
        setupSuggestions(AUTHORITY, MODE);
    }
}
