package example.kafka;

public interface KafkaOperations {

	void write(Object key, Object value);

	void readAll(ReadCallback callback);
}
