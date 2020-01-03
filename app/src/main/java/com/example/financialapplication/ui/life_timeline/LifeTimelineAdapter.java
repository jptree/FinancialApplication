package com.example.financialapplication.ui.life_timeline;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.financialapplication.R;
import com.example.financialapplication.db.entity.LifeEventEntity;
import com.example.financialapplication.ui.life_picture_board.LifeElementsAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LifeTimelineAdapter extends RecyclerView.Adapter<LifeTimelineAdapter.TimelineEventHolder> {

    private List<LifeEventEntity> lifeEventEntityList = new ArrayList<>();
    private Context context;
    private Drawable mImageDrawable;

    @NonNull
    @Override
    public LifeTimelineAdapter.TimelineEventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_life_event, parent, false);

        return new TimelineEventHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TimelineEventHolder holder, int position) {
        LifeEventEntity mLifeEventEntity = lifeEventEntityList.get(position);

        SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy", Locale.US);
        String formattedDate = formatter.format(mLifeEventEntity.getBeginningDate().getTime());
        holder.textViewDate.setText(formattedDate);

        mImageDrawable = context.getResources().getDrawable(mLifeEventEntity.getImageId());
        holder.textViewEventName.setText(mLifeEventEntity.getEventName());
        holder.imageViewEventIcon.setImageDrawable(mImageDrawable);
    }

    @Override
    public int getItemCount() {
        return lifeEventEntityList.size();
    }

    public void setLifeEventEntityList(List<LifeEventEntity> lifeEventEntityList) {
        this.lifeEventEntityList = lifeEventEntityList;
        notifyDataSetChanged();
    }

    class TimelineEventHolder extends RecyclerView.ViewHolder {
        private TextView textViewDate;
        private ImageView imageViewEventIcon;
        private ImageView imageViewNoticeIcon;
        private TextView textViewEventName;

        public TimelineEventHolder(@NonNull View itemView) {
            super(itemView);
            textViewDate = itemView.findViewById(R.id.textViewEventDate);
            textViewEventName = itemView.findViewById(R.id.textViewEventTitle);
            imageViewEventIcon = itemView.findViewById(R.id.imageViewEventIcon);
            imageViewNoticeIcon = itemView.findViewById(R.id.imageEventNotice);

        }
    }
}
