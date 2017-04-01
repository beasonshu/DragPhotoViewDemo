package dk.dragphotoviewdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private android.support.v7.widget.RecyclerView rvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.rvMain = (RecyclerView) findViewById(R.id.rv_activity);
        rvMain.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        MainAdapter adapter = new MainAdapter();
        adapter.setOnItemCliclListener(onItemClickListener);
        rvMain.setAdapter(adapter);
    }

    class MainAdapter extends MyRecyclerViewAdapter {
        ArrayList< Integer > mipmap = CountUtils.getMipmap();

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder viewHolder = new MyViewHolder(LayoutInflater.from(getApplicationContext()).inflate(R.layout
                    .item_iv_activity, parent, false));
            return viewHolder;
        }

        @Override
        public int getItemCount() {
            return 40;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            super.onBindViewHolder(holder, position);
            MyViewHolder viewHolder = (MyViewHolder) holder;
            viewHolder.ivProduction.setImageResource(mipmap.get(position));
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView ivProduction;

            public MyViewHolder(View itemView) {
                super(itemView);
                ivProduction = (ImageView) itemView.findViewById(R.id.iv_activity);
            }
        }
    }

    //在这调用
    MainAdapter.OnItemClickListener onItemClickListener = new MyRecyclerViewAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            ImageView iv = (ImageView) view.findViewById(R.id.iv_activity);
            start2Activity(getApplicationContext(), iv, position);
        }

        @Override
        public void onItemLongClick(View view, int position) {

        }
    };

    //主要方法
    private void start2Activity(Context context, ImageView imageView, int currentItem) {
        Intent intent = new Intent(context, Main2Activity.class);
        int location[] = new int[2];
        imageView.getLocationOnScreen(location);
        intent.putExtra("currentItem", currentItem);
        intent.putExtra("left", location[0]);
        intent.putExtra("top", location[1]);
        intent.putExtra("height", imageView.getHeight());
        intent.putExtra("width", imageView.getWidth());
        //在这把数据集合传过去
        ArrayList< Integer > list = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            list.add(CountUtils.getMipmap().get(i));
        }
        intent.putIntegerArrayListExtra("mipmaps", list);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
}
