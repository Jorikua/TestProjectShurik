package ru.vsevolodkaganovych.testprojectshurik;


import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import ru.vsevolodkaganovych.testprojectshurik.provider.test.TestColumns;


public class FragmentEven extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final int URL_LOADER_EVEN = 0;
    private CustomAdapter mAdapter;
    private String mQuery;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_view, container, false);

        setHasOptionsMenu(true);

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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        SearchManager searchManager = (SearchManager)getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView)menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                Bundle bundle = new Bundle();
//                bundle.putString("SearchQuery", query);
//                getLoaderManager().restartLoader(URL_LOADER_EVEN, bundle, FragmentEven.this);
                if (TextUtils.isEmpty(newText)) {
                    mQuery = "";
                } else {
                    mQuery = newText;
                }
                getLoaderManager().restartLoader(URL_LOADER_EVEN, null, FragmentEven.this);
               return true;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {TestColumns._ID, TestColumns.TEXT, TestColumns.FLAG};
//        String selection = TestColumns.FLAG + " =0";
        String selection = null;
        String[] selectionArgs = null;
        if (mQuery != null) {
            selection = TestColumns.TEXT + " like ? " + " AND " + TestColumns.FLAG + " =?";
            selectionArgs = new String[] {"%" + mQuery + "%", "0"};
        }
        CursorLoader cursorLoader = new CursorLoader(getActivity().getApplicationContext(),
                TestColumns.CONTENT_URI, projection, selection, selectionArgs, null);
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
