package com.gugu.utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.type.JavaType;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class MongoBeanUtil {

	private static Logger logger = Logger.getLogger(MongoBeanUtil.class);

	/**
	 * ��ʵ��bean����ת����DBObject
	 * 
	 * @param bean
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static <T> DBObject bean2DBObject(T bean)
			throws IllegalArgumentException, IllegalAccessException {
		if (bean == null) {
			return null;
		}
		DBObject dbObject = new BasicDBObject();
		// ��ȡ�����Ӧ���е�����������
		Field[] fields = bean.getClass().getDeclaredFields();
		for (Field field : fields) {
			// ��ȡ������
			String varName = field.getName();
			// �޸ķ��ʿ���Ȩ��
			boolean accessFlag = field.isAccessible();
			if (!accessFlag) {
				field.setAccessible(true);
			}
			Object param = field.get(bean);
			if (param == null) {
				continue;
			} else if (param instanceof Integer) {// �жϱ���������
				int value = ((Integer) param).intValue();
				dbObject.put(varName, value);
			} else if (param instanceof String) {
				String value = (String) param;
				dbObject.put(varName, value);
			} else if (param instanceof Double) {
				double value = ((Double) param).doubleValue();
				dbObject.put(varName, value);
			} else if (param instanceof Float) {
				float value = ((Float) param).floatValue();
				dbObject.put(varName, value);
			} else if (param instanceof Long) {
				long value = ((Long) param).longValue();
				dbObject.put(varName, value);
			} else if (param instanceof Boolean) {
				boolean value = ((Boolean) param).booleanValue();
				dbObject.put(varName, value);
			} else if (param instanceof Date) {
				Date value = (Date) param;
				dbObject.put(varName, value);
			}
			// �ָ����ʿ���Ȩ��
			field.setAccessible(accessFlag);
		}
		return dbObject;
	}

	/**
	 * ��DBObjectת����bean����
	 * 
	 * @param dbObject
	 * @param bean
	 * @return
	 */
	public static <T> T dbObject2Bean(DBObject dbObject, T bean) {
		return handle(dbObject, (Class<T>) bean.getClass(), bean);
	}

	public static <T> T handle(DBObject dbObject, Class<T> clazz, T bean) {
		if (!clazz.getName().equals("java.lang.Object")) {
			handle(dbObject, clazz.getSuperclass(), bean);
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				String fieldName = field.getName();
				Object fieldValue = dbObject.get(fieldName);
				if (fieldValue != null) {
					if (field.getType().isPrimitive()
							|| field.getType().getName()
									.equals("java.lang.String")) {// ����ǻ����������ͻ��ַ���
						try {
							BeanUtils.setProperty(bean, fieldName, fieldValue);
						} catch (IllegalAccessException e) {
							logger.warn(e.getMessage());
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							logger.warn(e.getMessage());
							e.printStackTrace();
						}

					} else {// ����Ӧ�����ַ���ȡ���� תΪ ʵ��bean ��set�� ���ص�ʵ����

						if (field.getType().getName().equals("java.util.List")) {// �����List
							Type genericType = field.getGenericType();
							ParameterizedTypeImpl ty = (ParameterizedTypeImpl) genericType;
							Type[] actualType = ty.getActualTypeArguments();
							if (actualType.length > 0) {
								try {
									JavaType javaType = JsonUtil.OM
											.getTypeFactory().constructType(
													actualType[0]);
									JavaType javaTypeInArrayList = JsonUtil.OM
											.getTypeFactory()
											.constructParametricType(
													ArrayList.class, javaType);
									Object readValue = JsonUtil.OM.readValue(
											fieldValue.toString(),
											javaTypeInArrayList);
									BeanUtils.setProperty(bean, fieldName,
											readValue);
								} catch (IOException e) {
									logger.warn(e.getMessage());
									e.printStackTrace();
								} catch (IllegalAccessException e) {
									logger.warn(e.getMessage());
									e.printStackTrace();
								} catch (InvocationTargetException e) {
									logger.warn(e.getMessage());
									e.printStackTrace();
								}

							}

						} else {// ����ʵ��ȥת�� JSON
							Type type = field.getType();
							try {
								JavaType javaType = JsonUtil.OM
										.getTypeFactory().constructType(type);
								Object readValue = JsonUtil.OM.readValue(
										fieldValue.toString(), javaType);
								BeanUtils.setProperty(bean, fieldName,
										readValue);
							} catch (JsonParseException e) {
								logger.warn(e.getMessage());
								e.printStackTrace();
							} catch (JsonMappingException e) {
								logger.warn(e.getMessage());
								e.printStackTrace();
							} catch (IOException e) {
								logger.warn(e.getMessage());
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								logger.warn(e.getMessage());
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								logger.warn(e.getMessage());
								e.printStackTrace();
							}
						}

					}
				}
			}
		}
		return bean;
	}

	public static String upperCaseFirstLetter(String value) {
		if (value != null && value.length() > 0) {
			value = value.substring(0, 1).toUpperCase() + value.substring(1);
		}
		return value;
	}
}
