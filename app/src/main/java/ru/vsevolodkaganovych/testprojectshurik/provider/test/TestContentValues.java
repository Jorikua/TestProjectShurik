package ru.vsevolodkaganovych.testprojectshurik.provider.test;

import java.util.Date;

import android.content.ContentResolver;
import android.net.Uri;

import ru.vsevolodkaganovych.testprojectshurik.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code test} table.
 */
public class TestContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return TestColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, TestSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public TestContentValues putText(String value) {
        mContentValues.put(TestColumns.TEXT, value);
        return this;
    }

    public TestContentValues putTextNull() {
        mContentValues.putNull(TestColumns.TEXT);
        return this;
    }

}
