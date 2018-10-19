package com.indihx.cache;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: RedisCacheTransfer缓存</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年1月31日 下午1:08:53</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>RedisCacheTransfer.java</p>
 * <p></p>
 */
public class RedisCacheTransfer {
	
	public void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
       RedisCache.setJedisConnectionFactory(jedisConnectionFactory);
    }

}
