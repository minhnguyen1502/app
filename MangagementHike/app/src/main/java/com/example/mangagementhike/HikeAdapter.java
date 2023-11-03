package com.example.mangagementhike;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class HikeAdapter    extends BaseAdapter {
    private List<Hike> hikeList;
    private Context context;
    public HikeAdapter(Context context, List<Hike> listList){
        this.context = context;
        this.hikeList = listList;
    }

    @Override
    public int getCount() {
        return hikeList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            viewHolder = new ViewHolder();
            //convert 1 resource về view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.hike_detail    ,null);
            //ánh xạ
            viewHolder.tv_id = view.findViewById(R.id.tv_id);
            viewHolder.tv_name = view.findViewById(R.id.tv_name);
            // viewHolder.ivImage = view.findViewById(R.id.ivImage);
            viewHolder.tv_location = view.findViewById(R.id.tv_location);
            viewHolder.tv_date_of_hike = view.findViewById(R.id.tv_date_of_hike);
            viewHolder.tv_parking_available = view.findViewById(R.id.tv_parking_available);
            viewHolder.tv_length_the_hike = view.findViewById(R.id.tv_length_the_hike);
            viewHolder.level_of_difficulty = view.findViewById(R.id.level_of_difficulty);
            viewHolder.tv_description = view.findViewById(R.id.tv_description);
//            viewHolder.tv_risk_assessment = view.findViewById(R.id.tv_risk_assessment);
            viewHolder.tv_vehicle = view.findViewById(R.id.tv_vehicle);
//            viewHolder.tv_estimated_time = view.findViewById(R.id.tv_estimated_time);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Hike hikes = hikeList.get(i);
        viewHolder.tv_id.setText("Hike ID: " + hikes.getId());
        viewHolder.tv_name.setText("Name: " + hikes.getName());
        viewHolder.tv_location.setText("Location: " + hikes.getLocation());
        viewHolder.tv_date_of_hike.setText("Date Of Hike: " + hikes.getDate_of_hike());
        viewHolder.tv_parking_available.setText("Parking Available: " + hikes.getParking_available());
        viewHolder.tv_length_the_hike.setText("Length The Hike: " + hikes.getLength_the_hike());
        viewHolder.level_of_difficulty.setText("Level Of Difficulty: " + hikes.getLevel_of_difficulty());
        viewHolder.tv_description.setText("Description: " + hikes.getDescription());
//        viewHolder.tv_risk_assessment.setText("Risk Assessment: " + hikes.getRisk_assessment());
        viewHolder.tv_vehicle.setText("Vehicle: " + hikes.getVehicle());
//        viewHolder.tv_estimated_time.setText("Estimated Time: " + hikes.getEstimated_time());

        return view;
    }
    class ViewHolder{
        ImageView ivImage;
        TextView tv_id, tv_name ,tv_location,tv_date_of_hike,tv_parking_available,tv_length_the_hike,
                level_of_difficulty,tv_description,tv_vehicle;
    }
}