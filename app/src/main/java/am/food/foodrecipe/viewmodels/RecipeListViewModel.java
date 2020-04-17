package am.food.foodrecipe.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import am.food.foodrecipe.models.Recipe;
import am.food.foodrecipe.repository.RecipeRepository;

public class RecipeListViewModel extends ViewModel {
    private RecipeRepository mReipesRepositiry;

    public RecipeListViewModel() {
       mReipesRepositiry=RecipeRepository.getInstance();
    }

    public LiveData<List<Recipe>>getRecipes(){
        return  mReipesRepositiry.getRecipes();
    }
    public void searchRecipesApi(String query,int  pageNumber) {
       mReipesRepositiry.searchRecipesApi(query, pageNumber);
    }
}

//AndroidView Modeli dedqum heto kxosenq tarperutyuneric
//    public RecipeListViewModel(@NonNull Application application) {
//        super(application);
//    }