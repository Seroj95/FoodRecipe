package am.food.foodrecipe;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public abstract class BaseActivity extends AppCompatActivity {
    ProgressBar mProgressBar;
    @Override
    public void setContentView(int layoutResID) {
        ConstraintLayout constraintLayout=(ConstraintLayout)getLayoutInflater().inflate(R.layout.base_activity,null);
        FrameLayout frameLayout=constraintLayout.findViewById(R.id.activity_contnt);
        mProgressBar=constraintLayout.findViewById(R.id.progres_bar);
        getLayoutInflater().inflate(layoutResID,frameLayout,true);
        super.setContentView(layoutResID);
    }
    public void showProgresBar(boolean visilibite){
        mProgressBar.setVisibility(visilibite? View.VISIBLE:View.INVISIBLE);
    }
}
