package com.box.boxjavalibv2.requests.requestobjects;

import com.box.boxjavalibv2.dao.BoxEmail;
import com.box.boxjavalibv2.dao.BoxFolder;
import com.box.boxjavalibv2.jsonentities.BoxSharedLinkEntity;
import com.box.boxjavalibv2.jsonentities.MapJSONStringEntity;

public class BoxFolderRequestObject extends BoxItemRequestObject {

    public BoxFolderRequestObject() {
        super();
    }

    public BoxFolderRequestObject(BoxSharedLinkEntity sharedLink) {
        super(sharedLink);
    }

    public static BoxFolderRequestObject getRequestObject() {
        return new BoxFolderRequestObject();
    }

    public static BoxFolderRequestObject deleteSharedLinkRequestObject() {
        return new BoxFolderRequestObject(null);
    }

    public static BoxFolderRequestObject createSharedLinkRequestObject(BoxSharedLinkEntity sharedLink) {
        return new BoxFolderRequestObject(sharedLink);
    }

    public static BoxFolderRequestObject createFolderRequestObject(String name, String parentId) {
        BoxFolderRequestObject obj = new BoxFolderRequestObject();
        obj.setName(name);
        obj.setParent(parentId);
        return obj;
    }

    /**
     * Set the email-to-upload address for this folder.
     * 
     * @param access
     *            access level
     * @param email
     *            email address
     * @return
     */
    public void setUploadEmail(String access, String email) {
        MapJSONStringEntity entity = new MapJSONStringEntity();
        entity.put(BoxEmail.FIELD_ACCESS, access);
        entity.put(BoxEmail.FIELD_EMAIL, email);
        put(BoxFolder.FIELD_FOLDER_UPLOAD_EMAIL, entity);
    }
}
