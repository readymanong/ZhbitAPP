package adapter;

import android.content.Context;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.dm.zhbit.R;
import com.example.dm.zhbit.beans.TeacherMessage;

import java.util.ArrayList;


/**
 * Created by Administrator on 2017/3/17.
 */
public class TeachersAdapter extends ArrayAdapter<TeacherMessage> {
        private LayoutInflater mInflater;
        private int resource;
        private ArrayList<TeacherMessage> objects;

        public TeachersAdapter(Context context, int resource, ArrayList<TeacherMessage> objects) {
            super(context, resource, objects);
            mInflater = LayoutInflater.from(context);
            this.resource = resource;
        }



    @Override
        public View getView(int position, View convertView, ViewGroup parent) {
        TeacherMessage tm = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view=LayoutInflater.from(getContext()).inflate(resource,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.teaId= (TextView) view.findViewById(R.id.search_tea_tid);
            viewHolder.teaName= (TextView) view.findViewById(R.id.search_tea_name);
            viewHolder.teaSex= (TextView) view.findViewById(R.id.search_tea_sex);
            viewHolder.teaCourse= (TextView) view.findViewById(R.id.search_tea_course);
           viewHolder.teaIntruduce= (TextView) view.findViewById(R.id.search_tea_intruduce);
            view.setTag(viewHolder);//闪退的解决关键
        }else {
            view=convertView;
            viewHolder=(ViewHolder) view.getTag();
        }
        viewHolder.teaId.setText(tm.getTid());
        viewHolder.teaName.setText(tm.getTname());
        viewHolder.teaSex.setText(tm.getTsex());
        viewHolder.teaCourse.setText(tm.getTcourse());
       viewHolder.teaIntruduce.setText(tm.getTintroduce());
            return view;
        }

        class ViewHolder {
            TextView teaId;
            TextView teaName;
            TextView teaSex;
            TextView teaCourse;
            TextView teaIntruduce;
        }


    }

