package com.box.boxjavalibv2.requests.requestentities;

import com.box.boxjavalibv2.dao.BoxFile;
import com.box.boxjavalibv2.jsonentities.BoxSharedLinkEntity;

// CHECKSTYLE:OFF
/**
 * Entity for shared link.
 */
public class BoxSharedLinkRequestEntity extends BoxDefaultRequestEntity {

    protected BoxSharedLinkRequestEntity() {
    }

    public static BoxSharedLinkRequestEntity deleteSharedLinkRequestEntity() {
        BoxSharedLinkRequestEntity entity = new BoxSharedLinkRequestEntity();
        entity.setSharedLink(null);
        return entity;
    }

    public static BoxSharedLinkRequestEntity createSharedLinkRequestEntity(BoxSharedLinkEntity sharedLink) {
        BoxSharedLinkRequestEntity entity = new BoxSharedLinkRequestEntity();
        entity.setSharedLink(sharedLink);
        return entity;
    }

    /**
     * Set shared link. You can set this to null in a update file/folder info request in order to delete shared link in the file object.
     * 
     * @param sharedLink
     * @return
     */
    protected void setSharedLink(BoxSharedLinkEntity sharedLink) {
        put(BoxFile.FIELD_SHARED_LINK, sharedLink);
    }
}