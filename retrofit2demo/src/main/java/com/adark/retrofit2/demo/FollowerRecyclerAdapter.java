package com.adark.retrofit2.demo;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import com.adark.retrofit2.demo.model.Follower;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;

/**
 * Created by Shelly on 2017-3-2.
 */

public class FollowerRecyclerAdapter extends BaseQuickAdapter<Follower, BaseViewHolder> {

    public FollowerRecyclerAdapter(List<Follower> data) {
        super(R.layout.list_item_follower, data);
    }

    @Override protected void convert(BaseViewHolder helper, Follower item) {

        helper.setText(R.id.tv_follower_name, item.getLogin());

        ImageView iv_avatar = helper.getView(R.id.iv_follower_avatar);

        Glide.with(mContext)
            .load(item.getAvatar_url())
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher)
            .crossFade()
            .into(iv_avatar);
    }
}
