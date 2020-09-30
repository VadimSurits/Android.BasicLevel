package geekbrains.AndroidBasicLevel.PreviousRequests;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import geekbrains.AndroidBasicLevel.PreviousRequests.model.PreviousRequest;

import geekbrains.AndroidBasicLevel.R;

public class RecyclerDataAdapter_for_PRActivity extends RecyclerView.
        Adapter<RecyclerDataAdapter_for_PRActivity.ViewHolder> {
//    private ArrayList<PreviousRequest> previousRequests;
    private Activity activity;
    private PreviousRequestsSource dataSource;

    public RecyclerDataAdapter_for_PRActivity(PreviousRequestsSource dataSource, Activity activity){
//            (ArrayList<PreviousRequest> previousRequests){
//        this.previousRequests = previousRequests;
        this.dataSource = dataSource;
        this.activity = activity;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerDataAdapter_for_PRActivity.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerDataAdapter_for_PRActivity.ViewHolder holder, int position) {
//        holder.bind(previousRequests.get(position));
        List<PreviousRequest> previousRequests = dataSource.getPreviousRequests();
        PreviousRequest previousRequest = previousRequests.get(position);
        holder.cityName.setText(previousRequest.cityName);
        holder.date.setText(previousRequest.date);
        holder.temperature.setText(Float.toString(previousRequest.temperature));
    }

    @Override
    public int getItemCount() {
//        return previousRequests == null ? 0 : previousRequests.size();
        return (int) dataSource.getCountPreviousRequests();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
//        TextView city;
//        TextView date;
//        TextView temperature;
        TextView cityName;
        TextView date;
        TextView temperature;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
//            city = itemView.findViewById(R.id.HoRcity);
//            date = itemView.findViewById(R.id.HoRdate);
//            temperature = itemView.findViewById(R.id.HoRtemperature);
            cityName = itemView.findViewById(R.id.HoRcity);
            date = itemView.findViewById(R.id.HoRdate);
            temperature = itemView.findViewById(R.id.HoRtemperature);
        }

        @SuppressLint("SetTextI18n")
        public void bind (PreviousRequest previousRequest){
//            city.setText(previousRequest.getCity());
//            date.setText(previousRequest.getCurrentDate());
//            temperature.setText(Float.toString(previousRequest.getTemperature()));


        }
    }
}
