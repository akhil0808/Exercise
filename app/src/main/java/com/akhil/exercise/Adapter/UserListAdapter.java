package com.akhil.exercise.Adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akhil.exercise.Modal.ResultList;
import com.akhil.exercise.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.MyViewHolder> {
    @NonNull

    ArrayList<ResultList> resultLists;
    Context context;


    public UserListAdapter(@NonNull ArrayList<ResultList> resultLists, Context context) {
        this.resultLists = resultLists;
        this.context = context;
    }

    @Override
    public UserListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.raw_layout_list_view, null, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserListAdapter.MyViewHolder myViewHolder, final int i) {
        final ResultList resultList = resultLists.get(i);


        myViewHolder.title.setText(resultLists.get(i).getTitle());
        myViewHolder.fast_name.setText(resultLists.get(i).getFirst());
        myViewHolder.last_name.setText(resultLists.get(i).getLast());

        myViewHolder.age.setText(resultLists.get(i).getAge());
        myViewHolder.city.setText("," + resultLists.get(i).getCity());
        myViewHolder.state.setText("," + resultLists.get(i).getState());
        myViewHolder.connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideView(v, i, R.anim.slide_out_left);
                myViewHolder.accept_img_bck.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.color_acc));
            }
        });


        Picasso.with(context)
                .load(resultLists.get(i).getLarge())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(myViewHolder.profile_img);


        myViewHolder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideView(v, i, R.anim.slide_out_right);
                myViewHolder.decline_img_bck.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.colour_back));

            }
        });


    }

    private void slideView(View V, final int i, int slide_out_right) {

        Animation animation = AnimationUtils.loadAnimation(context, slide_out_right);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                resultLists.remove(i);
                notifyDataSetChanged();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        V.startAnimation(animation);
    }


    @Override
    public int getItemCount() {
        return resultLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, time_register, fast_name, last_name, age, city, state;

        ImageView profile_img,decline_img_bck,accept_img_bck;
        LinearLayout connect, cancel;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            fast_name = (TextView) itemView.findViewById(R.id.fast_name);
            last_name = (TextView) itemView.findViewById(R.id.last_name);
            age = (TextView) itemView.findViewById(R.id.age);
            city = (TextView) itemView.findViewById(R.id.city);
            state = (TextView) itemView.findViewById(R.id.state);
            cancel = (LinearLayout) itemView.findViewById(R.id.cancel);
            connect = (LinearLayout) itemView.findViewById(R.id.connect);
            profile_img = (ImageView) itemView.findViewById(R.id.profile_img);

        }
    }


}

