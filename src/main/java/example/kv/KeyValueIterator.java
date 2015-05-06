package example.kv;

import java.util.Iterator;
import java.util.Map.Entry;

public interface KeyValueIterator<Key,Value> extends Iterator<Entry<Key,Value>> {
}
