package com.lmtec.app_fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Factura#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Factura extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "Producto";
    private static final String ARG_PARAM2 = "Cantidad";
    private static final String ARG_PARAM3 = "Precio";
    private static final String ARG_PARAM4 = "Iva";
    private static final String ARG_PARAM5 = "Sub-total";
    private static final String ARG_PARAM6 = "Total";

    // TODO: Rename and change types of parameters
    private String producto, cantidad, precio, iva, sub_total, total;
    private TextView text_producto, text_cantidad, text_precio, text_iva, text_sub_total, text_total;

    private static final String TAG = "Fragment Factura:";

    public Factura() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Factura.
     */
    // TODO: Rename and change types and number of parameters
    public static Factura newInstance(String param1, String param2, String param3, String param4, String param5, String param6) {
        Factura fragment = new Factura();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        args.putString(ARG_PARAM5, param5);
        args.putString(ARG_PARAM6, param6);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            producto = getArguments().getString(ARG_PARAM1);
            cantidad = getArguments().getString(ARG_PARAM2);
            precio = getArguments().getString(ARG_PARAM3);
            iva = getArguments().getString(ARG_PARAM4);
            sub_total = getArguments().getString(ARG_PARAM5);
            total = getArguments().getString(ARG_PARAM6);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_factura, container, false);

        text_producto = v.findViewById(R.id.campo_producto);
        text_cantidad = v.findViewById(R.id.campo_cantidad);
        text_precio = v.findViewById(R.id.campo_precio);
        text_iva = v.findViewById(R.id.campo_iva);
        text_sub_total = v.findViewById(R.id.campo_sub_total);
        text_total = v.findViewById(R.id.campo_total);

        text_producto.setText("Producto: " + producto);
        text_cantidad.setText("Cantidad: " + cantidad);
        text_precio.setText("Precio: " + precio);
        text_iva.setText("Iva: " + iva);
        text_sub_total.setText("Sub Total: " + sub_total);
        text_total.setText("Total: " + total);

        return v;
    }
}