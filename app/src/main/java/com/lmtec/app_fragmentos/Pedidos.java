package com.lmtec.app_fragmentos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Pedidos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Pedidos extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button boton_pagar;
    private EditText cantidad;
    private EditText precio, iva;
    private TextView sub_total, total;

    private static final String TAG = "Fragment_Pedido";

    private TextWatcher actualizar_menu = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            String text_cant = cantidad.getText().toString().trim();
            String text_precio = precio.getText().toString().trim();
            String text_iva = iva.getText().toString().trim();

            if(!(text_cant.isEmpty() || text_precio.isEmpty() || text_iva.isEmpty())) {
                int _cantidad = Integer.parseInt(text_cant);
                float _precio = Float.parseFloat(text_precio);
                float _iva = Float.parseFloat(text_iva);

                Float resultado =  ((int) (((_cantidad * _precio) + 0.005f) * 100)) / 100f;

                sub_total.setText(resultado.toString());

                resultado +=    Math.round(resultado * _iva);

                total.setText(resultado.toString());
            }
            else {
                Log.i(TAG, "Esta vacio!");
                sub_total.setText("0.00");
                total.setText("0.00");
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };



    public Pedidos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment pedidos.
     */
    // TODO: Rename and change types and number of parameters
    public static Pedidos newInstance(String param1, String param2) {
        Pedidos fragment = new Pedidos();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pedidos, container, false);

        boton_pagar = v.findViewById(R.id.boton_pagar);

        boton_pagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Pago realizado!", Toast.LENGTH_SHORT).show();
            }
        });

        cantidad = v.findViewById(R.id.edit_cant);
        precio = v.findViewById(R.id.edit_precio);
        iva = v.findViewById(R.id.edit_iva);

        sub_total = v.findViewById(R.id.edit_sub_total);
        total = v.findViewById(R.id.edit_total);

        cantidad.addTextChangedListener(actualizar_menu);
        precio.addTextChangedListener(actualizar_menu);
        iva.addTextChangedListener(actualizar_menu);


        return v;
    }


}