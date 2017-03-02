package com.adark.retrofit2.demo;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.adark.retrofit2.demo.model.Follower;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getName();

    private Context mContext;
    @BindView(R.id.rv_follower) RecyclerView mRecyclerView;
    private Unbinder mBind;

    private List<Follower> mFollowerList;
    //private FollowerAdapter mFollowerAdapter;
    private FollowerRecyclerAdapter mFollowerRecyclerAdapter;
    private Retrofit mRetrofit;
    private LayoutInflater mLayoutInflater;

    private IDataListChangeListener mIDataListChangeListener;
    private ProgressDialog mProgressDialog;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        mBind = ButterKnife.bind(this);
        mFollowerList = new ArrayList<>();

        mFollowerRecyclerAdapter = new FollowerRecyclerAdapter(mFollowerList);
        LinearLayoutManager linearLayoutManager =
            new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mFollowerRecyclerAdapter);

        mRetrofit = initRetrofitClient();

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.show();
    }

    @Override protected void onStart() {
        super.onStart();
        getListFollowers();
    }

    @NonNull private Retrofit initRetrofitClient() {
        OkHttpClient client =
            new OkHttpClient.Builder().cache(new Cache(FileUtil.getHttpCacheDir(this), Constant.HTTP_CACHE_SIZE))
                .connectTimeout(Constant.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(Constant.HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .build();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        return new Retrofit.Builder().baseUrl(Constant.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }

    public void getListFollowers() {
        IGithubService githubService = mRetrofit.create(IGithubService.class);
        String userName = "jeasonlzy";
        githubService.listFollowers(userName).enqueue(new Callback<List<Follower>>() {
            @Override public void onResponse(Call<List<Follower>> call, Response<List<Follower>> response) {
                Toast.makeText(mContext, "OK", Toast.LENGTH_SHORT).show();
                mFollowerList = response.body();
                mFollowerRecyclerAdapter.addData(mFollowerList);
                mProgressDialog.dismiss();
            }

            @Override public void onFailure(Call<List<Follower>> call, Throwable t) {
                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
