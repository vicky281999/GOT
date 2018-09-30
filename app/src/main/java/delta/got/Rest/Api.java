package delta.got.Rest;

import java.util.List;

import delta.got.Model.CharacterName;
import delta.got.Model.Cultures;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface Api {

    String BASE_URL = "https://api.got.show/api/";
    //https://api.got.show/api/characters/:name
//https://api.got.show/api/cultures/
    @GET
    Call<CharacterName> getName(@Url String url);

    @GET("cultures/")
    Call<List<Cultures>> getAllCultures();
}
