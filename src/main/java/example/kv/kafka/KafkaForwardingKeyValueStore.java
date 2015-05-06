package example.kv.kafka;

import example.kafka.KafkaOperations;
import example.kv.ForwardingKeyValueStore;
import example.kv.KeyValueStore;

public class KafkaForwardingKeyValueStore<Key, Value> extends ForwardingKeyValueStore<Key, Value> {

	private final KafkaOperations kafkaOperations;

	public KafkaForwardingKeyValueStore(KeyValueStore<Key, Value> backingStore, KafkaOperations kafkaOperations) {
		super(backingStore);
		this.kafkaOperations = kafkaOperations;
	}

	@Override
	public Value put(Key key, Value value) {
		
		kafkaOperations.write(key, value);
		
		return super.put(key, value);
	}
	
	@Override
	public void initialize() {
		super.initialize();
		
		//load data from kafka topic
	}
}
