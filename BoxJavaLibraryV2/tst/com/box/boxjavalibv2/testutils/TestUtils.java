package com.box.boxjavalibv2.testutils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.box.boxjavalibv2.dao.BoxObject;
import com.box.boxjavalibv2.jsonparsing.BoxJSONParser;
import com.box.boxjavalibv2.jsonparsing.BoxResourceHub;
import com.box.restclientv2.exceptions.BoxRestException;

public class TestUtils {

    public static File createTempFile(String fileContent) throws IOException {
        File tmp = File.createTempFile("tmp", "tmp");
        FileUtils.writeStringToFile(tmp, fileContent);
        return tmp;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static BoxObject getFromJSON(String json, Class cls) throws BoxRestException {
        BoxJSONParser jsonParser = new BoxJSONParser(new BoxResourceHub());
        return jsonParser.parseIntoBoxObjectQuietly(json, cls);
    }
}
