package com.box.boxjavalibv2.requests;

import com.box.boxjavalibv2.interfaces.IBoxJSONParser;
import com.box.boxjavalibv2.requests.requestobjects.BoxOAuthRequestObject;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.requests.DefaultBoxRequest;

public class RevokeOAuthRequest extends DefaultBoxRequest {

    public static final String URI = "/oauth2/revoke";

    public RevokeOAuthRequest(final IBoxConfig config, final IBoxJSONParser parser, final BoxOAuthRequestObject requestObject) throws BoxRestException {
        super(config, parser, getUri(), RestMethod.POST, requestObject);
    }

    public static String getUri() {
        return URI;
    }

    @Override
    public String getScheme() {
        return getConfig().getOAuthUrlScheme();
    }

    @Override
    public String getAuthority() {
        return getConfig().getOAuthUrlAuthority();
    }

    @Override
    public String getApiUrlPath() {
        return getConfig().getOAuthApiUrlPath();
    }
}
