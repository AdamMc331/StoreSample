package com.adammcneilly.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by adam.mcneilly on 9/15/16.
 */
public class StoreAdapter extends BaseAdapter {
    private Context context;
    private List<Store> stores;

    public StoreAdapter(Context context, List<Store> stores) {
        this.context = context;
        this.stores = stores;
    }

    public void add(Store store) {
        stores.add(store);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        stores.remove(position);
        notifyDataSetChanged();
    }

    public void swapStores(List<Store> stores) {
        this.stores = stores;
        notifyDataSetChanged();
    }

    public void switchStores(Store store1, Store store2) {
        Collections.swap(
                stores,
                stores.indexOf(store1),
                stores.indexOf(store2)
        );
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return stores.size();
    }

    @Override
    public Object getItem(int position) {
        return stores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        StoreViewHolder viewHolder;

        // Convert view is null, this is the first row
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_store, viewGroup, false);
            viewHolder = new StoreViewHolder(convertView);
            // Set view holder as tag
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (StoreViewHolder) convertView.getTag();
        }

        Store currentStore = stores.get(position);
        viewHolder.bindStore(currentStore);

        return convertView;
    }

    public class StoreViewHolder {
        private TextView storeName;
        private TextView storePhone;
        private TextView storeAddress;

        public StoreViewHolder(View view) {
            storeName = (TextView) view.findViewById(R.id.store_name);
            storePhone = (TextView) view.findViewById(R.id.store_phone);
            storeAddress = (TextView) view.findViewById(R.id.store_address);
        }

        public void bindStore(Store store) {
            storeName.setText(store.getName());
            storePhone.setText(store.getPhoneNumber());
            storeAddress.setText(store.getAddress());
        }
    }
}
