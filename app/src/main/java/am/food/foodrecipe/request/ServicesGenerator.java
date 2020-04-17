package am.food.foodrecipe.request;

import am.food.foodrecipe.util.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServicesGenerator {
    private static Retrofit retrofit=new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private static RecipeApi recipeApi=retrofit.create(RecipeApi.class);
    public static  RecipeApi getResipeApi(){
        return  recipeApi;
    }
}
