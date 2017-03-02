package com.adark.retrofit2.demo;

import com.adark.retrofit2.demo.model.Follower;
import java.util.List;

/**
 * Created by Shelly on 2017-3-2.
 */

public interface IDataListChangeListener {
    void onLoadDataList(List<Follower> pFollowers);
}
