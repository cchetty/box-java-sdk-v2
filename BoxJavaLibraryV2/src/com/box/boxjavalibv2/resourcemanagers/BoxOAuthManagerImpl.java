package com.box.boxjavalibv2.resourcemanagers;

import com.box.boxjavalibv2.IBoxConfig;
import com.box.boxjavalibv2.dao.BoxOAuthToken;
import com.box.boxjavalibv2.dao.BoxResourceType;
import com.box.boxjavalibv2.exceptions.AuthFatalFailureException;
import com.box.boxjavalibv2.exceptions.BoxServerException;
import com.box.boxjavalibv2.jsonparsing.IBoxJSONParser;
import com.box.boxjavalibv2.jsonparsing.IBoxResourceHub;
import com.box.boxjavalibv2.requests.CreateOAuthRequest;
import com.box.boxjavalibv2.requests.RefreshOAuthRequest;
import com.box.boxjavalibv2.requests.RevokeOAuthRequest;
import com.box.boxjavalibv2.requests.requestentities.BoxOAuthRequestEntity;
import com.box.boxjavalibv2.requests.requestobjects.BoxOAuthRequestObject;
import com.box.restclientv2.IBoxRESTClient;
import com.box.restclientv2.exceptions.BoxRestException;

/**
 * API for OAuth. Full details about the Box API can be found at <a href="http://developers.box.com/oauth/">http://developers.box.com/docs</a>
 */
public class BoxOAuthManagerImpl extends AbstractBoxResourceManager implements IBoxOAuthManager {

    /**
     * Constructor.
     * 
     * @param config
     *            config
     * @param resourceHub
     *            resource hub
     * @param parser
     *            json parser
     * @param restClient
     *            REST client to make api calls.
     */
    public BoxOAuthManagerImpl(final IBoxConfig config, IBoxResourceHub resourceHub, final IBoxJSONParser parser, final IBoxRESTClient restClient) {
        super(config, resourceHub, parser, null, restClient);
    }

    /**
     * @param code
     *            The authorization code you retrieved previously used to create OAuth.
     * @param clientId
     *            client id
     * @param clientSecret
     *            client secret
     * @param redirectUri
     *            optional, required only if a redirect URI is not configured at <a href="http://box.com/developers/services">Box Developers Services</a>, use
     *            null if don't want to supply this field.
     */
    @Override
    public BoxOAuthToken createOAuth(final String code, final String clientId, final String clientSecret, final String redirectUrl) throws BoxRestException,
        BoxServerException, AuthFatalFailureException {
        BoxOAuthRequestEntity entity = BoxOAuthRequestEntity.createOAuthRequestEntity(code, clientId, clientSecret, redirectUrl);
        BoxOAuthRequestObject obj = BoxOAuthRequestObject.getRequestObject(entity);
        return createOAuth(obj);
    }

    /**
     * 
     * @param code
     *            The authorization code you retrieved previously used to create OAuth.
     * @param clientId
     *            client id
     * @param clientSecret
     *            client secret
     * @param redirectUri
     *            optional, required only if a redirect URI is not configured at <a href="http://box.com/developers/services">Box Developers Services</a>, use
     *            null if don't want to supply this field.
     * @param deviceId
     *            device id
     * @param deviceName
     *            device name
     */
    @Override
    public BoxOAuthToken createOAuth(final String code, final String clientId, final String clientSecret, final String redirectUrl, final String deviceId,
        final String deviceName) throws BoxRestException, BoxServerException, AuthFatalFailureException {
        BoxOAuthRequestEntity entity = BoxOAuthRequestEntity.createOAuthRequestEntity(code, clientId, clientSecret, redirectUrl);
        entity.setDevice(deviceId, deviceName);
        BoxOAuthRequestObject obj = BoxOAuthRequestObject.getRequestObject(entity);

        return createOAuth(obj);
    }

    public BoxOAuthToken createOAuth(BoxOAuthRequestObject requestObject) throws BoxRestException, BoxServerException, AuthFatalFailureException {
        CreateOAuthRequest request = new CreateOAuthRequest(getConfig(), getJSONParser(), requestObject);
        return (BoxOAuthToken) getResponseAndParseAndTryCast(request, BoxResourceType.OAUTH_DATA, getJSONParser());
    }

    @Override
    public BoxOAuthToken refreshOAuth(String refreshToken, String clientId, String clientSecret, String deviceId, String deviceName) throws BoxRestException,
        BoxServerException, AuthFatalFailureException {
        BoxOAuthRequestEntity entity = BoxOAuthRequestEntity.refreshOAuthRequestEntity(refreshToken, clientId, clientSecret);
        entity.setDevice(deviceId, deviceName);
        BoxOAuthRequestObject obj = BoxOAuthRequestObject.getRequestObject(entity);

        return createOAuth(obj);
    }

    @Override
    public BoxOAuthToken refreshOAuth(String refreshToken, String clientId, String clientSecret) throws BoxRestException, BoxServerException,
        AuthFatalFailureException {
        BoxOAuthRequestEntity entity = BoxOAuthRequestEntity.refreshOAuthRequestEntity(refreshToken, clientId, clientSecret);
        BoxOAuthRequestObject obj = BoxOAuthRequestObject.getRequestObject(entity);

        return createOAuth(obj);
    }

    @Override
    public void revokeOAuth(String accessToken, String clientId, String clientSecret) throws BoxServerException, BoxRestException, AuthFatalFailureException {
        BoxOAuthRequestEntity entity = BoxOAuthRequestEntity.revokeOAuthRequestEntity(accessToken, clientId, clientSecret);
        revokeOAuth(BoxOAuthRequestObject.getRequestObject(entity));
    }

    public BoxOAuthToken refreshOAuth(final BoxOAuthRequestObject requestObject) throws BoxRestException, BoxServerException, AuthFatalFailureException {
        RefreshOAuthRequest request = new RefreshOAuthRequest(getConfig(), getJSONParser(), requestObject);
        return (BoxOAuthToken) getResponseAndParseAndTryCast(request, BoxResourceType.OAUTH_DATA, getJSONParser());
    }

    public void revokeOAuth(final BoxOAuthRequestObject requestObject) throws BoxServerException, BoxRestException, AuthFatalFailureException {
        RevokeOAuthRequest request = new RevokeOAuthRequest(getConfig(), getJSONParser(), requestObject);
        executeRequestWithNoResponseBody(request);
    }
}