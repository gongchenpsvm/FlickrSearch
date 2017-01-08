package chen.gong.flickrsearch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by gongchen on 1/7/17.
 */

class FlickrRecyclerViewAdapter
        extends RecyclerView.Adapter<FlickrRecyclerViewAdapter.FlickrImageViewHolder> {
    private static final String TAG = "FlickrRecyclerViewAdapt";
    private List<Photo> mPhotoList;
    private Context mContext;

    public FlickrRecyclerViewAdapter( Context context, List<Photo> photoList) {
        mPhotoList = photoList;
        mContext = context;
    }

    @Override
    public FlickrImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: new view requested");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.browse,parent,false);
        return new FlickrImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FlickrImageViewHolder holder, int position) {
        Photo photoItem = mPhotoList.get(position);
        Log.d(TAG, "onBindViewHolder: " + photoItem.getTitle() + " --> " + position);
        Picasso.with(mContext).load(photoItem.getImage())
                .error(R.drawable.broken_image)
                .placeholder(R.drawable.placeholder)
                .into(holder.thumbnail);
        holder.title.setText(photoItem.getTitle());
    }

    @Override
    public int getItemCount() {
        //Log.d(TAG, "getItemCount: called");
        if (mPhotoList == null){
            return 0;
        } else {
            return mPhotoList.size();
        }
    }

    void loadNewData(List<Photo> newPhotos){
        mPhotoList = newPhotos;

        notifyDataSetChanged();//Tell registered observers that data's changed
    }
    public Photo getPhoto(int position){
        if (mPhotoList == null || mPhotoList.size() == 0){
            return null;
        }
        else {
            return mPhotoList.get(position);
        }
    }

    static class FlickrImageViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "FlickrImageViewHolder";
        ImageView thumbnail = null;
        TextView title = null;

        public FlickrImageViewHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "FlickrImageViewHolder starts ");
            this.thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            this.title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
