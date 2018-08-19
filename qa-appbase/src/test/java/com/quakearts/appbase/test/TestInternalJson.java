package com.quakearts.appbase.test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Iterator;

import org.junit.Test;

import com.quakearts.appbase.internal.json.Json;
import com.quakearts.appbase.internal.json.JsonArray;
import com.quakearts.appbase.internal.json.JsonObject;
import com.quakearts.appbase.internal.json.JsonObject.Member;
import com.quakearts.appbase.internal.json.JsonValue;
import com.quakearts.appbase.internal.json.WriterConfig;

public class TestInternalJson {

	@Test
	public void testValueInt() {
		JsonValue value = Json.value(10);
		assertThat(value.asInt(), is(10));
	}

	@Test
	public void testValueLong() {
		JsonValue value = Json.value(10l);
		assertThat(value.asLong(), is(10l));
	}

	@Test
	public void testValueFloat() {
		JsonValue value = Json.value(10f);
		assertThat(value.asFloat(), is(10f));
	}

	@Test
	public void testValueDouble() {
		JsonValue value = Json.value(10d);
		assertThat(value.asDouble(), is(10d));
	}

	@Test
	public void testValueString() {
		JsonValue value = Json.value("10");
		assertThat(value.asString(), is("10"));
	}

	@Test
	public void testValueBoolean() {
		JsonValue value = Json.value(true);
		assertThat(value.asBoolean(), is(true));
	}

	@Test
	public void testArray() throws Exception {
		JsonValue arrayValue = Json.array();
		assertThat(arrayValue.isArray(), is(true));
		assertTrue(arrayValue instanceof JsonArray);
		JsonArray array = (JsonArray) arrayValue;
		assertThat(array.isEmpty(), is(true));
		array.add(true).add(10d).add(10f).add(10).add(Json.value("9")).add(10l).add("10");
		assertThat(array.get(0).asBoolean(), is(true));
		assertThat(array.get(1).asDouble(), is(10d));
		assertThat(array.get(2).asFloat(), is(10f));
		assertThat(array.get(3).asInt(), is(10));
		assertThat(array.get(4), is(Json.value("9")));
		assertThat(array.get(5).asLong(), is(10l));
		assertThat(array.get(6), is(Json.value("10")));
		
		array.set(0, false).set(1, 11d).set(2, 12f).set(3, 13)
		.set(4, Json.value("Test")).set(5, 14l).set(6, "Test2");

		assertThat(array.get(0).asBoolean(), is(false));
		assertThat(array.get(1).asDouble(), is(11d));
		assertThat(array.get(2).asFloat(), is(12f));
		assertThat(array.get(3).asInt(), is(13));
		assertThat(array.get(4), is(Json.value("Test")));
		assertThat(array.get(5).asLong(), is(14l));
		assertThat(array.get(6), is(Json.value("Test2")));
		
		assertThat(array.isEmpty(), is(false));
		assertThat(array.size(), is(7));
		array.remove(4);
		assertThat(array.size(), is(6));
		
		JsonArray array2 = new JsonArray(array);
		assertThat(array, is(array));
		assertThat(array2, is(array));

		StringWriter stringWriter = new StringWriter();
		array.writeTo(stringWriter, WriterConfig.PRETTY_PRINT);
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testArrayIteratorRemove() throws Exception {
		Iterator<JsonValue> iterator = Json.array(1,2,3,4)
		.iterator();
		iterator.next();
		iterator.remove();
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testArrayValues() throws Exception {
		Json.array(1,2,3,4)
			.values().add(Json.value(true));
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testUnmodifiableArray() throws Exception {
		JsonArray.unmodifiableArray(Json.array(1,2,3,4))
			.add(true);
	}
	
	@Test
	public void testArrayIntArray() {
		JsonArray array = Json.array(1,2,3,4);
		assertThat(array.get(0).asInt(), is(1));
		assertThat(array.get(1).asInt(), is(2));
		assertThat(array.get(2).asInt(), is(3));
		assertThat(array.get(3).asInt(), is(4));
		//unmodifiableArray
	}

	@Test
	public void testArrayLongArray() {
		JsonArray array = Json.array(1l,2l,3l,4l);
		assertThat(array.get(0).asLong(), is(1l));
		assertThat(array.get(1).asLong(), is(2l));
		assertThat(array.get(2).asLong(), is(3l));
		assertThat(array.get(3).asLong(), is(4l));
	}

	@Test
	public void testArrayFloatArray() {
		JsonArray array = Json.array(1f,2f,3f,4f);
		assertThat(array.get(0).asFloat(), is(1f));
		assertThat(array.get(1).asFloat(), is(2f));
		assertThat(array.get(2).asFloat(), is(3f));
		assertThat(array.get(3).asFloat(), is(4f));
	}

	@Test
	public void testArrayDoubleArray() {
		JsonArray array = Json.array(1d,2d,3d,4d);
		assertThat(array.get(0).asDouble(), is(1d));
		assertThat(array.get(1).asDouble(), is(2d));
		assertThat(array.get(2).asDouble(), is(3d));
		assertThat(array.get(3).asDouble(), is(4d));
	}

	@Test
	public void testArrayBooleanArray() {
		JsonArray array = Json.array(true,false,false,true);
		assertThat(array.get(0).asBoolean(), is(true));
		assertThat(array.get(1).asBoolean(), is(false));
		assertThat(array.get(2).asBoolean(), is(false));
		assertThat(array.get(3).asBoolean(), is(true));
	}

	@Test
	public void testArrayStringArray() {
		JsonArray array = Json.array("1","2","3","4");
		assertThat(array.get(0).asString(), is("1"));
		assertThat(array.get(1).asString(), is("2"));
		assertThat(array.get(2).asString(), is("3"));
		assertThat(array.get(3).asString(), is("4"));
	}

	@Test
	public void testObject() throws Exception {
		JsonValue objectValue = Json.object();
		assertThat(objectValue.isObject(), is(true));
		assertTrue(objectValue instanceof JsonObject);
		
		JsonObject object = (JsonObject) objectValue;
		assertThat(object.isEmpty(), is(true));
		object.add("boolean",true).add("double",10d).add("float",10f).add("int",10)
			.add("string1",Json.value("9"))
			.add("long",10l).add("string2","10");

		assertThat(object.isEmpty(), is(false));

		assertThat(object.getBoolean("boolean", false), is(true));
		assertThat(object.getDouble("double", 0), is(10d));
		assertThat(object.getFloat("float", 0), is(10f));
		assertThat(object.getInt("int", 0), is(10));
		assertThat(object.get("string1"), is(Json.value("9")));
		assertThat(object.getLong("long", 0), is(10l));
		assertThat(object.getString("string2",""), is("10"));
		
		object.set("boolean",false).set("double",11d).set("float",12f).set("int",13)
		.set("string1",Json.value("Test1"))
		.set("long", 14l).set("string2","Test2");
		
		assertThat(object.getBoolean("boolean", true), is(false));
		assertThat(object.getDouble("double", 0), is(11d));
		assertThat(object.getFloat("float", 0), is(12f));
		assertThat(object.getInt("int", 0), is(13));
		assertThat(object.get("string1"), is(Json.value("Test1")));
		assertThat(object.getLong("long", 0), is(14l));
		assertThat(object.getString("string2",""), is("Test2"));
		
		assertThat(object.size(),is(7));

		object.remove("string1");
		assertThat(object.size(),is(6));
		
		object.set("string1", "Test3");
		assertThat(object.size(),is(7));
		assertThat(object.get("string1"), is(Json.value("Test3")));
		
		JsonObject object2 = new JsonObject(object);
		assertThat(object, is(object));
		assertThat(object2, is(object));
		
		StringWriter stringWriter = new StringWriter();
		object.writeTo(stringWriter, WriterConfig.PRETTY_PRINT);
		
		JsonObject object3 = new JsonObject()
				.add("hello", "world");
		
		object3.merge(new JsonObject()
				.add("hi", "earth"));		

		assertThat(object3.get("hello"), is(Json.value("world")));
		assertThat(object3.get("hi"), is(Json.value("earth")));
	}
	
	@Test
	public void testJsonReaderParser() throws Exception {
		JsonValue jsonValue = Json
				.parse(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("test.json")));
		
		assertThat(jsonValue.isObject(), is(true));
		
		JsonObject object = (JsonObject) jsonValue;
		assertThat(object.size(), is(8));
		assertThat(object.getBoolean("boolean", false), is(true));
		assertThat(object.getDouble("double", 0), is(1d));
		assertThat(object.getFloat("float", 0), is(1e10f));
		assertThat(object.getString("string", ""), is("string"));
		assertThat(object.get("object"), is(new JsonObject().add("hello", "world")
				.add("false", false)));
		assertThat(object.get("array"), is(new JsonArray().add("hello")
				.add(1).add(true)));
		assertThat(object.get("null").isNull(), is(true));
		assertThat(object.getString("escape", ""), is("\u0a0f\t\b\f\r\n"));
		
		StringWriter stringWriter = new StringWriter();
		object.writeTo(stringWriter);
	}
	
	@Test
	public void testJsonStringParser() throws Exception {
		assertThat(Json.parse("[\"hello\", 1, true]"), is(new JsonArray().add("hello")
				.add(1).add(true)));
		
		assertThat(Json.parse("{}").asObject().isEmpty(), is(true));
		assertThat(Json.parse("[]").asArray().isEmpty(), is(true));
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testObjectIteratorRemove() throws Exception {
		Iterator<Member> iterator = new JsonObject()
				.add("string1",Json.value("10"))
				.add("string2","10")
				.iterator(),
				iterator2 = new JsonObject()
						.add("string1",Json.value("10"))
						.iterator();
		
		assertThat(iterator.hasNext(), is(true));
		Member member1 = iterator.next();
		
		assertThat(member1.getName(), is("string1"));
		assertThat(member1.getValue(), is(Json.value("10")));
		
		assertThat(iterator2.hasNext(), is(true));
		Member member2 = iterator2.next();
		
		assertThat(member1, is(member2));
		
		iterator.remove();
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testObjectNames() throws Exception {
		new JsonObject().add("boolean",true)
				.add("double",10d).add("float",10f).add("int",10)
				.add("string1",Json.value("9"))
				.add("long",10l).add("string2","10")
				.names().add("test");
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testUnmodifiableObject() throws Exception {
		JsonObject.unmodifiableObject(new JsonObject().add("boolean",true)
				.add("double",10d).add("float",10f).add("int",10)
				.add("string1",Json.value("9"))
				.add("long",10l).add("string2","10"))
		.add("test", true);
	}
	
	
}
