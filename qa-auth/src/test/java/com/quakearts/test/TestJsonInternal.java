package com.quakearts.test;

import static org.junit.Assert.*;

import java.io.StringReader;

import static org.hamcrest.core.Is.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.quakearts.webapp.security.jwt.internal.json.Json;
import com.quakearts.webapp.security.jwt.internal.json.JsonObject;
import com.quakearts.webapp.security.jwt.internal.json.JsonValue;
import com.quakearts.webapp.security.jwt.internal.json.ParseException;
import com.quakearts.webapp.security.jwt.internal.json.PrettyPrint;
import com.quakearts.webapp.security.jwt.internal.json.JsonObject.Member;

public class TestJsonInternal {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testValueInt() {
		assertThat(Json.value(1).asInt(), is(1));
		assertThat(Json.value(1).isNumber(), is(true));
		assertThat(Json.value(1).isArray(), is(false));
		assertThat(Json.value(1).isBoolean(), is(false));
		assertThat(Json.value(1).isFalse(), is(false));
		assertThat(Json.value(1).isNull(), is(false));
		assertThat(Json.value(1).isObject(), is(false));
		assertThat(Json.value(1).isString(), is(false));
		assertThat(Json.value(1).isTrue(), is(false));
		JsonValue value = Json.value(1);
		assertThat(value, is(value));
		assertThat(value, is(Json.value(1)));
		assertThat(value.hashCode(), is(Json.value(1).hashCode()));
		assertThat(Json.value(1).equals(null), is(false));
		assertThat(Json.value(1).equals("Test"), is(false));
	}

	@Test
	public void testValueLong() {
		assertThat(Json.value(1l).asLong(), is(1l));
		assertThat(Json.value(1l).isNumber(), is(true));
	}

	@Test
	public void testValueFloat() {
		assertThat(Json.value(1f).asFloat(), is(1f));
		assertThat(Json.value(1f).isNumber(), is(true));
	}

	@Test
	public void testValueDouble() {
		assertThat(Json.value(1d).asDouble(), is(1d));
		assertThat(Json.value(1d).isNumber(), is(true));
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testValueString() {
		assertThat(Json.value("1").asString(), is("1"));
		assertThat(Json.value("1").isString(), is(true));
		JsonValue jsonValue = Json.value("1");
		assertThat(jsonValue, is(jsonValue));
		assertThat(jsonValue, is(Json.value("1")));
		assertThat(jsonValue.hashCode(), is(Json.value("1").hashCode()));
		assertThat(jsonValue.equals(null), is(false));
		assertThat(jsonValue.equals("Test"), is(false));

		assertThat(Json.value("1").isArray(), is(false));
		assertThat(Json.value("1").isBoolean(), is(false));
		assertThat(Json.value("1").isFalse(), is(false));
		assertThat(Json.value("1").isNull(), is(false));
		assertThat(Json.value("1").isObject(), is(false));
		assertThat(Json.value("1").isNumber(), is(false));
		assertThat(Json.value("1").isTrue(), is(false));
	}

	@Test
	public void testValueBoolean() {
		assertThat(Json.value(true).asBoolean(), is(true));
		assertThat(Json.value(true).isArray(), is(false));
		assertThat(Json.value(false).isFalse(), is(true));
		assertThat(Json.value(true).isNull(), is(false));
		assertThat(Json.value(true).isObject(), is(false));
		assertThat(Json.value(true).isNumber(), is(false));
		assertThat(Json.value(true).isString(), is(false));
		assertThat(Json.value(true).isTrue(), is(true));
	}

	@Test
	public void testArray() {
		assertThat(Json.array("1").get(0).asString(), is("1"));
		assertThat(Json.array("1").set(0,Json.value("2")).get(0).asString(), is("2"));
		assertThat(Json.array("1","2").remove(0).get(0).asString(), is("2"));
		assertThat(Json.array("1","2").equals(Json.array("1","2")), is(true));
		JsonValue value = Json.array("1","2");
		assertThat(value.equals(value), is(true));
		assertThat(value.equals(null), is(false));
		assertThat(value.equals(Json.value("[\"1\",\"2\"]")), is(false));
		assertThat(Json.array("1","2").hashCode() == Json.array("1","2").hashCode(), is(true));
		assertThat(Json.array("1","2").isArray(), is(true));
		assertThat(Json.array("1","2").iterator().next().asString(), is("1"));
		assertThat(Json.array("1","2").iterator().hasNext(), is(true));
		assertThat(Json.array("1","2").size(), is(2));
		assertThat(Json.array("1","2").isEmpty(), is(false));
		assertThat(Json.array("1","2").toString(), is("[\"1\",\"2\"]"));
		
		expectedException.expect(UnsupportedOperationException.class);		
		Json.array("1","2").values().add(Json.value("3"));
	}

	@Test
	public void testArrayAdd() {
		expectedException.expect(NullPointerException.class);
		JsonValue value = null;
		Json.array("1","2").add(value);
	}

	@Test
	public void testArraySet() {
		expectedException.expect(NullPointerException.class);		
		JsonValue value = null;
		Json.array("1","2").set(1, value);
	}

	@Test
	public void testArrayIteratorRemove() {
		expectedException.expect(UnsupportedOperationException.class);		
		Json.array("1","2").iterator().remove();
	}
	
	@Test
	public void testArrayIntArray() {
		assertThat(Json.array(1,2,3).get(2).asInt(), is(3));
		assertThat(Json.array(1,2,3).set(0,5).get(0).asInt(), is(5));
	}

	@Test
	public void testArrayLongArray() {
		assertThat(Json.array(1l,2l,3l).get(1).asLong(), is(2l));
		assertThat(Json.array(1l,2l,3l).set(0,5l).get(0).asLong(), is(5l));
	}

	@Test
	public void testArrayFloatArray() {
		assertThat(Json.array(1f,2f,3f).get(0).asFloat(), is(1f));
		assertThat(Json.array(1f,2f,3f).set(0,5f).get(0).asFloat(), is(5f));
	}

	@Test
	public void testArrayDoubleArray() {
		assertThat(Json.array(1d,2d,3d).get(0).asDouble(), is(1d));
		assertThat(Json.array(1d,2d,3d).set(0,5d).get(0).asDouble(), is(5d));
	}

	@Test
	public void testArrayBooleanArray() {
		assertThat(Json.array(true,false,false).get(0).asBoolean(), is(true));
		assertThat(Json.array(true,false,false).set(2,true).get(2).asBoolean(), is(true));
	}

	@Test
	public void testArrayStringArray() {
		assertThat(Json.array("1","2","3").get(2).asString(), is("3"));
		assertThat(Json.array("1","2","3").set(2,"5").get(2).asString(), is("5"));
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testObject() {
		assertThat(Json.object().add("test", "value").toString(), is("{\"test\":\"value\"}"));
		assertThat(Json.object().add("test", "value").names().iterator().next(), is("test"));
		JsonObject object = Json.object();
		object.add("string", "string");
		
		assertThat(object, is(object));
		assertThat(object, is(Json.object().add("string", "string")));
		assertThat(object.hashCode(), is(Json.object().add("string", "string").hashCode()));
		assertThat(object.equals(null), is(false));
		assertThat(object.equals(Json.value(false)), is(false));

		Member member = Json.object().add("test", "value").iterator().next();
		Member member2 = Json.object().add("test", "value").iterator().next();
		assertThat(member, is(member));
		assertThat(member, is(member2));
		assertThat(member.hashCode(), is(member2.hashCode()));
		assertThat(member.equals(null), is(false));
		assertThat(member.equals("String"), is(false));

		assertThat(object.get("string").asString(), is("string"));
		object.set("string", "another-string");
		assertThat(object.getString("string",""), is("another-string"));
		assertThat(object.getString("void", ""), is(""));
		object.remove("string");
		assertThat(object.size(), is(0));
		assertThat(object.isEmpty(), is(true));
		object.set("value", 1);
		assertThat(object.getInt("value", 0), is(1));
		assertThat(object.getInt("void", 0), is(0));
		object.set("value", 1l);
		assertThat(object.getLong("value", 0), is(1l));
		assertThat(object.getLong("void", 0), is(0l));
		object.set("value", 1f);
		assertThat(object.getFloat("value", 0), is(1f));
		assertThat(object.getFloat("void", 0), is(0f));
		object.set("value", 1d);
		assertThat(object.getDouble("value", 0), is(1d));
		assertThat(object.getDouble("void", 0), is(0d));
		object.set("value", true);
		assertThat(object.getBoolean("value", false), is(true));
		assertThat(object.getBoolean("void", false), is(false));

	}

	@Test
	public void testObjectUnmodifiable() throws Exception {
		expectedException.expect(UnsupportedOperationException.class);
		JsonObject object = Json.object().add("test", "value");
		JsonObject unmodifiableObject = JsonObject.unmodifiableObject(object);
		
		unmodifiableObject.add("another", "value");
	}

	@Test
	public void testObjectAddNullName() throws Exception {
		expectedException.expect(NullPointerException.class);
		Json.object().add(null, "value");
	}

	@Test
	public void testObjectAddNullValue() throws Exception {
		expectedException.expect(NullPointerException.class);
		JsonValue jsonValue = null;
		Json.object().add("test", jsonValue);
	}

	@Test
	public void testUnmodifiableNullValue() throws Exception {
		expectedException.expect(NullPointerException.class);
		JsonObject jsonValue = null;
		JsonObject.unmodifiableObject(jsonValue);
	}

	@Test
	public void testObjectSetNullName() throws Exception {
		expectedException.expect(NullPointerException.class);
		Json.object().set(null, "value");
	}

	@Test
	public void testObjectRemoveNullName() throws Exception {
		expectedException.expect(NullPointerException.class);
		Json.object().remove(null);
	}
	
	public void testObjectgetNullName() throws Exception {
		expectedException.expect(NullPointerException.class);
		Json.object().get(null);
	}
	
	@Test
	public void testObjectSetNullValue() throws Exception {
		expectedException.expect(NullPointerException.class);
		JsonValue jsonValue = null;
		Json.object().set("test", jsonValue);
	}
	
	@Test
	public void testObjectIteratorRemove() throws Exception {
		expectedException.expect(UnsupportedOperationException.class);
		Json.object().add("test", "value").iterator().remove();
	}

	@Test
	public void testParseString() {
		JsonValue value = Json.parse("{\"string\":\"string\",\"number\":1.0,\"array\":[1,2,3,4,5],\"boolean\":true,\"null\":null}");
		assertThat(value.isObject(), is(true));
		JsonObject jsonObject = value.asObject();
		assertThat(jsonObject.get("string").asString(), is("string"));
		assertThat(jsonObject.get("number").asDouble(), is(1.0d));
		assertThat(jsonObject.get("array").asArray().get(0).asInt(), is(1));
		assertThat(jsonObject.get("boolean").asBoolean(), is(true));
		assertThat(jsonObject.get("null").isNull(), is(true));

	}

	@Test
	public void testParseReader() throws Exception {
		JsonValue value = Json.parse(new StringReader("{\"string\":\"string\",\"number\":1.0,\"array\":[1,2,3,4,5],\"boolean\":true,\"null\":null}"));
		assertThat(value.isObject(), is(true));
		JsonObject jsonObject = value.asObject();
		assertThat(jsonObject.get("string").asString(), is("string"));
		assertThat(jsonObject.get("number").asDouble(), is(1.0d));
		assertThat(jsonObject.get("array").asArray().get(0).asInt(), is(1));
		assertThat(jsonObject.get("boolean").asBoolean(), is(true));
		assertThat(jsonObject.get("null").isNull(), is(true));
	}
	
	@Test
	public void testReadAndWrite() throws Exception {
		JsonObject object = Json.object()
				.add("boolean-true", Json.TRUE)
				.add("boolean-false", Json.FALSE)
				.add("java-boolean", true)
				.add("null", Json.NULL)
				.add("string", "string")
				.add("double", 2d)
				.add("float", 1f)
				.add("int", 1)
				.add("long", 3l)
				.add("array", Json.array("a","b","c"));
		
		JsonValue value = Json.parse(object.toString());
		JsonObject object2 = value.asObject();
		assertThat(object, is(object2));
		assertThat(object.toString(PrettyPrint.indentWithTabs()), is("{\n" + 
				"	\"boolean-true\": true,\n" + 
				"	\"boolean-false\": false,\n" + 
				"	\"java-boolean\": true,\n" + 
				"	\"null\": null,\n" + 
				"	\"string\": \"string\",\n" + 
				"	\"double\": 2,\n" + 
				"	\"float\": 1,\n" + 
				"	\"int\": 1,\n" + 
				"	\"long\": 3,\n" + 
				"	\"array\": [\n" + 
				"		\"a\",\n" + 
				"		\"b\",\n" + 
				"		\"c\"\n" + 
				"	]\n" + 
				"}"));
		assertThat(object.toString(PrettyPrint.indentWithSpaces(3)), is("{\n" + 
				"   \"boolean-true\": true,\n" + 
				"   \"boolean-false\": false,\n" + 
				"   \"java-boolean\": true,\n" + 
				"   \"null\": null,\n" + 
				"   \"string\": \"string\",\n" + 
				"   \"double\": 2,\n" + 
				"   \"float\": 1,\n" + 
				"   \"int\": 1,\n" + 
				"   \"long\": 3,\n" + 
				"   \"array\": [\n" + 
				"      \"a\",\n" + 
				"      \"b\",\n" + 
				"      \"c\"\n" + 
				"   ]\n" + 
				"}"));
		assertThat(object.toString(PrettyPrint.singleLine()),is("{\"boolean-true\": true, \"boolean-false\": false, \"java-boolean\": true, \"null\": null, \"string\": \"string\", \"double\": 2, \"float\": 1, \"int\": 1, \"long\": 3, \"array\": [\"a\", \"b\", \"c\"]}"));
	}
	
	@Test
	public void testParseExpectation() throws Exception {
		expectedException.expect(ParseException.class);
		Json.parse("{test}");
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testParseExpectationLocation() throws Exception {
		try {
			Json.parse("{test}");
			fail("Not thrown");
		} catch (ParseException e) {
			assertThat(e.getLocation().getLine(), is(1));
			assertThat(e.getLocation().getColumn(), is(2));
			assertThat(e.getLocation().hashCode(), is(1));
			assertThat(e.getLocation(), is(e.getLocation()));
			try {
				Json.parse("{test}");
				fail("Not thrown");
			} catch (ParseException e2) {
				assertThat(e2.getLocation(), is(e.getLocation()));
			}
			assertThat(e.getLocation().equals("test"), is(false));
		}
	}
	
	@Test
	public void testWritingEscapeChars() throws Exception {
		int CONTROL_CHARACTERS_END = 0x001f+1;
		String str = new String(new char[] {(char) CONTROL_CHARACTERS_END});
		assertThat(Json.value("\"\r\n\t\\\u2028\u2029\u0012"+str).toString().toCharArray(),
				is(new char[]{'\"', '\\', '"', '\\', 'r', '\\', 'n', '\\', 't', '\\', 
						'\\', '\\', 'u', '2', '0', '2', '8', '\\', 'u', '2', '0', '2', '9',
						'\\', 'u', '0', '0', '1', '2',(char) CONTROL_CHARACTERS_END , '"'}));
	}
	
	@Test
	public void testJSonValues() throws Exception {
		try {
			Json.value(true).asArray();
			fail("Did not throw exception");
		} catch (UnsupportedOperationException e) {
		}

		try {
			Json.value(true).asString();
			fail("Did not throw exception");
		} catch (UnsupportedOperationException e) {
		}

		try {
			Json.value(true).asDouble();
			fail("Did not throw exception");
		} catch (UnsupportedOperationException e) {
		}

		try {
			Json.value(true).asFloat();
			fail("Did not throw exception");
		} catch (UnsupportedOperationException e) {
		}

		try {
			Json.value(true).asLong();
			fail("Did not throw exception");
		} catch (UnsupportedOperationException e) {
		}

		try {
			Json.value(true).asInt();
			fail("Did not throw exception");
		} catch (UnsupportedOperationException e) {
		}
		
		try {
			Json.value(1).asObject();
			fail("Did not throw exception");
		} catch (UnsupportedOperationException e) {
		}
		
		try {
			Json.value(1).asArray();
			fail("Did not throw exception");
		} catch (UnsupportedOperationException e) {
		}

		try {
			Json.value(1).asString();
			fail("Did not throw exception");
		} catch (UnsupportedOperationException e) {
		}

		try {
			Json.value(1).asBoolean();
			fail("Did not throw exception");
		} catch (UnsupportedOperationException e) {
		}
		
		try {
			Json.value(1).asObject();
			fail("Did not throw exception");
		} catch (UnsupportedOperationException e) {
		}
		
		try {
			Json.array(1).asInt();
			fail("Did not throw exception");
		} catch (UnsupportedOperationException e) {
		}

		try {
			Json.array(1).asString();
			fail("Did not throw exception");
		} catch (UnsupportedOperationException e) {
		}

		try {
			Json.array(1).asDouble();
			fail("Did not throw exception");
		} catch (UnsupportedOperationException e) {
		}

		try {
			Json.array(1).asFloat();
			fail("Did not throw exception");
		} catch (UnsupportedOperationException e) {
		}

		try {
			Json.array(1).asLong();
			fail("Did not throw exception");
		} catch (UnsupportedOperationException e) {
		}

		try {
			Json.array(1).asBoolean();
			fail("Did not throw exception");
		} catch (UnsupportedOperationException e) {
		}
		
		try {
			Json.array(1).asObject();
			fail("Did not throw exception");
		} catch (UnsupportedOperationException e) {
		}
		
		try {
			Json.object().asInt();
			fail("Did not throw exception");
		} catch (UnsupportedOperationException e) {
		}

		try {
			Json.object().asString();
			fail("Did not throw exception");
		} catch (UnsupportedOperationException e) {
		}

		try {
			Json.object().asDouble();
			fail("Did not throw exception");
		} catch (UnsupportedOperationException e) {
		}

		try {
			Json.object().asFloat();
			fail("Did not throw exception");
		} catch (UnsupportedOperationException e) {
		}

		try {
			Json.object().asLong();
			fail("Did not throw exception");
		} catch (UnsupportedOperationException e) {
		}

		try {
			Json.object().asBoolean();
			fail("Did not throw exception");
		} catch (UnsupportedOperationException e) {
		}
		
		try {
			Json.object().asArray();
			fail("Did not throw exception");
		} catch (UnsupportedOperationException e) {
		}
	}

}
