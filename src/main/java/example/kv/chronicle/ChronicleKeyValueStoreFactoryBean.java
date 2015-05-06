package example.kv.chronicle;

import static com.google.common.base.Preconditions.*;

import java.io.File;

import net.openhft.chronicle.map.ChronicleMap;
import net.openhft.chronicle.map.ChronicleMapBuilder;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

public class ChronicleKeyValueStoreFactoryBean implements FactoryBean<ChronicleKeyValueStore<?, ?>>, InitializingBean {

	private File backingFile;
	private Class<?> keyType;
	private Class<?> valueType;
	private ChronicleKeyValueStore<?, ?> chronicleKeyValueStore;
	
	@Override
	public ChronicleKeyValueStore<?, ?> getObject() throws Exception {
		return this.chronicleKeyValueStore;
	}

	@Override
	public Class<?> getObjectType() {
		return ChronicleKeyValueStore.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public File getBackingFile() {
		return backingFile;
	}

	public void setBackingFile(File backingFile) {
		this.backingFile = backingFile;
	}

	public Class<?> getKeyType() {
		return keyType;
	}

	public void setKeyType(Class<?> keyType) {
		this.keyType = keyType;
	}

	public Class<?> getValueType() {
		return valueType;
	}

	public void setValueType(Class<?> valueType) {
		this.valueType = valueType;
	}

	@Override
	public void afterPropertiesSet() throws Exception {

		checkNotNull(getKeyType(), "KeyType");
		checkNotNull(getKeyType(), "ValueType");

		ChronicleMapBuilder<?, ?> builder = ChronicleMapBuilder.of(getKeyType(), getValueType());

		ChronicleMap<?, ?> chronicleMap = backingFile == null ? //
		builder.create()
				: builder.createPersistedTo(backingFile);
		
		this.chronicleKeyValueStore = new ChronicleKeyValueStore<>(chronicleMap);
	}

}
