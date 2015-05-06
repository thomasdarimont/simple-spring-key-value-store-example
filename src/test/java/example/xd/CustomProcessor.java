package example.xd;

import org.springframework.beans.factory.annotation.Autowired;

import example.kv.KeyValueStore;

public class CustomProcessor {

	@Autowired KeyValueStore<String, Long> keyValueStore;

	public Object process(Object input) {

		Object result = null; // process input
		// update module state

		keyValueStore.put(getModuleId(), getModuleState());

		return result;
	}

	String getModuleId() {
		return null;
	}

	Long getModuleState() {
		return null;
	}
}
