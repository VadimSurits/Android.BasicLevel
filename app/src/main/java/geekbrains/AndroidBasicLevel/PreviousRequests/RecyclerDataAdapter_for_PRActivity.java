package geekbrains.AndroidBasicLevel.PreviousRequests;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import geekbrains.AndroidBasicLevel.R;

public class RecyclerDataAdapter_for_PRActivity extends RecyclerView.
        Adapter<RecyclerDataAdapter_for_PRActivity.ViewHolder> {
    private ArrayList<PreviousRequest> previousRequests;

    public RecyclerDataAdapter_for_PRActivity(ArrayList<PreviousRequest> previousRequests){
        this.previousRequests = previousRequests;
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
        holder.bind(previousRequests.get(position));
    }

    @Override
    public int getItemCount() {
        return previousRequests == null ? 0 : previousRequests.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView city;
        TextView temperature;
        TextView pressure;
        TextView windSpeed;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            city = itemView.findViewById(R.id.HoRcity);
            temperature = itemView.findViewById(R.id.HoRtemperature);
            pressure = itemView.findViewById(R.id.HoRpressure);
            windSpeed = itemView.findViewById(R.id.HoRwindSpeed);
        }

        @SuppressLint("SetTextI18n")
        public void bind (PreviousRequest previousRequest){
            city.setText(previousRequest.getCity());
            temperature.setText(Float.toString(previousRequest.getTemperature()));
            pressure.setText(Integer.toString(previousRequest.getPressure()));
            windSpeed.setText(Integer.toString(previousRequest.getWindSpeed()));
        }
    }
}
