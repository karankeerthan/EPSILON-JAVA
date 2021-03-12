package com.epsilon.training.annotations.processors;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.epsilon.training.annotations.JsonIgnored;
import com.epsilon.training.annotations.JsonProperty;
import com.epsilon.training.annotations.JsonSerializable;
import com.epsilon.training.exceptions.NotJsonSerializableException;

public class JsonSerializer {

	public String serialize(Object obj) {
		if (obj == null) {
			throw new NotJsonSerializableException("Cannot serialize a null object");
		}

		Class<?> cls = obj.getClass();
		System.out.println("cls =" + cls);

		if (!cls.isAnnotationPresent(JsonSerializable.class)) {
			throw new NotJsonSerializableException("The class " + cls.getName() + "does not have @JsonSerializable");
		}

		List<String> kvList = new ArrayList<>();
		for (Field f : cls.getDeclaredFields()) {
			if (!f.isAnnotationPresent(JsonIgnored.class)) {
				try {

					String label = f.getName();

					JsonProperty ann = f.getAnnotation(JsonProperty.class);
					if (ann != null) {
						label = ann.label();
						if (label == null || label.trim().equals("")) {
							label = f.getName();
						}
					}

					f.setAccessible(true);

					if (label.equals("address")) {
						JsonSerializer js = new JsonSerializer();
						String json = js.serialize(f.get(obj));
						

						kvList.add(label + " : " + json);
						
					}

					String kv = String.format("\"%s\": \"%s\"", label, f.get(obj));
					kvList.add(kv);
				} catch (Exception e) {
					System.err.println("Error for field" + f.getName() + "-" + e.getMessage());
				}

			}
		}

		StringBuffer sb = new StringBuffer(1000);
		sb.append("{");
		for (int i = 0, j = kvList.size()-1; i < j; i++) {
			sb.append(kvList.get(i));
			sb.append(", ");
		}

		
		sb.append("}");

		return sb.toString();
	}

}
