package com.example.minim2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
     Activity activity;
    private List<Element> elements;

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewTitle;
        private ImageView imageViewIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle=itemView.findViewById(R.id.title);
            imageViewIcon=itemView.findViewById(R.id.icon);
        }
    }

    public MyAdapter(List<Element> myDataset, Activity activity) {
        elements = myDataset;
        this.activity=activity;
    }
    
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items, parent, false);
        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Element currentElement=elements.get(position);

        holder.textViewTitle.setText(currentElement.getAdrecaNom());
        Picasso.get().load(currentElement.getImatge().get(0)).into(holder.imageViewIcon);


    }

    @Override
    //how many items it will display (size = all items)
    public int getItemCount() {
        return elements.size();
    }

    public void setNotes(List<Element> elements){
        this.elements = elements;
        notifyDataSetChanged();
    }

    public Element getElementAt(int position){
        return elements.get(position);
    }

    public void remove(int position) {
        elements.remove(position);
        notifyItemRemoved(position);
    }
}

