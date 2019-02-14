package it.at7.gemini.api;

import it.at7.gemini.core.EntityRecord;
import it.at7.gemini.core.RecordConverters;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class EntityRecordApiTypeMessageConverter extends MappingJackson2HttpMessageConverter {

    @Override
    protected boolean canRead(MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return super.canWrite(mediaType) && GeminiWrappers.EntityRecordApiType.class.isAssignableFrom(clazz);
    }

    @Override
    protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        GeminiWrappers.EntityRecordApiType record = GeminiWrappers.EntityRecordApiType.class.cast(object);
        EntityRecord entityRecord = record.get();
        Map<String, Object> results = new HashMap<>();
        Map<String, Object> recordMap = RecordConverters.toJSONMap(entityRecord);
        results.put("meta", "__TODOOOO___meta_data_here____");
        results.put("data", recordMap);
        super.writeInternal(results, type, outputMessage);
    }
}
