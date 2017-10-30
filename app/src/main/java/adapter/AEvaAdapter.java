package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dm.zhbit.R;
import com.example.dm.zhbit.beans.AEvaluation;
import com.example.dm.zhbit.beans.Evaluation;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/2.
 */

public class AEvaAdapter extends ArrayAdapter<AEvaluation> {
    private LayoutInflater mInflater;
    private int resource;
    public  AEvaAdapter(Context context, int resource, ArrayList<AEvaluation> objects) {
        super(context, resource, objects);
        mInflater = LayoutInflater.from(context);
        this.resource = resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AEvaluation evaluation = getItem(position);
       ViewHolder viewHolder;
        if (convertView == null) {
            convertView=LayoutInflater.from(getContext()).inflate(resource,parent,false);
            viewHolder= new ViewHolder();
            viewHolder.text_id= (TextView) convertView.findViewById(R.id.a_text_id);
            viewHolder.text_course_name= (TextView) convertView.findViewById(R.id.a_text_course_name);
            viewHolder.text_tea_name= (TextView) convertView.findViewById(R.id.a_text_tea_name);
            viewHolder.text_rank= (TextView) convertView.findViewById(R.id.a_text_rank);
            viewHolder.text_avg_score= (TextView) convertView.findViewById(R.id.a_text_avg_score);
            viewHolder.text_plan_num= (TextView) convertView.findViewById(R.id.a_text_plan_num);
            viewHolder.text_actual_num= (TextView) convertView.findViewById(R.id.a_text_actual_num);
            viewHolder.text_eva_rate= (TextView) convertView.findViewById(R.id.a_text_eva_rate);

            convertView.setTag(viewHolder);//闪退的解决关键
        }else {

            viewHolder=(ViewHolder)convertView.getTag();
        }

        viewHolder.text_id.setText(evaluation.getId());
        viewHolder.text_course_name.setText(evaluation.getEvaluate_tea_course());
        viewHolder.text_tea_name.setText(evaluation.getEvaluate_tea_name());
        viewHolder.text_avg_score.setText( evaluation.getAvg_score());
        viewHolder.text_rank.setText(evaluation.getRank());
        viewHolder.text_plan_num.setText(evaluation.getEvl_plan_num());
        viewHolder.text_actual_num.setText(evaluation.getEvl_actual_num());
        viewHolder.text_eva_rate.setText(evaluation.getEvl_rate());

        return convertView;
    }
   public static class ViewHolder {
       public TextView text_id;
       public TextView text_course_name;
       public TextView text_tea_name;
       public TextView text_avg_score;
       public TextView text_rank;
       public TextView text_plan_num;
       public TextView text_actual_num;
       public TextView text_eva_rate;
       public TextView score4;
    }
}

