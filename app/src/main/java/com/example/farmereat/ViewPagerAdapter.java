package com.example.farmereat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> {

    private final Context context;

    private final int[] images = {
            R.drawable.img,
            R.drawable.img_1,
            R.drawable.img_2
    };

    private final int[] headings = {
            R.string.onboarding_heading_1,
            R.string.onboarding_heading_2,
            R.string.onboarding_heading_3
    };

    private final int[] paragraphs = {
            R.string.onboarding_paragraph_1,
            R.string.onboarding_paragraph_2,
            R.string.onboarding_paragraph_3
    };

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.onboarding_fragment_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onboardImg.setImageResource(images[position]);
        holder.heading.setText(headings[position]);
        holder.paragraph.setText(paragraphs[position]);
    }

    @Override
    public int getItemCount() {
        return headings.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView onboardImg;
        TextView heading;
        TextView paragraph;

        ViewHolder(View itemView) {
            super(itemView);
            onboardImg = itemView.findViewById(R.id.onboard_img_1);
            heading = itemView.findViewById(R.id.heading_1);
            paragraph = itemView.findViewById(R.id.paragraph_1);
        }
    }
}
