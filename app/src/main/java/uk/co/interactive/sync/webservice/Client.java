package uk.co.interactive.sync.webservice;

import com.squareup.okhttp.OkHttpClient;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.http.GET;
import retrofit.http.Query;
import uk.co.interactive.sync.models.Feed;
import uk.co.interactive.sync.utils.Constants;

/**
 * Created by javiergonzalezcabezas on 9/6/15.
 */
public class Client {
    public interface ClientInterface{
        @GET("/")
        void getQuestions( @Query("nojsoncallback") String nojsoncallback, @Query("format") String format, @Query("tags") String tag, Callback<Feed> callback);
    }

    public static ClientInterface initRestAdapter()
    {
        OkHttpClient client = new OkHttpClient();

        return (ClientInterface) new RestAdapter.Builder()
                .setClient(new OkClient(client))
                .setEndpoint(Constants.URL)
                .build()
                .create(ClientInterface.class);
    }
}
