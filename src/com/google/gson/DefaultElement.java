package com.google.gson;

/**
 * I shouldn't do this.. but did it here for the sake of cleaner code to create empty Json elements when value is empty
 */
public class DefaultElement extends JsonElement {
    @Override
    public String getAsString() {
        return "";
    }
    public int getAsInt() {
        return 0;
    }
    public long getAsLong() {
        return 0;
    }
    public JsonArray getAsJsonArray(){
        return new JsonArray();
    }

    @Override
    JsonElement deepCopy() {
        throw new UnsupportedOperationException("");
    }
}
