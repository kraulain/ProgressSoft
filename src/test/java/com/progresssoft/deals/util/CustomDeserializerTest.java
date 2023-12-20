package com.progresssoft.deals.util;

import com.progresssoft.deals.model.FXDeal;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
@SpringBootTest
public class CustomDeserializerTest {
    @Autowired
    private CustomDeserializer customDeserializer;

    @Test
    public void givenAValidJsonString_whenCustomDeserializer_thenReturnFXDeal() {
        //Given
        String sampleMessage = """
                {"schema":{"type":"struct","fields":[{"type":"struct","fields":[{"type":"int32","optional":false,"default":0,"field":"id"},{"type":"string","optional":false,"field":"from_currency"},{"type":"string","optional":false,"field":"to_currency"},{"type":"int64","optional":false,"name":"io.debezium.time.MicroTimestamp","version":1,"default":0,"field":"created_at"},{"type":"int64","optional":false,"field":"amount"}],"optional":true,"name":"sourcedb.public.fxdeal.Value","field":"before"},{"type":"struct","fields":[{"type":"int32","optional":false,"default":0,"field":"id"},{"type":"string","optional":false,"field":"from_currency"},{"type":"string","optional":false,"field":"to_currency"},{"type":"int64","optional":false,"name":"io.debezium.time.MicroTimestamp","version":1,"default":0,"field":"created_at"},{"type":"int64","optional":false,"field":"amount"}],"optional":true,"name":"sourcedb.public.fxdeal.Value","field":"after"},{"type":"struct","fields":[{"type":"string","optional":false,"field":"version"},{"type":"string","optional":false,"field":"connector"},{"type":"string","optional":false,"field":"name"},{"type":"int64","optional":false,"field":"ts_ms"},{"type":"string","optional":true,"name":"io.debezium.data.Enum","version":1,"parameters":{"allowed":"true,last,false,incremental"},"default":"false","field":"snapshot"},{"type":"string","optional":false,"field":"db"},{"type":"string","optional":true,"field":"sequence"},{"type":"string","optional":false,"field":"schema"},{"type":"string","optional":false,"field":"table"},{"type":"int64","optional":true,"field":"txId"},{"type":"int64","optional":true,"field":"lsn"},{"type":"int64","optional":true,"field":"xmin"}],"optional":false,"name":"io.debezium.connector.postgresql.Source","field":"source"},{"type":"string","optional":false,"field":"op"},{"type":"int64","optional":true,"field":"ts_ms"},{"type":"struct","fields":[{"type":"string","optional":false,"field":"id"},{"type":"int64","optional":false,"field":"total_order"},{"type":"int64","optional":false,"field":"data_collection_order"}],"optional":true,"name":"event.block","version":1,"field":"transaction"}],"optional":false,"name":"sourcedb.public.fxdeal.Envelope","version":1},"payload":{"before":null,"after":{"id":4,"from_currency":"GBP","to_currency":"JPY","created_at":1703036026011470,"amount":615},"source":{"version":"2.4.2.Final","connector":"postgresql","name":"sourcedb","ts_ms":1703036026012,"snapshot":"false","db":"sourcedb","sequence":"[\\"27561472\\",\\"27561472\\"]","schema":"public","table":"fxdeal","txId":751,"lsn":27561472,"xmin":null},"op":"c","ts_ms":1703036026306,"transaction":null}}
                """;
        //When
        Optional<FXDeal> fxDeal = customDeserializer.messageToDeal(sampleMessage);

        //Then
        assertThat(fxDeal).isPresent();
        assertThat(fxDeal.get().getId()).isEqualTo(4);
    }

    @Test
    public void givenAnInvalidJsonString_whenCustomDeserializer_thenReturnEmpty() {
        //Given
        String sampleMessage = """
                {"payload": {
                    "before": null,
                    "after": {
                      "id": 4,
                      "from_currency": "GBP",
                      "to_currency": "JPY",
                    },
                 "source": {
                      "version": "2.4.2.Final",
                      "connector": "postgresql",
                      "name": "sourcedb",
                      "ts_ms": 1703036026012,
                      "snapshot": "false",
                      "db": "sourcedb"}}
                """;
        //When
        Optional<FXDeal> fxDeal = customDeserializer.messageToDeal(sampleMessage);

        //Then
        assertThat(fxDeal).isEmpty();
    }
}
