package example.kafka;

public interface ReadCallback {
	
	void doRead(Object key, Object value);
}
