package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dm.zhbit.R;
import com.example.dm.zhbit.beans.Evaluation;


import java.util.ArrayList;


/**
 * Created by Administrator on 2017/3/21.
 */

public class EvaluationAdpter extends ArrayAdapter<Evaluation> {
    private LayoutInflater mInflater;
    private int resource;
    public  EvaluationAdpter(Context context, int resource, ArrayList<Evaluation> objects) {
        super(context, resource, objects);
        mInflater = LayoutInflater.from(context);
        this.resource = resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       Evaluation evaluation = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view=LayoutInflater.from(getContext()).inflate(resource,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.a_course= (TextView) view.findViewById(R.id.a_course);
            viewHolder.a_tea= (TextView) view.findViewById(R.id.a_tea);
//            viewHolder.text_id= (TextView) view.findViewById(R.id.a_text_id);
//            viewHolder.text_course_name= (TextView) view.findViewById(R.id.a_text_course_name);
//            viewHolder.text_tea_name= (TextView) view.findViewById(R.id.a_text_tea_name);
//            viewHolder.text_rank= (TextView) view.findViewById(R.id.a_text_rank);
//            viewHolder.text_avg_score= (TextView) view.findViewById(R.id.a_text_avg_score);
//            viewHolder.text_plan_num= (TextView) view.findViewById(R.id.a_text_plan_num);
//            viewHolder.text_actual_num= (TextView) view.findViewById(R.id.a_text_actual_num);
//            viewHolder.text_eva_rate= (TextView) view.findViewById(R.id.a_text_eva_rate);
            view.setTag(viewHolder);//闪退的解决关键
        }else {
            view=convertView;
            viewHolder=(ViewHolder) view.getTag();
        }
        viewHolder.a_tea.setText(evaluation.getEvaluate_tea_name());
        viewHolder.a_course.setText(evaluation.getEvaluate_tea_course());
//        viewHolder.text_course_name.setText(evaluation.getEvaluate_tea_course());
//        viewHolder.text_tea_name.setText(evaluation.getEvaluate_tea_name());
//        viewHolder.text_eva_rate.setText(evaluation.getEvl_rate());
        return view;
    }
    class ViewHolder {
        TextView a_course;
        TextView a_tea;
//        TextView text_id;
//        TextView text_course_name;
//        TextView text_tea_name;
//        TextView text_avg_score;
//        TextView text_rank;
//        TextView text_plan_num;
//        TextView text_actual_num;
//        TextView text_eva_rate;
    }
}

