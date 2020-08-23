package com.desarrollo.labroomdata.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.desarrollo.labroomdata.R;

public class AgregarActivity extends AppCompatActivity {

    public static final String RPT_EXTRA = "com.desarrollo.android.roomdata.RPT";
    private EditText editTextAnimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        editTextAnimal = findViewById(R.id.edt_animal);

        final Button btn_agregar = findViewById(R.id.btn_guardar);
        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                if(TextUtils.isEmpty(editTextAnimal.getText())){
                    setResult(RESULT_CANCELED,intent);
                }else {
                    String animal = editTextAnimal.getText().toString();
                    intent.putExtra(RPT_EXTRA,animal);
                    setResult(RESULT_OK,intent);
                }
                finish();
            }
        });
    }
}