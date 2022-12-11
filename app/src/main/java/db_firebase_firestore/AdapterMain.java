package db_firebase_firestore;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class AdapterMain extends FirestoreRecyclerAdapter<Model, AdapterMain.ViewHolder> {

    public AdapterMain(@NonNull FirestoreRecyclerOptions<Model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Model model) {
        holder.position.setText(model.getPosition());
        holder.id.setText(model.getId());
        holder.name.setText(model.getName());

        holder.cardview.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), ActivitySecond.class);
            intent.putExtra("position", model.getPosition());
            view.getContext().startActivity(intent);
        });

        holder.delete.setOnClickListener(view -> getSnapshots().getSnapshot(position).getReference().delete());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView position, id, name;
        CardView cardview, delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            position = itemView.findViewById(R.id.position);
            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            cardview = itemView.findViewById(R.id.cardview);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}