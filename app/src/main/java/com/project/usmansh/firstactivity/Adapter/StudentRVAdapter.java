package com.project.usmansh.firstactivity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.usmansh.firstactivity.Model.Student;
import com.project.usmansh.firstactivity.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class StudentRVAdapter extends RecyclerView.Adapter<StudentRVAdapter.ViewHolder> {

    Context context;
    ArrayList<Student> studentsList;

    public StudentRVAdapter(Context ctx, ArrayList<Student> stuList){
        context = ctx;
        studentsList = stuList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View item_layout = LayoutInflater.from(context).inflate(R.layout.item_student, parent, false);
        ViewHolder viewHolder = new ViewHolder(item_layout);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        Student stu = studentsList.get(position);
        holder.studentNameTextView.setText(stu.getName());

    }



    @Override
    public int getItemCount() {
        return studentsList.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView profileImage;
        TextView studentNameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //Linking Item_layout Components
             profileImage = itemView.findViewById(R.id.profileImage);
             studentNameTextView = itemView.findViewById(R.id.studentNameTextView);
        }
    }

}
