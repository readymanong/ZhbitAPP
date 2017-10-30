package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dm.zhbit.R;
import com.example.dm.zhbit.beans.Student;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/13.
 */

public class StudentAdapter extends ArrayAdapter<Student> {
    private LayoutInflater mInflater;
    private int resource;

    public StudentAdapter(Context context, int resource, ArrayList<Student> objects) {
        super(context, resource, objects);
        mInflater = LayoutInflater.from(context);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Student student = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resource, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.stunum = (TextView) view.findViewById(R.id.stu_sid);
            viewHolder.stuname = (TextView) view.findViewById(R.id.stu_name);
            viewHolder.stusex = (TextView) view.findViewById(R.id.stu_sex);
            viewHolder.stumajor = (TextView) view.findViewById(R.id.stu_major);
            viewHolder.stuclass = (TextView) view.findViewById(R.id.stu_class);
            view.setTag(viewHolder);//闪退的解决关键
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.stunum.setText(student.getStunum());
        viewHolder.stuname.setText( student.getStuname());
        viewHolder.stusex.setText(student.getStusex());
        viewHolder.stumajor.setText(student.getStumajor());
        viewHolder.stuclass.setText( student.getStuclass());
        return view;
    }

    class ViewHolder {
        TextView stunum;
        TextView stuname;
        TextView stusex;
        TextView stumajor;
        TextView stuclass;
    }
}