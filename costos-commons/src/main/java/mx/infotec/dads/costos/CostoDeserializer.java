package mx.infotec.dads.costos;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class CostoDeserializer extends StdDeserializer<Costo> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public CostoDeserializer() {
        this(null);
    }

    public CostoDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Costo deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        String id = node.get("_id").get("$oid").asText();
        ((ObjectNode) node).remove("_id");
        ((ObjectNode) node).put("id", id);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(node.toString(), Costo.class);
    }

}
