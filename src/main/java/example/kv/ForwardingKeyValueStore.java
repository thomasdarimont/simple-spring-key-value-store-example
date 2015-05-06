package example.kv;

import java.util.Iterator;

public class ForwardingKeyValueStore<Key, Value> implements KeyValueStore<Key, Value>, Initializable, Flushable {

	private final KeyValueStore<Key, Value> targetStore;

	public ForwardingKeyValueStore(KeyValueStore<Key, Value> backingStore) {
		this.targetStore = backingStore;
	}

	@Override
	public Value get(Key key) {
		return targetStore.get(key);
	}

	@Override
	public Value put(Key key, Value value) {
		return targetStore.put(key, value);
	}

	@Override
	public void flush() {

		if (targetStore instanceof Flushable) {
			((Flushable)targetStore).flush();
		}
	}

	@Override
	public void initialize() {
		
		if(targetStore instanceof Initializable){
			((Initializable)targetStore).initialize();
		}
	}

	@Override
	public Iterator<Key> keys() {
		return targetStore.keys();
	}
	
	@Override
	public KeyValueIterator<Key, Value> entries() {
		return targetStore.entries();
	}

	@Override
	public long size() {
		return targetStore.size();
	}
}
