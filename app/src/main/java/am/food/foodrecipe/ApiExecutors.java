package am.food.foodrecipe;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import io.reactivex.schedulers.Schedulers;

public class ApiExecutors {
    private static ApiExecutors instanse;
  public  static ApiExecutors  getInstance(){
        if (instanse==null){
            instanse=new ApiExecutors();

        }
        return  instanse;
    }
    private  final ScheduledExecutorService mNetworIo= Executors.newScheduledThreadPool(3);
    private Executor mBackgraundExetur=Executors.newFixedThreadPool(3);
    public ScheduledExecutorService networIo(){
        return mNetworIo;
//
//    public ApiExecutors() {
//     mBackgraundExetur .execute(new Runnable() {
//         @Override
//         public void run() {
//
//         }
//     });
//     mBackgraundExetur.execute(new Runnable() {
//         @Override
//         public void run() {
//
//         }
//     });
    }
}
