package mx.infotec.dads.kukulkan.costos;

import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import static org.hamcrest.Matchers.is;
import static org.springframework.cloud.stream.test.matcher.MessageQueueMatcher.receivesPayloadThat;

import java.io.IOException;

/**
 * 
 * @author Roberto Villarejo Martínez
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
@SpringBootTest
public abstract class CostosProcessorApplicationTests {

    @Autowired
    protected Processor channels;

    @Autowired
    protected MessageCollector collector;

    public static class TestCostoDeserializer extends CostosProcessorApplicationTests {

        @Test
        public void testCostoIdDeserializer() throws JsonParseException, JsonMappingException, IOException {
            ObjectMapper mapper = new ObjectMapper();
            CostoObjectId costo = mapper.readValue(
                    "{\"_id\" : { \"$oid\" : \"5be8c487b027b84fa3d94362\" },\"processed\":true,\"monto\":0.0,\"porcentaje\":0.0,\"anio\":0,\"mes\":0,\"fechaRegistro\":null,\"area\":0,\"proyectoOperativo\":null,\"proyectoPresupuestal\":null,\"servicio\":null,\"tipoCosto\":null,\"partida\":0,\"proveedor\":null,\"user\":null,\"subpartida\":null,\"subSubPartida\":null,\"numeroFactura\":null}",
                    CostoObjectId.class);

            System.out.println("TEST:" + mapper.writeValueAsString(costo));
        }

        @Test
        public void test() {
            channels.input().send(new GenericMessage<Object>(
                    "{\"_id\" : { \"$oid\" : \"5be8c487b027b84fa3d94362\" },\"processed\":false,\"monto\":0.0,\"porcentaje\":16.0,\"anio\":0,\"mes\":0,\"fechaRegistro\":null,\"area\":0,\"proyectoOperativo\":null,\"proyectoPresupuestal\":null,\"servicio\":null,\"tipoCosto\":null,\"partida\":0,\"proveedor\":null,\"user\":null,\"subpartida\":null,\"subSubPartida\":null,\"numeroFactura\":null}"
                            .getBytes()));
            // assertThat(collector.forChannel(channels.output()),
            // receivesPayloadThat(is()));
        }
    }

}
