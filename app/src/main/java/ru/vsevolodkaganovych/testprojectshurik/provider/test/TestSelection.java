package ru.vsevolodkaganovych.testprojectshurik.provider.test;

import java.util.Date;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import ru.vsevolodkaganovych.testprojectshurik.provider.base.AbstractSelection;

/**
 * Selection for the {@code test} table.
 */
public class TestSelection extends AbstractSelection<TestSelection> {
    @Override
    public Uri uri() {
        return TestColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code TestCursor} object, which is positioned before the first entry, or null.
     */
    public TestCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new TestCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null}.
     */
    public TestCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null}.
     */
    public TestCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }


    public TestSelection id(long... value) {
        addEquals(TestColumns._ID, toObjectArray(value));
        return this;
    }


    public TestSelection text(String... value) {
        addEquals(TestColumns.TEXT, value);
        return this;
    }

    public TestSelection textNot(String... value) {
        addNotEquals(TestColumns.TEXT, value);
        return this;
    }

    public TestSelection textLike(String... value) {
        addLike(TestColumns.TEXT, value);
        return this;
    }

    public TestSelection flag(Integer... value) {
        addEquals(TestColumns.FLAG, value);
        return this;
    }

    public TestSelection flagNot(Integer... value) {
        addNotEquals(TestColumns.FLAG, value);
        return this;
    }

    public TestSelection flagGt(int value) {
        addGreaterThan(TestColumns.FLAG, value);
        return this;
    }

    public TestSelection flagGtEq(int value) {
        addGreaterThanOrEquals(TestColumns.FLAG, value);
        return this;
    }

    public TestSelection flagLt(int value) {
        addLessThan(TestColumns.FLAG, value);
        return this;
    }

    public TestSelection flagLtEq(int value) {
        addLessThanOrEquals(TestColumns.FLAG, value);
        return this;
    }
}
