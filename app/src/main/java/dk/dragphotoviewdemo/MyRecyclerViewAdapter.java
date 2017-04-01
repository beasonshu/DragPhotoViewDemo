package dk.dragphotoviewdemo;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by dongjie.guo on 2017/2/15.
 */
public abstract class MyRecyclerViewAdapter extends RecyclerView.Adapter {
    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener listener;

    public void setOnItemCliclListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (listener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(holder.itemView, position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onItemLongClick(holder.itemView, position);
                    return true;
                }
            });
        }
    }
}