package com.indihx.cache;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;  
import org.springframework.dao.DataAccessException;  
import org.springframework.data.redis.connection.RedisConnection;  
import org.springframework.data.redis.core.RedisCallback;  
import org.springframework.data.redis.core.RedisTemplate; 

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: SpringRedisCache缓存</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年1月31日 下午1:08:53</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>SpringRedisCache.java</p>
 * <p></p>
 */
public class SpringRedisCache implements Cache {
	
	private RedisTemplate<String, Object> redisTemplate;    
	private String name;    
	public RedisTemplate<String, Object> getRedisTemplate() {  
		return redisTemplate;    
	}  
	     
	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {  
		this.redisTemplate = redisTemplate;
	}  
	     
	public void setName(String name) {  
		this.name = name;    
	}  

	@Override
	public void clear() {
		System.out.println("clear key");  
        redisTemplate.execute(new RedisCallback<String>() {    
             public String doInRedis(RedisConnection connection)    
                     throws DataAccessException {    
               connection.flushDb();    
                 return "ok";    
            }    
        });    
	}

	@Override
	public void evict(Object key) {
		System.out.println("del key");  
		final String keyf = key.toString();    
		redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection)    
	             throws DataAccessException {    
				return connection.del(keyf.getBytes());    
			}    
	   });    
	}

	@Override
	public ValueWrapper get(Object key) {
		System.out.println("get key");  
	    final String keyf =  key.toString();  
	    Object object = null;  
	    object = redisTemplate.execute(new RedisCallback<Object>() {  
	    	public Object doInRedis(RedisConnection connection)    
	                throws DataAccessException {
	    		byte[] key = keyf.getBytes();  
	    		byte[] value = connection.get(key);  
	    		if (value == null) {
	    			return null;  
	    		}  
	    		return toObject(value);  
	        }  
	    });  
	    return (object != null ? new SimpleValueWrapper(object) : null);  
	}

	@Override
	public <T> T get(Object arg0, Class<T> arg1) {
		return null;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Object getNativeCache() {
		return this.redisTemplate;
	}

	@Override
	public void put(Object key, Object value) {
		System.out.println("**************key"+key+"===value:"+value); 
	    System.out.println("put key");  
	    final String keyf = key.toString();    
	    final Object valuef = value;    
	    final long liveTime = 86400;    
	    redisTemplate.execute(new RedisCallback<Long>() {
	    	public Long doInRedis(RedisConnection connection)    
	    			throws DataAccessException {
	    		byte[] keyb = keyf.getBytes();    
	            byte[] valueb = toByteArray(valuef);    
	            connection.set(keyb, valueb);    
	            if (liveTime > 0) {
	            	connection.expire(keyb, liveTime);    
	            }    
	            return 1L;    
	    	}    
	    }); 
	}

	@Override
	public ValueWrapper putIfAbsent(Object arg0, Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	private byte[] toByteArray(Object obj) {    
		byte[] bytes = null;    
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();    
	    try {    
	    	ObjectOutputStream oos = new ObjectOutputStream(bos);    
	        oos.writeObject(obj);    
	        oos.flush();    
	        bytes = bos.toByteArray();    
	        oos.close();    
	        bos.close();    
	    }catch (IOException ex) {    
	    	ex.printStackTrace();    
	    }    
	    return bytes;    
	}    

	private Object toObject(byte[] bytes) {
		Object obj = null;    
	    try {  
	    	ByteArrayInputStream bis = new ByteArrayInputStream(bytes);    
            ObjectInputStream ois = new ObjectInputStream(bis);    
            obj = ois.readObject();    
            ois.close();    
            bis.close();    
	    } catch (IOException ex) {    
        	 ex.printStackTrace();    
        } catch (ClassNotFoundException ex) {    
        	ex.printStackTrace();    
        }    
	    return obj;    
	}  
}
