package adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blogspot.athletio.R;
import com.blogspot.athletio.ShowProfileActivity;
import com.blogspot.athletio.ShowWorkoutActivity;

import java.util.List;

import general.Workout;

/**
 * Created by tanvir on 10/12/17.
 */

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder>{

    public  List<Workout> list;

    public WorkoutAdapter(List<Workout> list) {
        this.list=list;
        WorkoutViewHolder.list=list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(WorkoutViewHolder holder, int i) {
        Workout workout=list.get(i);
        holder.time.setText(" "+workout.day.day+"/"+workout.day.month+"/"+workout.day.year+"  "+String.format("%02d", workout.hour)+" : "+String.format("%02d", workout.min));
        if(workout.TYPE==0)
        holder.type.setText("Running");
        else
        holder.type.setText("Cycling");
        holder.callorie.setText(workout.callorie+" ");
        holder.distance.setText((int)workout.distanceInMeters+" m");
        holder.duration.setText(workout.timeInSec/60+" min"+workout.timeInSec%60+" sec");
        holder.speed.setText((int)(workout.distanceInMeters/workout.timeInSec)+" m/s");

    }

    @Override
    public WorkoutViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_workout, viewGroup, false);
        return new WorkoutViewHolder(itemView);
    }

    public static class WorkoutViewHolder extends RecyclerView.ViewHolder {
        static public  List<Workout> list;
        protected TextView time;
        protected TextView type;
        protected TextView callorie;
        protected TextView distance;
        protected TextView duration;
        protected TextView speed;

        public WorkoutViewHolder(View v) {
            super(v);
            time =  (TextView) v.findViewById(R.id.workoutTimeDate);
            type =  (TextView) v.findViewById(R.id.workoutType);
            callorie =  (TextView) v.findViewById(R.id.workout_calories_burnt_amount);
            distance =  (TextView) v.findViewById(R.id.workout_distance_covered_amount);
            duration= (TextView)v.findViewById(R.id.workout_duration_amount);
            speed= (TextView)v.findViewById(R.id.workout_average_speed);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int pos = getPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        Intent intent=new Intent(v.getContext(),ShowWorkoutActivity.class);
                        intent.putExtra("WorkoutKey",list.get(pos).key.toString());
                        v.getContext().startActivity(intent);
                    }
                }
            });


        }
    }
}
