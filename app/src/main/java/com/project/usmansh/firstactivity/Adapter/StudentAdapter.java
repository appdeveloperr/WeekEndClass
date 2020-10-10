package com.project.usmansh.firstactivity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.project.usmansh.firstactivity.Model.Student;
import com.project.usmansh.firstactivity.R;
import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;



public class StudentAdapter extends BaseAdapter {

        Context context;
        ArrayList<Student> studentsList;

        public StudentAdapter(Context ctx, ArrayList<Student> stuList){
            context = ctx;
            studentsList = stuList;
        }



    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        // Check if an existing view is being reused, otherwise inflate the view
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_student, viewGroup, false);
        }

        //Linking Item_layout Components
        CircleImageView profileImage = view.findViewById(R.id.profileImage);
        TextView studentNameTextView = view.findViewById(R.id.studentNameTextView);


        //Getting Students Object from List and Set Student data on layoute components
        Student stu = studentsList.get(position);

        studentNameTextView.setText(stu.getName());

        return view;
    }


    @Override
    public int getCount() {
        return studentsList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


}
