package kildea.audioresourcecompanion;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;

public class CustomArtistAdapter extends RecyclerView.Adapter<CustomArtistAdapter.CustomViewHolder> {

    private LayoutInflater layoutInflater;
    private static ArrayList<Artist> artists = new ArrayList<Artist>();
    public static Artist fragment_artist = null;
    private static CustomFragmentManager cfm;

    CustomArtistAdapter(Context context, Set<Artist> the_artists) {
        this.layoutInflater = LayoutInflater.from(context);
        artists.addAll(the_artists);
    }

    @NonNull
    @Override
    public CustomArtistAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.artist_icon, parent, false);

        return new CustomArtistAdapter.CustomViewHolder(view);
    }

    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        try {
            holder.artist = artists.get(position);
            holder.artist_name.setText(artists.get(position).getName());
            holder.artist_image_file = artists.get(position).getArtistImage();
            createArtistImage(holder);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void createArtistImage(CustomViewHolder holder) {
        Transformation transformation = new RoundedTransformationBuilder()
                .borderColor(R.color.colorPrimary)
                .borderWidthDp(1)
                .cornerRadiusDp(10)
                .build();
        Picasso.get()
                .load(holder.artist_image_file)
                .fit()
                .centerCrop()
                .transform(transformation)
                .into(holder.artist_image);
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView artist_name;
        public ImageView artist_image;
        public Artist artist;
        public File artist_image_file;

        public CustomViewHolder(View v)
        {
            super(v);

            try {
                cfm = (CustomFragmentManager) v.getContext();
            } catch (ClassCastException castException) {
                /** The activity does not implement the listener. */
            }

            artist_name = (TextView) v.findViewById(R.id.artist_name);
            artist_image = (ImageView) v.findViewById(R.id.artist_image);


            artist_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragment_artist = artist;
                    cfm.setActiveFragment(new ArtistAlbumViewFragment(), "Artist_"+fragment_artist.getName());
                }
            });
        }
    }
}
