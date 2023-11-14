package com.example.mascotas.ui.recyclerView;

import android.app.Dialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mascotas.R;
import com.example.mascotas.modelos.ModelMascotas;

public class ViewHolderMascotas extends RecyclerView.ViewHolder {
    CardView cardMascota;
    ImageView imagenMascota;
    TextView tvNombreMascota, tvEdadMascota, tvIdMascota, tvRazaMascota,   tvUsuarioSesion;
    View divider;

    public ViewHolderMascotas(@NonNull View itemView) {
        super(itemView);

        cardMascota = itemView.findViewById(R.id.card_mascota);
        imagenMascota = itemView.findViewById(R.id.image_view_mascotas);
        tvNombreMascota = itemView.findViewById(R.id.tv_nombre_mascota_card);
        divider = itemView.findViewById(R.id.view_divider_card);
        tvIdMascota = itemView.findViewById(R.id.tv_id_mascota_card);
        tvRazaMascota = itemView.findViewById(R.id.tv_raza_mascota_card);
        tvEdadMascota = itemView.findViewById(R.id.tv_edad_mascota_card);
        tvUsuarioSesion = itemView.findViewById(R.id.tv_usuario_card);
    }

    public void render(ModelMascotas modelMascotas) {
        tvNombreMascota.setText(String.valueOf(modelMascotas.getNombre()));
        tvIdMascota.setText(String.valueOf(modelMascotas.getID()));
        tvRazaMascota.setText(String.valueOf(modelMascotas.getRaza()));
        tvEdadMascota.setText(String.valueOf(modelMascotas.getEdad()));
    }
}
