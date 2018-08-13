package org.example.proyectoisw1213;



import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;

import java.util.List;

public class LugarAdapter extends RecyclerView.Adapter<LugarAdapter.LugarViewHolder> {
    private List<Lugar> items;
    public static class LugarViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public ImageView foto;
        public TextView lugar;
        public TextView tipo;
        public TextView gps;
        public TextView comentario;
        public TextView ubicacion;
        public RatingBar rating;
        public MapView map;


        public LugarViewHolder(View v) {
            super(v);
            foto = (ImageView) v.findViewById(R.id.fotoCard);
            lugar = (TextView) v.findViewById(R.id.lugarCard);
            tipo = (TextView) v.findViewById(R.id.tipoCard);
            gps = (TextView) v.findViewById(R.id.gpsCard);
            comentario = (TextView) v.findViewById(R.id.comentarioCard);
            ubicacion = (TextView) v.findViewById(R.id.ubicacionCard);
            rating = (RatingBar) v.findViewById(R.id.ratingCard);


        }
    }
    public LugarAdapter(List<Lugar> items) {
        this.items = items;
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
    @Override
    public LugarViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.lugar_card, viewGroup, false);
        return new LugarViewHolder(v);
    }
    @Override
    public void onBindViewHolder(LugarViewHolder viewHolder, int i) {
        viewHolder.foto.setImageBitmap(items.get(i).getfoto());
        viewHolder.ubicacion.setText("Ubic:"+items.get(i).getubicacion());
        viewHolder.tipo.setText("Tipo:"+items.get(i).gettipo());
        viewHolder.gps.setText("GPS:"+items.get(i).getgps());
        viewHolder.comentario.setText(items.get(i).getcomentario());
        viewHolder.rating.setRating(items.get(i).getrating());
        viewHolder.lugar.setText("Lugar:"+items.get(i).getlugar());

    }
}
