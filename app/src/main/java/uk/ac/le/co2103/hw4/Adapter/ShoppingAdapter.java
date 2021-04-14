package uk.ac.le.co2103.hw4.Adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import uk.ac.le.co2103.hw4.DB.ShoppingList;
import uk.ac.le.co2103.hw4.MainActivity;
import uk.ac.le.co2103.hw4.R;

public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter.ShoppingListHolder>{
    private List<ShoppingList> shoppingLists = new ArrayList<>();
    private OnItemClickListener listener;
    private OnItemLongClickListener longListener;

    class ShoppingListHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private ImageView image;

        public ShoppingListHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.myShoppingName);
            image = itemView.findViewById(R.id.myImageView);

            //onClick
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION){listener.onItemClick(shoppingLists.get(position));}
                }
            });

            //onLongClick
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int position = getAdapterPosition();
                    longListener.onItemLongClick(shoppingLists.get(position));
                    return false;
                }
            });

        }

    }

    @NonNull
    @Override
    public ShoppingListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new ShoppingListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingListHolder holder, int position) {
        ShoppingList currentShoppingList = shoppingLists.get(position);
        holder.name.setText(currentShoppingList.getName());
        holder.image.setImageResource(currentShoppingList.getImage());
    }

    @Override
    public int getItemCount() {
        return shoppingLists.size();
    }

    public void setShoppingLists(List<ShoppingList> shoppingLists){
        this.shoppingLists = shoppingLists;
        notifyDataSetChanged();
    }

    //onClick
    public interface OnItemClickListener {
        void onItemClick(ShoppingList shoppingList);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    //onLongClick
    public interface OnItemLongClickListener{
        void onItemLongClick(ShoppingList shoppingList);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener){
        this.longListener = listener;
    }

}
