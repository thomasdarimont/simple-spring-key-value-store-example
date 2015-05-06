package example.kv.chronicle;

import java.util.Iterator;
import java.util.Map.Entry;

import net.openhft.chronicle.map.ChronicleMap;
import example.kv.KeyValueIterator;
import example.kv.KeyValueStore;

public class ChronicleKeyValueStore<Key, Value> implements KeyValueStore<Key, Value> {

	private final ChronicleMap<Key, Value> chronicleMap;

	public ChronicleKeyValueStore(ChronicleMap<Key, Value> chronicleMap) {
		this.chronicleMap = chronicleMap;
	}

	@Override
	public Value get(Key key) {
		return chronicleMap.get(key);
	}

	@Override
	public Value put(Key key, Value value) {
		return chronicleMap.put(key, value);
	}

	@Override
	public Iterator<Key> keys() {
		return chronicleMap.keySet().iterator();
	}

	@Override
	public KeyValueIterator<Key, Value> entries() {

		return new KeyValueIterator<Key, Value>() {

			final Iterator<Entry<Key, Value>> entrySet = chronicleMap.entrySet().iterator();

			@Override
			public boolean hasNext() {
				return entrySet.hasNext();
			}

			@Override
			public Entry<Key, Value> next() {
				return entrySet.next();
			}
		};
	}

	@Override
	public long size() {
		return chronicleMap.longSize();
	}
}
