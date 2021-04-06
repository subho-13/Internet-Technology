package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Json {
	public static <T> T getObject(BufferedReader reader, Class<T> cls) {
		final Gson gson = new GsonBuilder().create();

		String line;
		final StringBuilder buffer = new StringBuilder();

		try {
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
				buffer.append(System.lineSeparator());
			}
		} catch (final IOException e) {
			e.printStackTrace();
		}

		System.out.println(buffer.toString());

		return gson.fromJson(buffer.toString(), cls);
	}

	public static <T> void writeObject(PrintWriter writer, T t) {
		final Gson gson = new GsonBuilder().create();

		final String string = gson.toJson(t);
		writer.print(string);
		writer.flush();
	}
}
