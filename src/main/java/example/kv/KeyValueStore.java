package example.kv;

import java.util.Iterator;

public interface KeyValueStore<Key, Value> {
	
	Value get(Key key);
	
	Value put(Key key, Value value);
	
	long size();
	
	Iterator<Key> keys();
	
	KeyValueIterator<Key, Value> entries();
}
