package ru.vsevolodkaganovych.testprojectshurik;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import ru.vsevolodkaganovych.testprojectshurik.provider.test.TestColumns;


public class CustomAdapter extends CursorAdapter {

    private static class ViewHolder {
        private TextView mText;
        private int textIndex;
    }

    public CustomAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        LayoutInflater inflater = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        ViewHolder holder = new ViewHolder();
        holder.mText = (TextView)view.findViewById(R.id.textView);
        holder.textIndex = cursor.getColumnIndex(TestColumns.TEXT);
        view.setTag(holder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder)view.getTag();
        holder.mText.setText(cursor.getString(holder.textIndex));
    }
}
