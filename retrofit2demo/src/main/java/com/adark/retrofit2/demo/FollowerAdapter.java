package com.adark.retrofit2.demo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.adark.retrofit2.demo.model.Follower;
import java.util.List;

/**
 * Created by Shelly on 2017-3-1.
 */

public class FollowerAdapter extends BaseAdapter implements IDataListChangeListener {

    private LayoutInflater mInflater;
    private List<Follower> mFollowers;

    public FollowerAdapter(LayoutInflater inflater, List<Follower> followers) {
        mInflater = inflater;
        mFollowers = followers;
    }

    @Override public int getCount() {
        return mFollowers.size();
    }

    @Override public Follower getItem(int position) {
        return mFollowers.get(position);
    }

    @Override public long getItemId(int position) {
        return position;
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_item_follower, parent, false);
            holder.avatar = (ImageView) convertView.findViewById(R.id.iv_follower_avatar);
            holder.loginName = (TextView) convertView.findViewById(R.id.tv_follower_name);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.loginName.setText(mFollowers.get(position).getLogin());

        return convertView;
    }

    @Override public void onLoadDataList(List<Follower> pFollowers) {
        mFollowers.clear();
        mFollowers.addAll(pFollowers);
        notifyDataSetChanged();
    }

    class ViewHolder {
        ImageView avatar;
        TextView loginName;
    }
}
