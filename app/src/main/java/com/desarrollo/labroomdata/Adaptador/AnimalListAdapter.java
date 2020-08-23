package com.desarrollo.labroomdata.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.desarrollo.labroomdata.R;
import com.desarrollo.labroomdata.RoomDatabase.Animal;

import java.util.List;

public class AnimalListAdapter
        extends RecyclerView.Adapter<AnimalListAdapter.AnimalViewHolder> {

    private final LayoutInflater mInflater;
    private List<Animal> mAnimal;

    public AnimalListAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

    public void setmAnimales(List<Animal> animales){
        mAnimal =animales;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item,parent,false);
        return new AnimalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalViewHolder holder, int position) {
        if(mAnimal != null){
            Animal animal = mAnimal.get(position);
            holder.animalItemView.setText(animal.getNombreAnimal());
        }else {
            holder.animalItemView.setText("Sin Animal");
        }
    }

    @Override
    public int getItemCount() {
        if(mAnimal != null){
            return mAnimal.size();
        }
        else{
            return 0;
        }
    }

    class AnimalViewHolder extends RecyclerView.ViewHolder{
        private final TextView animalItemView;

        private AnimalViewHolder(View itemView){
            super(itemView);
            animalItemView = itemView.findViewById(R.id.textView);
        }
    }
}
