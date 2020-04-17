package am.food.foodrecipe.request;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import am.food.foodrecipe.ApiExecutors;
import am.food.foodrecipe.models.Recipe;
import am.food.foodrecipe.response.RecipeSearchResponse;
import am.food.foodrecipe.util.Constants;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;

import static am.food.foodrecipe.util.Constants.NETWORK_TIMEOUT;

public class RecipeApiClient {
    private  static RecipeApiClient instane;
    private MutableLiveData<List<Recipe>> mRecipes;
    private RetrievalRecipeRunable mRestveRunable;
   public static RecipeApiClient getInstance(){

        if (instane==null){
            instane=new RecipeApiClient();
        }
        return  instane;
    }
    private RecipeApiClient(){
mRecipes=new MutableLiveData<>();
    }
    public LiveData<List<Recipe>> getRecipe(){
       return mRecipes;
    }
    public void  serchRecipesApi(String query,int pageNumber){
       if (mRestveRunable !=null) {
           mRestveRunable = null;
       }
       mRestveRunable= new RetrievalRecipeRunable(query,pageNumber);
       final Future handler= ApiExecutors.getInstance().networIo().submit(mRestveRunable) ;

       ApiExecutors.getInstance().networIo().schedule(new Runnable() {
           @Override
           public void run() {
handler.cancel(true);
           }
       },NETWORK_TIMEOUT, TimeUnit.MICROSECONDS);
    }
    private  class RetrievalRecipeRunable implements  Runnable{
private String query;
private  int pageNumber;
boolean cancelRequest;

        public RetrievalRecipeRunable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            cancelRequest=false;
        }

        @Override
        public void run() {
            OkHttpClient client;
            Request request;
            try {
                Response<RecipeSearchResponse> response = getRecipes(query,pageNumber).execute();
                if (response.code()==200) {
                    List<Recipe> list = new ArrayList((Collection) response.body().getRecipes());
                    if (pageNumber == 1) {
                        mRecipes.setValue(list);
                    } else {
                        List<Recipe> cureentRecipe = mRecipes.getValue();
                        cureentRecipe.addAll(list);
                        mRecipes.setValue(cureentRecipe);
                    }
                }else {
                    String error=response.errorBody().string();
                    mRecipes.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mRecipes.postValue(null);
            }

    return;
}

        }
        private Call<RecipeSearchResponse> getRecipes(String query,int pageNumber){
            return ServicesGenerator.getResipeApi().serachRecipe(
                    Constants.API_KEY,
                    query,
                    String.valueOf(pageNumber)
            );
        }
        private  void setCancelRequest(){

        }

   }
}
