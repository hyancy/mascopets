package com.example.mascotas.ui.recyclerView;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mascotas.R;
import com.example.mascotas.modelos.ModelMascotas;

import java.util.ArrayList;

public class AdapterMascotas extends RecyclerView.Adapter<ViewHolderMascotas> {
    ArrayList<ModelMascotas> listaMascotas;

    View view;

    public AdapterMascotas(ArrayList<ModelMascotas> listaMascotas) {
        this.listaMascotas = listaMascotas;
    }

    @NonNull
    @Override
    public ViewHolderMascotas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutInflater = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista_mascotas, parent, false);
        view = parent;
        return new ViewHolderMascotas(layoutInflater);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMascotas holder, int position) {
        holder.render(listaMascotas.get(position));

        holder.cardMascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogMascota();
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaMascotas.size();
    }

    private void showDialogMascota() {
        TextView tvNombreMascota, tvEdadMascota, tvIdMascota, tvRazaMascota, tvUsuarioSesion;
        View divider;
        Button btnActualizarMascota;
        Dialog dialogMascota = new Dialog(view.getContext());
        dialogMascota.setContentView(R.layout.dialog_mascota);

        tvNombreMascota = dialogMascota.findViewById(R.id.tv_nombre_mascota_dialog);
        divider = dialogMascota.findViewById(R.id.view_divider_dialog);
        tvIdMascota = dialogMascota.findViewById(R.id.tv_id_mascota_dialog);
        tvRazaMascota = dialogMascota.findViewById(R.id.tv_raza_mascota_dialog);
        tvEdadMascota = dialogMascota.findViewById(R.id.tv_edad_mascota_dialog);

        btnActualizarMascota = dialogMascota.findViewById(R.id.btn_actualizar_mascoa_dialog);

        dialogMascota.show();


    }
}

