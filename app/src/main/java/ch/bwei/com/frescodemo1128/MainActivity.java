package ch.bwei.com.frescodemo1128;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ch.bwei.com.frescodemo1128.adpter.ListAdapter;
import ch.bwei.com.frescodemo1128.bean.ImageBean;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends Activity {


    @BindView(R.id.list_view)
    ListView listView;
    private ListAdapter listAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getData();
    }

    //请求网络数据的方法
    private void getData() {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://120.27.23.105/product/getProducts?pscid=1&page=1")
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, e + "", Toast.LENGTH_SHORT).show();

                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                //成功的回调.运行在子线程中
                //  System.out.println(response.body().string());
                final String json = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();

                        ImageBean imageBean = null;

                        imageBean = gson.fromJson(json, ImageBean.class);

                        List<ImageBean.DataBean> listImage = imageBean.getData();
                        System.out.println(listImage.get(0).getTitle());
                        if (listAdapter == null) {
                            listAdapter = new ListAdapter(MainActivity.this, listImage);
                            listView.setAdapter(listAdapter);
                        } else {
                            listAdapter.notifyDataSetChanged();
                        }

                    }
                });
            }
        });
    }
}
