package com.xatkit.plugins.giphy.platform;

import com.xatkit.core.XatkitCore;
import com.xatkit.core.platform.RuntimePlatform;
import com.xatkit.core.platform.action.RuntimeAction;
import com.xatkit.core.platform.action.RuntimeActionResult;
import com.xatkit.execution.StateContext;
import com.xatkit.plugins.giphy.platform.action.GetGif;
import lombok.NonNull;
import org.apache.commons.configuration2.Configuration;
import org.jetbrains.annotations.NonNls;

import static fr.inria.atlanmod.commons.Preconditions.checkArgument;

/**
 * A {@link RuntimePlatform} class that connects and interacts with the Giphy API.
 */
public class GiphyPlatform extends RuntimePlatform {

    /**
     * The {@link Configuration} key to store the Giphy API token.
     */
    public static String GIPHY_TOKEN_KEY = "xatkit.giphy.token";

    /**
     * The Giphy API token used to initialize this class.
     */
    private String giphyToken;

    /**
     * {@inheritDoc}
     * <p>
     * This method checks that the provided {@code configuration} contains a Giphy API token.
     *
     * @throws IllegalArgumentException if the provided {@code configuration} does not contain a Giphy API token
     */
    @Override
    public void start(XatkitCore xatkitCore, Configuration configuration) {
        super.start(xatkitCore, configuration);
        checkArgument(configuration.containsKey(GIPHY_TOKEN_KEY), "Cannot construct a %s, please ensure that the " +
                        "configuration contains a valid Giphy API token (configuration key: %s)",
                this.getClass().getSimpleName(), GIPHY_TOKEN_KEY);
        giphyToken = configuration.getString(GIPHY_TOKEN_KEY);
    }

    /**
     * Returns the URL of the first GIF returned by the Giphy API and matching the provided {@code searchString}.
     *
     * @param context      the current {@link StateContext}
     * @param searchString the query to send to the Giphy API
     * @return the URL of the GIF
     */
    public @NonNull String getGif(@NonNull StateContext context, @NonNull String searchString) {
        GetGif action = new GetGif(this, context, searchString);
        RuntimeActionResult result = this.executeRuntimeAction(action);
        return (String) result.getResult();
    }

    /**
     * Returns the Giphy API token.
     *
     * @return the Giphy API token
     */
    public String getGiphyToken() {
        return this.giphyToken;
    }
}
