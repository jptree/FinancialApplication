package com.example.financialapplication.ui.notifications;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.financialapplication.R;
import com.example.financialapplication.db.entity.ActionEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

public class IncompleteActionsAdapter extends RecyclerView.Adapter<IncompleteActionsAdapter.ActionHolder> {

    private List<ActionEntity> actionEntityList = new ArrayList<>();
    private Context context;
    private Drawable mImageDrawable;

    @NonNull
    @Override
    public IncompleteActionsAdapter.ActionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_action, parent, false);

        return new ActionHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ActionHolder holder, int position) {
        ActionEntity mActionEntity = actionEntityList.get(position);

        SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy", Locale.US);
        String formattedDate = formatter.format(mActionEntity.getDoByDate().getTime());
        holder.textViewDoByDate.setText(formattedDate);

//        mImageDrawable = context.getResources().getDrawable(mActionEntity.getImageId());
        holder.textViewActionTitle.setText(mActionEntity.getActionTitle());
        holder.textViewReasoning.setText(mActionEntity.getReasoning());
//        holder.imageViewIcon.setImageDrawable(mImageDrawable);
    }

    @Override
    public int getItemCount() {
        return actionEntityList.size();
    }

    public void setActionEntityList(List<ActionEntity> actionEntityList) {
        this.actionEntityList = actionEntityList;
        notifyDataSetChanged();
    }

    class ActionHolder extends RecyclerView.ViewHolder {
        private TextView textViewDoByDate;
        private ImageView imageViewIcon;
        private TextView textViewActionTitle;
        private TextView textViewReasoning;

        public ActionHolder(@NonNull View itemView) {
            super(itemView);
            textViewDoByDate = itemView.findViewById(R.id.textViewActionDate);
            textViewActionTitle = itemView.findViewById(R.id.textViewActionTitle);
            textViewReasoning = itemView.findViewById(R.id.textViewActionReasoning);
            imageViewIcon = itemView.findViewById(R.id.imageViewActionImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String[] s = new String[5];
//                    s[0] = textViewActionTitle.getText().toString();
                    //TODO: get layout type and send it here from viewmodel.
                    NavDirections action = NotificationsFragmentDirections.actionNavigationNotificationsToSpecifyUserInformationFragment(s, "Specify User Information");
                    Navigation.findNavController(view).navigate(action);
                }
            });

        }
    }
}
