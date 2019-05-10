package com.ramprasath.mobiotics;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ramprasath.mobiotics.Model.VideoPlayer;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VideoStreamActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


//    //creating the api interface
//    Api api = retrofit.create(Api.class);
//    //now making the call object
//    //Here we are using the api method that we created inside the api interface
//    Call<List<VideoPlayer>> call = api.getVideos();



class ListAdapterView extends BaseAdapter {

    private List<VideoPlayer> getVideos;
    private Context context;

    public ListAdapterView(Context context,List<VideoPlayer> getVideos) {
            this.context = context;
            this.getVideos = getVideos;
    }

    @Override
    public int getCount() {
        return getVideos.size();
    }

    @Override
    public Object getItem(int position) {
        return getVideos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.activity_model,parent,false);
        }

        TextView textView = view.findViewById(R.id.textview);
        ImageView imageView = view.findViewById(R.id.images);
        TextView textView1 = view.findViewById(R.id.descrption);

        final VideoPlayer thisplayer = getVideos.get(position);

        textView.setText(thisplayer.getTitle());
        textView1.setText(thisplayer.getDescription());

        if (thisplayer.getThumb() != null && thisplayer.getThumb().length()>0){
            Picasso.get().load(thisplayer.getThumb()).placeholder(R.drawable.mobiotics).into(imageView);
        }else{
            Toast.makeText(context,"empty image url",Toast.LENGTH_LONG).show();
            Picasso.get().load(thisplayer.getThumb()).placeholder(R.drawable.mobiotics).into(imageView);

        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,thisplayer.getTitle(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(VideoStreamActivity.this, VideoActivity.class);
                intent.putExtra("videoTitle",thisplayer.getTitle());
                intent.putExtra("videoThumbs",thisplayer.getThumb());
                intent.putExtra("videoUrl",thisplayer.getUrl());
                intent.putExtra("videodescrption",thisplayer.getDescription());
                startActivity(intent);

            }
        });

        return view;
    }
}

private ListAdapterView adapterView;
private ListView listView;

    private void populateListView(List<VideoPlayer> getVideos) {
        listView = findViewById(R.id.videolist);
        adapterView = new ListAdapterView(this,getVideos);
        listView.setAdapter(adapterView);
    }


    @Override
    protected  void  onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videostream);

            //creating the api interface
    Api api = retrofit.create(Api.class);
    //now making the call object
    //Here we are using the api method that we created inside the api interface
    Call<List<VideoPlayer>> call = api.getVideos();


//        Api api = RetrofitClientInstance.getRetrofitInstance().create(Api.class);
//        Call<List<VideoPlayer>> call = api.getVideos();
        call.enqueue(new Callback<List<VideoPlayer>>() {

            @Override
            public void onResponse(Call<List<VideoPlayer>> call, Response<List<VideoPlayer>> response) {
                populateListView(response.body());
            }

            @Override
            public void onFailure(Call<List<VideoPlayer>> call, Throwable t) {
                Toast.makeText(VideoStreamActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });


    }

}

