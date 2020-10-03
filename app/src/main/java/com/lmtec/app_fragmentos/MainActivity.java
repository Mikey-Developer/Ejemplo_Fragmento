package com.lmtec.app_fragmentos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn_pedidos, btn_factura;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_pedidos = findViewById(R.id.boton_pedidos);
        btn_factura = findViewById(R.id.boton_factura);

        btn_pedidos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                changeFragment(new Pedidos());
            }
        });

        btn_factura.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                changeFragment(new Factura());
            }
        });

    }

    private void changeFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.ventana_fragmento,fragment);
        transaction.commit();
    }
}