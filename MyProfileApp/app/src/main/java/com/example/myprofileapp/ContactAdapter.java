package com.example.myprofileapp;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> implements Filterable {

    private List<Contact> contactList;
    private List<Contact> contactListFull;

    public ContactAdapter(List<Contact> contactList) {
        this.contactList = contactList;
        this.contactListFull = new ArrayList<>(contactList);
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = contactList.get(position);

        holder.tvNama.setText(contact.getNama());
        holder.tvTelepon.setText(contact.getTelepon());
        holder.tvAvatar.setText(contact.getAvatarText());

        // Set background color for avatar
        holder.layoutAvatar.setBackgroundColor(contact.getColor());

        // Set click listener for call button
        holder.btnCall.setOnClickListener(v -> {
            String phoneNumber = contact.getTelepon().replaceAll("[^0-9+]", "");
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + phoneNumber));
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public Filter getFilter() {
        return contactFilter;
    }

    private Filter contactFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Contact> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(contactListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Contact contact : contactListFull) {
                    if (contact.getNama().toLowerCase().contains(filterPattern) ||
                            contact.getTelepon().contains(filterPattern)) {
                        filteredList.add(contact);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            contactList.clear();
            contactList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvTelepon, tvAvatar;
        View btnCall, layoutAvatar;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvTelepon = itemView.findViewById(R.id.tv_telepon);
            tvAvatar = itemView.findViewById(R.id.tv_avatar);
            btnCall = itemView.findViewById(R.id.btn_call);
            layoutAvatar = itemView.findViewById(R.id.layout_avatar);
        }
    }
}