package com.epsilon.training.annotations.processors;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.epsilon.training.annotations.JsonIgnored;
import com.epsilon.training.annotations.JsonProperty;
import com.epsilon.training.annotations.JsonSerializable;
import com.epsilon.training.annotations.XMLSerializable;
import com.epsilon.training.exceptions.NotXMLSerializableException;
import com.epsilon.training.exceptions.NotJsonSerializableException;

public class XMLserializer {

	public String serialize(Object obj) {
		if (obj == null) {
			throw new NotXMLSerializableException("Cannot serialize a null object");
		}

		Class<?> cls = obj.getClass();

		if (!cls.isAnnotationPresent(JsonSerializable.class)) {
			throw new NotJsonSerializableException("The class " + cls.getName() + "does not have @JsonSerializable");
		}

		List<String> kvList = new ArrayList<>();
		if(!cls.getSimpleName().equals("Address")) {
			String kv1 = String.format("< %s>", cls.getSimpleName());
			kvList.add(kv1);	
		}
		

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
					Class<?> c = f.get(obj).getClass();

					if (c.isAnnotationPresent(XMLSerializable.class)) {
						XMLserializer xm = new XMLserializer();

						String xml = xm.serialize(f.get(obj));

						kvList.add("\t <" + label + "> \n" + xml + "</"+label+">");

					} else {
						
						String kv = String.format("\t     <%s> %s </%s>", label, f.get(obj), label);
						kvList.add(kv);
					}

				}

				catch (Exception e) {
					System.err.println("Error for field" + f.getName() + "-" + e.getMessage());
				}

			}
		}

		StringBuffer sb = new StringBuffer(1000);
		
		for (int i = 0, j = kvList.size(); i < j; i++) {
			sb.append("" + kvList.get(i));
			sb.append("\n ");
			
		}
		
		if(!cls.getSimpleName().equals("Address")) {
			sb.append("</" +cls.getSimpleName()+ ">");
		}
		

		return sb.toString();
	}

}
