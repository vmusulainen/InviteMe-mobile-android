package com.mva.inviteme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class InviteAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Invite> mDataSource;

    public InviteAdapter(Context context, ArrayList<Invite> items){
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Invite invite = (Invite) getItem(position);
        View rowView;
        rowView = mInflater.inflate(R.layout.list_item_invite, parent, false);

        TextView titleTextView = (TextView) rowView.findViewById(R.id.recipe_list_title);
        TextView subtitleTextView = (TextView) rowView.findViewById(R.id.recipe_list_subtitle);

        titleTextView.setText(invite.getPointName());
        subtitleTextView.setText(invite.getText());

        return rowView;
    }
}
