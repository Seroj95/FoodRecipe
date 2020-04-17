package am.food.foodrecipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import am.food.foodrecipe.models.Recipe;
import am.food.foodrecipe.request.RecipeApi;
import am.food.foodrecipe.request.ServicesGenerator;
import am.food.foodrecipe.response.RecipeSearchResponse;
import am.food.foodrecipe.util.Constants;
import am.food.foodrecipe.util.Testing;
import am.food.foodrecipe.viewmodels.RecipeListViewModel;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeListActivity extends BaseActivity {
    private RecipeListViewModel mRecipeListViewModel = new RecipeListViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        mRecipeListViewModel = ViewModelProviders.of(this).get(RecipeListViewModel.class);
//findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//        testRetroofitRequest();
//    }
//});
        subscribeObservelable();
        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testRetroofitRequest();
            }
        });
    }

    private void subscribeObservelable() {
        mRecipeListViewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                if (recipes != null) {
                    Testing.printRecipe(recipes,"recipe test");
                }
            }
        });
    }

    private void testRetroofitRequest() {
        RecipeApi recipeApi = ServicesGenerator.getResipeApi();
        Call<RecipeSearchResponse> responseCall = recipeApi.serachRecipe(Constants.API_KEY, "Chiken", "1");
        responseCall.enqueue(new Callback<RecipeSearchResponse>() {
            @Override
            public void onResponse(Call<RecipeSearchResponse> call, Response<RecipeSearchResponse> response) {
                if (response.code() == 200) {
                    List<Recipe> recipes = new ArrayList<>(response.body().getRecipes());
                    for (Recipe recipe : recipes) {

                    }
                } else {
                    try {
                        Log.d("onResponse: ", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<RecipeSearchResponse> call, Throwable t) {

            }
        });

    }

    private void searchRecipesApi(String query, int pageNumber) {
        mRecipeListViewModel.searchRecipesApi(query, pageNumber);
    }

    private void testRetrofitRequest() {
        searchRecipesApi("chicken brekast", 1);
    }
}
