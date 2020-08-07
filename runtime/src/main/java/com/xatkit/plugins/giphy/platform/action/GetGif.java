package com.xatkit.plugins.giphy.platform.action;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.HttpRequest;
import com.xatkit.core.platform.action.RuntimeAction;
import com.xatkit.execution.StateContext;
import com.xatkit.plugins.giphy.platform.GiphyPlatform;
import lombok.NonNull;
import org.json.JSONObject;

/**
 * A {@link RuntimeAction} that retrieves a GIF from a given search string.
 * <p>
 * This class relies on the {@link GiphyPlatform} to access the Giphy API using the token stored in the
 * {@link org.apache.commons.configuration2.Configuration}.
 */
public class GetGif extends RuntimeAction<GiphyPlatform> {

    /**
     * The Giphy REST endpoint to retrieve GIFs from search strings.
     */
    private static String GIFS_SEARCH_URL = "https://api.giphy.com/v1/gifs/search";

    /**
     * The {@link String} used to search GIFs.
     */
    private String searchString;

    /**
     * Constructs a new {@link GetGif} with the provided {@code platform}, {@code context}, and {@code searchString}.
     * <p>
     * This constructor requires a valid Giphy API token in order to build the REST query used to retrieve GIF urls.
     *
     * @param platform     the {@link GiphyPlatform} containing this action
     * @param context      the {@link StateContext} associated to this action
     * @param searchString the {@link String} used to search GIFs
     * @throws NullPointerException if the provided {@code runtimePlatform}, {@code session}, or {@code searchString}
     *                              is {@code null}
     * @see GiphyPlatform#getGiphyToken()
     */
    public GetGif(@NonNull GiphyPlatform platform, @NonNull StateContext context, @NonNull String searchString) {
        super(platform, context);
        this.searchString = searchString;
    }

    @Override
    protected Object compute() throws Exception {
        HttpRequest request = Unirest.get(GIFS_SEARCH_URL)
                .queryString("api_key", this.runtimePlatform.getGiphyToken())
                .queryString("q", this.searchString);
        JsonNode jsonNode = request.asJson().getBody();
        JSONObject jsonObject = (JSONObject) jsonNode.getObject().getJSONArray("data").get(0);
        JSONObject images = jsonObject.getJSONObject("images");
        JSONObject original = images.getJSONObject("original");
        String url = original.getString("url");
        return url;
    }
}

