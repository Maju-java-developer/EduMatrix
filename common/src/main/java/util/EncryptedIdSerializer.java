package util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public class EncryptedIdSerializer extends JsonSerializer<Number> {
    @Override
    public void serialize(Number value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) {
            gen.writeNull();
            return;
        }

        if (Constants.DISABLE_ENCRYPTION_ID) {
            // 🔓 No encryption, write plain number
            gen.writeNumber(value.longValue());
        } else {
            // 🔐 Encrypted
            gen.writeString(EncryptionUtils.encryptId(value.longValue()));
        }
    }
}
