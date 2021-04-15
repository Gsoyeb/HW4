package uk.ac.le.co2103.hw4.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import uk.ac.le.co2103.hw4.DB.Product;
import uk.ac.le.co2103.hw4.DB.ShoppingList;
import uk.ac.le.co2103.hw4.R;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder>{
    private List<Product> products = new ArrayList<>();
    private ProductAdapter.OnItemClickListener listener;


    class ProductHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView quantity;
        private TextView unit;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.myProductName);
            quantity = itemView.findViewById(R.id.myProductQuantity);
            unit = itemView.findViewById(R.id.myProductUnit);

            //onClick
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION){listener.onItemClick(products.get(position));}
                }
            });
        }
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row, parent, false);
        return new ProductHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        Product currentProduct = products.get(position);
        holder.name.setText(currentProduct.getName());
        holder.quantity.setText(String.valueOf(currentProduct.getQuantity()));
        holder.unit.setText(String.valueOf(currentProduct.getUnit()));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setProducts(List<Product> products){
        this.products = products;
        notifyDataSetChanged();
    }

    //onClick
    public interface OnItemClickListener {
        void onItemClick(Product product);
    }

    public void setOnItemClickListener(ProductAdapter.OnItemClickListener listener){
        this.listener = listener;
    }
}
