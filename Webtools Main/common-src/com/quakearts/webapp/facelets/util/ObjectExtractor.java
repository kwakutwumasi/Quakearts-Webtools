package com.quakearts.webapp.facelets.util;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.event.AbortProcessingException;

public class ObjectExtractor {
	
	public static String extractString(ValueExpression stringExpression, ELContext ctx){
		if(stringExpression==null)
			return null;
		
		Object stringValue = stringExpression.getValue(ctx);
		if(stringValue == null)
			return null;
		else
			return stringValue.toString();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Collection extractCollection(ValueExpression collectionExpression, ELContext ctx){
		if(collectionExpression==null)
			return null;

		Object collectionValue = collectionExpression.getValue(ctx);
		if(collectionValue == null)
			return null;
		else{
			if(collectionValue instanceof Collection)
				return (Collection) collectionValue;
			else{
				Collection col = new ArrayList();
				col.add(collectionValue);
				return col;
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List extractList(ValueExpression listExpression, ELContext ctx){
		if(listExpression==null)
			return null;

		Object listValue = listExpression.getValue(ctx);
		if(listValue == null)
			return null;
		else{
			if(listValue instanceof List)
				return (List) listValue;
			else{
				List col = new ArrayList();
				col.add(listValue);
				return col;
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes" })
	public static Map extractMap(ValueExpression mapExpression, ELContext ctx){
		if(mapExpression==null)
			return null;

		Object mapValue = mapExpression.getValue(ctx);
		if(mapValue == null)
			return null;
		else{
			if(mapValue instanceof Map)
				return (Map) mapValue;
			else{
				throw new AbortProcessingException("Value is not of type java.util.Map");
			}
		}
	}
	
	public static boolean extractBoolean(ValueExpression booleanExpression, ELContext ctx){
		if(booleanExpression==null)
			return false;
		Object booleanValue = booleanExpression.getValue(ctx);
		if(booleanValue instanceof String){
			if(((String) booleanValue).length()<1)
				throw new AbortProcessingException("Empty string is not a valid boolean value.");
		}
		return booleanValue instanceof Boolean?((Boolean)booleanValue).booleanValue():Boolean.parseBoolean(booleanValue.toString());
	}
	
	public static Date extractDate(ValueExpression dateExpression, ELContext ctx, String attribute, String dateFormat){
		if(dateExpression==null)
			return null;

		Object dateValue = dateExpression.getValue(ctx);
		if(dateValue!=null)
		try {
			return dateValue instanceof Date?((Date)dateValue):(new SimpleDateFormat(dateFormat)).parse(dateValue.toString());
		} catch (ParseException e) {
			throw new AbortProcessingException(e);
		}
		return null;
	}

	public static long extractLong(ValueExpression longExpression, ELContext ctx){
		if(longExpression==null)
			throw new AbortProcessingException("Value cannot be null");

		Object longValue = longExpression.getValue(ctx);
		if(longValue == null)
			throw new AbortProcessingException("Value cannot be null");
		
		if(longValue instanceof Long)
			return ((Long)longValue).longValue();
		else if(longValue instanceof BigInteger){
			return ((BigInteger)longValue).longValue();
		}else{
			try{
				return Long.parseLong(longValue.toString());
			}catch(Exception e){
				throw new AbortProcessingException("Value '"+longValue+"' is not a valid long integer.",e);
			}
		}
	}

	public static int extractInteger(ValueExpression intExpression, ELContext ctx){
		if(intExpression == null)
			throw new AbortProcessingException("Value cannot be null");

		Object intValue = intExpression.getValue(ctx);
		if(intValue == null)
			throw new AbortProcessingException("Value cannot be null");
		
		if(intValue instanceof Integer)
			return ((Integer)intValue).intValue();
		else if(intValue instanceof BigInteger){
			return ((BigInteger)intValue).intValue();
		}else{
			try{
				return Integer.parseInt(intValue.toString());
			}catch(Exception e){
				throw new AbortProcessingException("Value '"+intValue+"' is not a valid integer",e);
			}
		}
	}
	
	public static double extractDouble(ValueExpression doubleExpression, ELContext ctx){
		if(doubleExpression == null)
			throw new AbortProcessingException("Value cannot be null");

		Object doubleValue = doubleExpression.getValue(ctx);
		if(doubleValue == null)
			throw new AbortProcessingException("Value cannot be null");
		
		if(doubleValue instanceof Integer)
			return ((Integer)doubleValue).doubleValue();
		else if(doubleValue instanceof BigInteger)
			return ((BigInteger)doubleValue).doubleValue();
		else if(doubleValue instanceof Double)
			return (Double)doubleValue;
		else{
			try{
				return Double.parseDouble(doubleValue.toString());
			}catch(Exception e){
				throw new AbortProcessingException("Value '"+doubleValue+"' is not a valid integer",e);
			}
		}
	}
	
}
