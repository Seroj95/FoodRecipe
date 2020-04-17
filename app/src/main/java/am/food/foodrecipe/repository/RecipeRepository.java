package am.food.foodrecipe.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import am.food.foodrecipe.models.Recipe;
import am.food.foodrecipe.request.RecipeApiClient;

public class RecipeRepository {
    private static RecipeRepository instance;
private RecipeApiClient recipeApiClient;
    public static RecipeRepository getInstance(){
        if (instance==null){
            instance=new RecipeRepository();

        }
        return instance;
    }
    private RecipeRepository(){
        recipeApiClient=RecipeApiClient.getInstance();

    }
public  LiveData<List<Recipe>> getRecipes(){
        return  recipeApiClient.getRecipe();
}
public void searchRecipesApi(String query,int  pageNumber){
        if (pageNumber==0){
            pageNumber=1;

        }

}
}
