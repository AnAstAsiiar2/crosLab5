package org.example.adapter;

import com.google.gson.*;
import org.example.data.Post;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class ClassDeserializerAdapter implements JsonDeserializer<Post> {
    private final String typeName;
    private final Gson gson;
    private final Map<String, Class<? extends Post>> classTypeRegistry;

    public ClassDeserializerAdapter(String typeName) {
        this.typeName = typeName;
        gson = new Gson();
        classTypeRegistry = new HashMap<>();
    }

    public void registerClassType(String classTypeName, Class<? extends Post> classType) {
        classTypeRegistry.put(classTypeName, classType);
    }

    @Override
    public Post deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement typeElement = jsonObject.get(typeName);
        String method = typeElement.getAsString();
        Class<? extends Post> classType = classTypeRegistry.get(method);
        if (classType == null) {
            throw new JsonParseException("Unknown type: " + method);
        }
        return gson.fromJson(json, classType);
    }
}
