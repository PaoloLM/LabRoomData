package com.desarrollo.labroomdata.ViewModel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.desarrollo.labroomdata.Repositorio.AnimalRepositorio;
import com.desarrollo.labroomdata.RoomDatabase.Animal;

import java.util.List;

public class AnimalViewModel extends AndroidViewModel {

    private AnimalRepositorio animalRepositorio;

    private LiveData<List<Animal>> mAllAnimales;


    public AnimalViewModel(@NonNull Application application) {
        super(application);
        animalRepositorio = new AnimalRepositorio(application);
        mAllAnimales = animalRepositorio.getAllAnimales();
    }
    public LiveData<List<Animal>> getmAllAnimales(){return mAllAnimales;}

    public void insert(Animal animal){animalRepositorio.insert(animal);}
}
