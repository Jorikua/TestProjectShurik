package ru.vsevolodkaganovych.testprojectshurik.provider.test;

import java.util.Date;

import android.database.Cursor;

import ru.vsevolodkaganovych.testprojectshurik.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code test} table.
 */
public class TestCursor extends AbstractCursor {
    public TestCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Get the {@code text} value.
     * Can be {@code null}.
     */
    public String getText() {
        Integer index = getCachedColumnIndexOrThrow(TestColumns.TEXT);
        return getString(index);
    }
}
