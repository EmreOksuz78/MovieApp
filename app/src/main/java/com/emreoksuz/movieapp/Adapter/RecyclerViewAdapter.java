package com.emreoksuz.movieapp.Adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.emreoksuz.movieapp.Activities.DetailsActivity;
import com.emreoksuz.movieapp.Model.Movie;
import com.emreoksuz.movieapp.R;

import java.io.Serializable;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RowHolder> {

    private ArrayList<Movie> moviesList;

    public RecyclerViewAdapter(ArrayList<Movie> moviesList){
        this.moviesList=moviesList;
    }


    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_row,parent,false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        Movie movie = moviesList.get(position);
        holder.title.setText("Movie Name : "+movie.getTitle());
        holder.genre.setText("Movie Country : "+movie.getCountry());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailsActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("title",movie.getTitle());
                bundle.putString("country",movie.getCountry());
                intent.putExtras(bundle);
                holder.itemView.getContext().startActivity(intent);
                Toast.makeText(holder.itemView.getContext(), movie.getTitle()+" : " + movie.getCountry(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView genre;

        public RowHolder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.movieName);
            genre=itemView.findViewById(R.id.movieGenre);

        }
    }


}
