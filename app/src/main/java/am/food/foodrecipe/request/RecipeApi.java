package am.food.foodrecipe.request;

import am.food.foodrecipe.response.RecipeResponse;
import am.food.foodrecipe.response.RecipeSearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipeApi {
    //Serach
    @GET("api/search")
    Call<RecipeSearchResponse> serachRecipe(
            @Query("key") String key,
            @Query("q") String query,
            @Query("page") String page


    );

    // get recipe Request
    @GET("api/qet")
    Call<RecipeResponse> getRecipe(
            @Query("key") String key,
            @Query("rID") String recipe_id
    );
}
