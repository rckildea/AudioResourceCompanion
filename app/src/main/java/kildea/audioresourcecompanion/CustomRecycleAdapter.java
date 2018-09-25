package kildea.audioresourcecompanion;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.siyamed.shapeimageview.RoundedImageView;

import java.util.ArrayList;

public class CustomRecycleAdapter extends RecyclerView.Adapter<CustomRecycleAdapter.CustomViewHolder> {

    private LayoutInflater layoutInflater;
    private ArrayList<String> names;
    private ArrayList<Integer> images;

    CustomRecycleAdapter(Context context, ArrayList<String> the_names, ArrayList<Integer> the_images) {
        this.layoutInflater = LayoutInflater.from(context);
        this.names = the_names;
        this.images = the_images;
    }

    @NonNull
    @Override
    public CustomRecycleAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.artist_icon, parent, false);

        return new CustomRecycleAdapter.CustomViewHolder(view);
    }

    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        try {
            holder.artist_name.setText(names.get(position));
            holder.artist_image.setImageResource(images.get(position));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView artist_name;
        public RoundedImageView artist_image;
        public CustomViewHolder(View v) {
            super(v);
            artist_name = (TextView) v.findViewById(R.id.artist_name);
            artist_image = (RoundedImageView) v.findViewById(R.id.artist_image);

        }
    }
}
