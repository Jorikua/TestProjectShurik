package ru.vsevolodkaganovych.testprojectshurik;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.vsevolodkaganovych.testprojectshurik.provider.test.TestColumns;


public class FragmentEven extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final int URL_LOADER_EVEN = 0;
    private CustomAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_view, container, false);

        getLoaderManager().initLoader(URL_LOADER_EVEN, null, this);

        String[] projection = {TestColumns._ID, TestColumns.TEXT};
        Cursor cursor = getActivity().getContentResolver().query(TestColumns.CONTENT_URI, projection, null, null, null);

        cursor.setNotificationUri(getActivity().getContentResolver(), TestColumns.CONTENT_URI);
        getActivity().getContentResolver().notifyChange(TestColumns.CONTENT_URI, null);

        mAdapter = new CustomAdapter(getActivity().getApplicationContext(), cursor , 0);
        setListAdapter(mAdapter);

//        getLoaderManager().restartLoader(URL_LOADER_EVEN, null, this);
        return rootView;

    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {TestColumns._ID, TestColumns.TEXT};
        CursorLoader cursorLoader = new CursorLoader(getActivity().getApplicationContext(),
                TestColumns.CONTENT_URI, projection, null, null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}
