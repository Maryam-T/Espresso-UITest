package com.marand.espressouitest.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.marand.espressouitest.R;
import com.marand.espressouitest.model.Photo;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotoViewHolder> {
    private static final String TAG = PhotosAdapter.class.getSimpleName();
    private List<Photo> mPhotos = new ArrayList<>();
    private OnPhotoClickListener mListener;

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_photo, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        holder.bind(mPhotos.get(position));
    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder {
        private ImageView mPhoto_image;
        private TextView mPhoto_title;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            mPhoto_image = itemView.findViewById(R.id.photo_image);
            mPhoto_title = itemView.findViewById(R.id.photo_title);

            itemView.setOnClickListener((View view) -> {
                int position = getAdapterPosition();
                if (mListener != null) {
                    mListener.onPhotoClicked(mPhotos.get(position).getTitle());
                }
            });
        }

        private void bind(Photo photo) {
            mPhoto_title.setText(photo.getTitle());
            Picasso.get()
                    .load(photo.getThumbnailUrl())
                    .into(mPhoto_image);
        }
    }

// -------------------------------------------------------------------------------------------------

    public void setPhotos(List<Photo> photos) {
        this.mPhotos = photos;
        notifyDataSetChanged();
    }

    public interface OnPhotoClickListener {
        void onPhotoClicked(String title);
    }
    public void setOnPhotoClickListener(OnPhotoClickListener listener) {
        this.mListener = listener;
    }
}
