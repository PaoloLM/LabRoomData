package com.desarrollo.labroomdata.Actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.desarrollo.labroomdata.Adaptador.AnimalListAdapter;
import com.desarrollo.labroomdata.R;
import com.desarrollo.labroomdata.RoomDatabase.Animal;
import com.desarrollo.labroomdata.ViewModel.AnimalViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int NUEVO_ANIMAL_RC = 1;
    private AnimalViewModel animalViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final AnimalListAdapter adapter = new AnimalListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        animalViewModel = ViewModelProviders.of(this).get(AnimalViewModel.class);

        animalViewModel.getmAllAnimales().observe(this, new Observer<List<Animal>>() {
            @Override
            public void onChanged(List<Animal> animales) {
                adapter.setmAnimales(animales);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AgregarActivity.class);
                startActivityForResult(intent,NUEVO_ANIMAL_RC);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == NUEVO_ANIMAL_RC && resultCode == RESULT_OK){
            Animal animal = new Animal(data.getStringExtra(AgregarActivity.RPT_EXTRA));
            animalViewModel.insert(animal);

        }else {
            Toast.makeText(getApplicationContext(), "No hay datos", Toast.LENGTH_SHORT).show();
        }
    }
}