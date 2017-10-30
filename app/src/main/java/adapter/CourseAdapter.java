package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.dm.zhbit.R;

import com.example.dm.zhbit.beans.Course;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/23.
 */

public class CourseAdapter extends ArrayAdapter<Course> {
private LayoutInflater mInflater;
private int resource;
public  CourseAdapter(Context context, int resource, ArrayList<Course> objects) {
        super(context, resource, objects);
        mInflater = LayoutInflater.from(context);
        this.resource = resource;
        }
@Override
public View getView(int position, View convertView, ViewGroup parent) {
        Course course = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
        view=LayoutInflater.from(getContext()).inflate(resource,parent,false);
        viewHolder=new ViewHolder();
        viewHolder.coursename= (TextView) view.findViewById(R.id.search_course_name);
        viewHolder.courseteas= (TextView) view.findViewById(R.id.search_course_teas);
        viewHolder.coursecontent= (TextView) view.findViewById(R.id.search_course_content);
        view.setTag(viewHolder);//闪退的解决关键
        }else {
        view=convertView;
        viewHolder=(ViewHolder) view.getTag();
        }
        viewHolder.coursename.setText(course.getCname());
        viewHolder.courseteas.setText(course.getCtea());
        viewHolder.coursecontent.setText(course.getCcontent());
        return view;
        }
class ViewHolder {
    TextView coursename;
    TextView courseteas;
    TextView coursecontent;
}
}

