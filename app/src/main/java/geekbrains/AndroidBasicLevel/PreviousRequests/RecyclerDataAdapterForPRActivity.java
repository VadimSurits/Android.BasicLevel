package geekbrains.AndroidBasicLevel.previousRequests;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import geekbrains.AndroidBasicLevel.previousRequests.model.PreviousRequest;
import geekbrains.AndroidBasicLevel.R;

public class RecyclerDataAdapterForPRActivity extends RecyclerView.
        Adapter<RecyclerDataAdapterForPRActivity.ViewHolder> {

    private Activity activity;
    public static PreviousRequestsSource dataSource;

    public RecyclerDataAdapterForPRActivity(PreviousRequestsSource dataSource, Activity activity){
        this.dataSource = dataSource;
        this.activity = activity;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerDataAdapterForPRActivity.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerDataAdapterForPRActivity.ViewHolder holder, int position) {
        List<PreviousRequest> previousRequests = dataSource.getPreviousRequests();
        PreviousRequest previousRequest = previousRequests.get(position);
        holder.cityName.setText(previousRequest.cityName);
        holder.date.setText(previousRequest.date);
        holder.temperature.setText(previousRequest.temperature);
    }

    @Override
    public int getItemCount() {
//        return previousRequests == null ? 0 : previousRequests.size();
        return (int) dataSource.getCountPreviousRequests();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView cityName;
        TextView date;
        TextView temperature;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            cityName = itemView.findViewById(R.id.HoRcity);
            date = itemView.findViewById(R.id.HoRdate);
            temperature = itemView.findViewById(R.id.HoRtemperature);
        }
    }
}
