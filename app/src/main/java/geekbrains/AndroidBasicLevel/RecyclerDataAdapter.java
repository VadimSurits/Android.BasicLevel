package geekbrains.AndroidBasicLevel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerDataAdapter extends RecyclerView.Adapter<RecyclerDataAdapter.ViewHolder> {
    private List<String> data1;
    private List<String> data2;

    public RecyclerDataAdapter(List<String> data1, List<String> data2) {
        this.data1 = data1;
        this.data2 = data2;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_temperature_history_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewForecastDays.setText(data1.get(position));
        holder.textViewForecastDescription.setText(data2.get(position));
    }

    @Override
    public int getItemCount() {
        return data1 == null ? 0 : data1.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewForecastDays;
        private TextView textViewForecastDescription;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            textViewForecastDays = itemView.findViewById(R.id.ForecastDaysTextView);
            textViewForecastDescription = itemView.findViewById(R.id.ForecastDescriptionTextView);
        }
    }
}
