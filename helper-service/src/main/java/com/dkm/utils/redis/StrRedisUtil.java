package com.dkm.utils.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.nio.charset.Charset;

public class StrRedisUtil {
	private static final Charset UTF8CHARSET=Charset.forName("utf8");
	
	/** 短信验证码key */
	public static final String VERI_CODE_REDIS_KEY = "c:g:u:vc:";
	/** 图形验证码key */
	public static final String IMG_VERICODE_REDIS_KEY = "c:g:u:ic:";
	/** 邀请吗key */
	public static final String USER_SCENEID_REDIS_KEY = "c:g:u:sceneid:";
	
	public static boolean setnx(RedisTemplate<String, String> redis, final String key, final String value) {
		return redis.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.setNX(key.getBytes(UTF8CHARSET), value.getBytes(UTF8CHARSET));
			}
		});
	}
	
	public static boolean set(RedisTemplate<String, String> redis, final String key, final Object value){
		String v;
		/*if (value instanceof BaseReq) {*/
			v= JSON.toJSONString(value);
		/*}else{
			v=value.toString();
		}*/
		final String json=v;
		return redis.execute(new RedisCallback<Boolean>() {

			@Override
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				
				connection.set(key.getBytes(UTF8CHARSET), json.getBytes(UTF8CHARSET));
				return true;
			}
			
		});
	}
	
	public static String get(RedisTemplate<String, String> redis, final String key){
		return redis.execute(new RedisCallback<String>() {

			@Override
			public String doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte [] data =connection.get(key.getBytes(UTF8CHARSET));
				if (data == null) {
					return null;
				}
				return new String(data, UTF8CHARSET);
			}
		});
	}

	public static byte[] getByte(RedisTemplate<String, byte[]> redis, final String key){
		return redis.execute(new RedisCallback<byte[]>() {

			@Override
			public byte[] doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte [] data =connection.get(key.getBytes(UTF8CHARSET));
				if (data == null) {
					return null;
				}
				return data;
			}
		});
	}
	
	public static boolean setEx(RedisTemplate<String, String> redis, final String key, final int expire, final Object value){
		String v;
		if (value instanceof BaseReq) {
			v= JSON.toJSONString(value);
		}else{
			v=value.toString();
		}
		final String json=v;
		return redis.execute(new RedisCallback<Boolean>() {

			@Override
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.setEx(
						key.getBytes(UTF8CHARSET),
						expire, json.getBytes(UTF8CHARSET));
				return true;
			}
			
		});
	}
	/*
	*//**
	 * 生成短信验证码
	 * @param redis
	 * @param key
	 * @param
	 * @param
	 * @return
	 *//*
	public static boolean setSMSEx(RedisTemplate<String, String> redis, final String key, final int expire, final Object value){
		String v;
		if (value instanceof String) {
			v=value.toString();
		}else{
			v= JSONObject.toJSONString(value);
		}
		final String json=v;
		return redis.execute(new RedisCallback<Boolean>() {

			@Override
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				boolean exist = connection
						.exists(key.getBytes(UTF8CHARSET));
				if (exist) {
					// 验证码已生成！
					throw AMPException.getException(120002);
				}
				connection.setEx(
						key.getBytes(UTF8CHARSET),
						expire, json.getBytes(UTF8CHARSET));
				return true;
			}
		});
	}*/
	
	public static Boolean exists(RedisTemplate<String, String> redis, final String key){
		return redis.execute(new RedisCallback<Boolean>() {

			@Override
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				return connection.exists(key.getBytes(UTF8CHARSET));
			}
		});
	}
	
	public static Long del(RedisTemplate<String, String> redis, final String key){
		return redis.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection)
					throws DataAccessException {
				return connection.del(key.getBytes(UTF8CHARSET));
			}
		});
	}
	
	public static String incr(RedisTemplate<String, String> redis, final String key){
		return redis.execute(new RedisCallback<String>() {

			@Override
			public String doInRedis(RedisConnection connection)
					throws DataAccessException {
				return connection.incr(key.getBytes(UTF8CHARSET)).toString();
			}
		});
	}
	
}
