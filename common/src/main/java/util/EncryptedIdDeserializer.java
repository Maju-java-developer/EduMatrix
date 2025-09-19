package util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class EncryptedIdDeserializer extends JsonDeserializer<Number> {

    private final Class<? extends Number> targetType;

    public EncryptedIdDeserializer(Class<? extends Number> targetType) {
        this.targetType = targetType;
    }

    @Override
    public Number deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String input = p.getValueAsString();
        if (input == null || input.isEmpty()) return null;

        Long decryptedId;

        if (Constants.DISABLE_ENCRYPTION_ID) {
            // 🔓 Bypass encryption: parse raw number directly
            try {
                decryptedId = Long.parseLong(input);
            } catch (NumberFormatException e) {
                throw new IOException("Invalid plain ID (not a number): " + input, e);
            }
        } else {
            // 🔐 Decrypt as usual
            try {
                decryptedId = EncryptionUtils.decryptId(input);
            } catch (Exception e) {
                throw new IOException("Failed to decrypt ID: " + input, e);
            }
        }

        // Convert to target type
        if (targetType.equals(Integer.class)) {
            return decryptedId.intValue();
        } else if (targetType.equals(Long.class)) {
            return decryptedId;
        } else {
            throw new IOException("Unsupported number type: " + targetType.getName());
        }
    }
}
