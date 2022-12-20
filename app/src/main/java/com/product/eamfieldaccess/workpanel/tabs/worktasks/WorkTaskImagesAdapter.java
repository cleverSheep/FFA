package com.product.eamfieldaccess.workpanel.tabs.worktasks;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.product.eamfieldaccess.R;

import java.util.ArrayList;

public class WorkTaskImagesAdapter extends RecyclerView.Adapter<WorkTaskImagesAdapter.ViewHolder> {

    private ArrayList list;

    public WorkTaskImagesAdapter(ArrayList list) {
        this.list = list;
    }

    @NonNull
    @Override
    public WorkTaskImagesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.selected_image_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkTaskImagesAdapter.ViewHolder holder, int position) {
        holder.imageView.setImageURI((Uri) list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.selected_image);
        }
    }

    public void addImages(@NonNull ArrayList images) {
        list.clear();
        list.addAll(images);
        notifyDataSetChanged();
    }
}
