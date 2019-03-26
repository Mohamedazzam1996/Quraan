package com.example.mazzam.quraan.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mazzam.quraan.Api.Model.RadiosItem;
import com.example.mazzam.quraan.R;

import java.util.List;

public class RadioRecyclerViewAdapter extends RecyclerView.Adapter<RadioRecyclerViewAdapter.ViewHolder> {
    List<RadiosItem> radiosItems;
    OnItemClickListner onplayclicklitsner;
    OnItemClickListner onstopclicklitsner;

    public void setOnplayclicklitsner(OnItemClickListner onplayclicklitsner) {
        this.onplayclicklitsner = onplayclicklitsner;
    }

    public void setOnstopclicklitsner(OnItemClickListner onstopclicklitsner) {
        this.onstopclicklitsner = onstopclicklitsner;
    }

    public RadioRecyclerViewAdapter(List<RadiosItem> radiosItems) {
        this.radiosItems = radiosItems;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.single_chanel,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        final RadiosItem radiosItem=radiosItems.get(position);
        viewHolder.qar2_tv.setText(radiosItem.getName());
        if (onplayclicklitsner!=null) {
            viewHolder.playImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onplayclicklitsner.onItemClick(position, radiosItem);
                }
            });
        }
        if (onstopclicklitsner!=null) {
            viewHolder.stopImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onstopclicklitsner.onItemClick(position, radiosItem);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (radiosItems==null)
        {
            return 0;
        }

        return radiosItems.size();
    }

    public void changeData(List<RadiosItem>radiosItems)
    {
        this.radiosItems=radiosItems;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView qar2_tv;
        ImageView playImage;
        ImageView stopImage;

        public ViewHolder(@NonNull View view) {
            super(view);
            qar2_tv=view.findViewById(R.id.qar2_tv);
            playImage=view.findViewById(R.id.play_image);
            stopImage=view.findViewById(R.id.stop_image);
        }
    }

    public interface OnItemClickListner{

        public void onItemClick(int position ,RadiosItem radiosItem);
    }
}
