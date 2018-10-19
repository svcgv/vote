package com.indihx.cache;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: RedisCache缓存</p>
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年1月31日 下午1:08:53</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>RedisCache.java</p>
 * <p></p>
 */
public class RedisCache implements Cache {
	
	private static JedisConnectionFactory jedisConnectionFactory;
	private final String id;
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    
    public RedisCache(final String id){
        if (id == null) {
            throw new IllegalArgumentException("cache instances require an ID");
        }
        this.id = id;
    }

	@Override
	public void clear() {
		JedisConnection connection = null;
        try {
            connection = (JedisConnection) jedisConnectionFactory.getConnection();
            connection.flushDb();
            connection.flushAll();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (connection != null) {
                connection.close();
            }
        }
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public Object getObject(Object key) {
		 Object result = null;
	        JedisConnection connection = null;
	        try {
	            connection = (JedisConnection) jedisConnectionFactory.getConnection();
	            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
	            result = serializer.deserialize(connection.get(serializer.serialize(key)));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }finally{
	            if (connection != null) {
	                connection.close();
	            }
	        }
	        return result;
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return this.readWriteLock;
	}

	@Override
	public int getSize() {
		 int result = 0;
        JedisConnection connection = null;
        try {
            connection = (JedisConnection) jedisConnectionFactory.getConnection();
            result = Integer.valueOf(connection.dbSize().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (connection != null) {
                connection.close();
            }
        }
        return result;
	}

	@Override
	public void putObject(Object key, Object value) {
		JedisConnection connection = null;
        try {
            connection = (JedisConnection) jedisConnectionFactory.getConnection();
            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
            System.out.println("**"+serializer.serialize(key));
            connection.set(serializer.serialize(key), serializer.serialize(value));
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (connection != null) {
                connection.close();
            }
        }
	}

	@Override
	public Object removeObject(Object key) {
		JedisConnection connection = null;
        Object result = null;
        try {
            connection = (JedisConnection) jedisConnectionFactory.getConnection();
            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
            result = connection.expireAt(serializer.serialize(key), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (connection != null) {
                connection.close();
            }
        }
        return result;
	}

	public static void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
        RedisCache.jedisConnectionFactory = jedisConnectionFactory;
    }
}
