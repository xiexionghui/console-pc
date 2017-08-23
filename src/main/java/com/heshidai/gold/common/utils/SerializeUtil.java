package com.heshidai.gold.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: SerializeUtil
 * @version 1.0
 * @Desc: 序列化工具
 * @author xiaoyun.zeng
 * @date 2015-2-28上午9:29:51
 * @history v1.0
 */
public class SerializeUtil {

	private static Logger logger = LoggerFactory.getLogger(SerializeUtil.class);
	
	
	/**
	 * 序列化
	 * 
	 * @param object
	 * @return
	 */
	public static byte[] serialize(Object object) {
		if(object == null) {
			return null;
		}
		try {
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream(128);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteStream);
			objectOutputStream.writeObject(object);
			objectOutputStream.flush();
			return byteStream.toByteArray();
		} catch (Throwable e) {
			logger.error("SerializeUtil序列化异常", e);
			throw new RuntimeException("SerializeUtil序列化异常", e);
		}
	}

	/**
	 * 反序列化
	 * 
	 * @param bytes
	 * @return
	 */
	public static Object deserialize(byte[] bytes) {
		if(bytes == null) {
			return null;
		}
		try {
			ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
			ObjectInputStream objectInputStream = new ObjectInputStream(byteStream);
			return objectInputStream.readObject();
		} catch (Throwable e) {
			logger.error("SerializeUtil反序列化异常", e);
			throw new RuntimeException("SerializeUtil反序列化异常", e);
		}
	}

}
