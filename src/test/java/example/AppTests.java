package example;

import static org.mockito.Mockito.*;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import example.kafka.KafkaOperations;
import example.kv.KeyValueStore;
import example.kv.chronicle.ChronicleKeyValueStore;
import example.kv.chronicle.ChronicleKeyValueStoreFactoryBean;
import example.kv.kafka.KafkaForwardingKeyValueStore;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class AppTests {

	@Test
	public void kafkaForwardingKeyValueStoreExample() throws Exception {

		KafkaOperations kafkaOperations = mock(KafkaOperations.class, withSettings().verboseLogging());

		ChronicleKeyValueStoreFactoryBean chronicleKeyValueStoreFactoryBean = new ChronicleKeyValueStoreFactoryBean(); 
		chronicleKeyValueStoreFactoryBean.setBackingFile(new File("/tmp/xd-stream-data.dat"));
		chronicleKeyValueStoreFactoryBean.setKeyType(String.class);
		chronicleKeyValueStoreFactoryBean.setValueType(Long.class);
		chronicleKeyValueStoreFactoryBean.afterPropertiesSet();
		
		@SuppressWarnings("unchecked")
		ChronicleKeyValueStore<String, Long> backingMap = (ChronicleKeyValueStore<String, Long>)chronicleKeyValueStoreFactoryBean.getObject();

		KafkaForwardingKeyValueStore<String, Long> kafkaForwardingStore = //
		new KafkaForwardingKeyValueStore<String, Long>(backingMap, kafkaOperations);

		// allow explicit initialization
		kafkaForwardingStore.initialize();

		// cast to user API
		KeyValueStore<String, Long> kvStore = (KeyValueStore<String, Long>) kafkaForwardingStore;

		System.out.println(kvStore.get("foo"));

		kvStore.put("foo", 42L);

		System.out.println(kvStore.get("foo"));
	}
}
