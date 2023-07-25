package com.example.bancoproyectos.perfil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bancoproyectos.R;

import java.util.ArrayList;
import java.util.List;


public class PerfilAdapter {

        private Context context;
        private ArrayList<Perfils> dataset;

        public PerfilAdapter(Context context) {
            this.context = context;
            dataset = new ArrayList<>();
        }

        public void add(List<Perfils> perfilsList) {
            dataset.addAll(perfilsList);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_perfil, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            Perfils p = dataset.get(position);
            holder.title.setText(p.getUsuario());

            String url = "";

            Glide.with(context)
                    .load(p.getFoto())
                    .into(holder.image);

            return convertView;
        }

        public int getItemCount() {
            return dataset.size();
        }

        private static class ViewHolder {
            private TextView title;
            private ImageView image;

            public ViewHolder(View itemView) {
                title = itemView.findViewById(R.id.text1);
                image = itemView.findViewById(R.id.imageView12);
            }
        }





}