package com.lmtec.app_fragmentos;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
//import android.widget.Toast;

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
    private EditText producto, cantidad;
    private EditText precio, iva;
    private TextView sub_total, total;

    private Fragment fragment;

    private String edit_producto, edit_cant, edit_precio, edit_iva;

    private static final String TAG = "Fragment_Pedido:";

    private TextWatcher actualizar_menu = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            edit_producto = producto.getText().toString().trim();
            edit_cant = cantidad.getText().toString().trim();
            edit_precio = precio.getText().toString().trim();
            edit_iva = iva.getText().toString().trim();

            if(!(edit_producto.isEmpty() || edit_cant.isEmpty() || edit_precio.isEmpty() || edit_iva.isEmpty())) {
                int _cantidad = Integer.parseInt(edit_cant);
                float _precio = Float.parseFloat(edit_precio);
                float _iva = Float.parseFloat(edit_iva);

                Float resultado = ((int) (((_cantidad * _precio) + 0.005f) * 100)) / 100f;

                sub_total.setText(resultado.toString());

                resultado += Math.round(resultado * _iva);

                total.setText(resultado.toString());

                boton_pagar.setEnabled(true);
            }

            else {
                boton_pagar.setEnabled(false);
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

        producto = v.findViewById(R.id.edit_producto);
        cantidad = v.findViewById(R.id.edit_cant);
        precio = v.findViewById(R.id.edit_precio);
        iva = v.findViewById(R.id.edit_iva);

        sub_total = v.findViewById(R.id.edit_sub_total);
        total = v.findViewById(R.id.edit_total);

        cantidad.addTextChangedListener(actualizar_menu);
        precio.addTextChangedListener(actualizar_menu);
        iva.addTextChangedListener(actualizar_menu);

        boton_pagar.setEnabled(false);

        boton_pagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            try {
                Bundle param = new Bundle();
                param.putString("Producto", edit_producto);
                param.putString("Cantidad", edit_cant);
                param.putString("Precio", edit_precio);
                param.putString("Iva", edit_iva);
                param.putString("Sub-total", sub_total.getText().toString().trim());
                param.putString("Total", total.getText().toString().trim());

                fragment = new Factura();
                fragment.setArguments(param);
                FragmentTransaction transaccion = getFragmentManager().beginTransaction();
                transaccion.replace(R.id.ventana_fragmento, fragment);
                transaccion.commit();
            } catch (Exception e){
                Log.i(TAG, e.getMessage());
            }
                //Toast.makeText(getContext(), "Pago realizado!", Toast.LENGTH_SHORT).show();
            }
        });


        return v;
    }


}