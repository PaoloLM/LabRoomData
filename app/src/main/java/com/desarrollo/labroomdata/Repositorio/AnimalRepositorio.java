package com.desarrollo.labroomdata.Repositorio;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.desarrollo.labroomdata.RoomDatabase.Animal;
import com.desarrollo.labroomdata.RoomDatabase.AnimalDao;
import com.desarrollo.labroomdata.RoomDatabase.AnimalRoomDatabase;

import java.util.List;

public class AnimalRepositorio {

    public AnimalDao mAnimalDao;
    public LiveData<List<Animal>> mAllAnimales;

    public AnimalRepositorio(Application application){
        AnimalRoomDatabase animalRoomDatabase = AnimalRoomDatabase.getDatabase(application);
        mAnimalDao = animalRoomDatabase.animalDao();
        mAllAnimales = mAnimalDao.getAllAnimales();
    }
    public LiveData<List<Animal>> getAllAnimales(){
        return mAllAnimales;
    }
    public void insert(Animal animal){
        new insertAsyncTask(mAnimalDao).execute(animal);
    }

    private static class insertAsyncTask extends AsyncTask<Animal,Void,Void> {

        private AnimalDao mAnimalTaskDao;
        insertAsyncTask(AnimalDao animalDao){
            mAnimalTaskDao = animalDao;
        }

        @Override
        protected Void doInBackground(Animal... animals) {
            mAnimalTaskDao.insert(animals[0]);
            return null;
        }
    }

}
