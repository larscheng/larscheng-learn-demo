package com.redis.queue.config;

import org.apache.commons.lang3.SerializationException;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * @author zhengql
 * @date 2018/12/11 16:00
 * @return
 */
@Configuration
//@EnableCaching
public class RedisConfiguration extends CachingConfigurerSupport {

	@Value("${spring.redis.host}")
	private String host;
	@Value("${spring.redis.port}")
	private String port;
	@Value("${spring.redis.database}")
	private String databaseIndex;
	@Value("${spring.redis.password}")
	private String password;
	@Value("${spring.redis.timeout}")
	private int timeout;
	@Value("${spring.redis.pool.max-idle}")
	private int maxIdle;
	@Value("${spring.redis.pool.max-wait}")
	private long maxWaitMillis;
	@Value("${spring.redis.pool.max-active}")
	private int maxActive;


	@Bean
	public JedisPool redisPoolFactory() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(maxIdle);
        config.setMaxTotal(maxActive);
        config.setMaxWaitMillis(maxWaitMillis);
		return new JedisPool(config,host, Integer.parseInt(port),100);
	}

	/**
	 * 连接池配置信息
	 */
	@Bean
	public JedisPoolConfig jedisPoolConfig(){
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		//最大连接数
		jedisPoolConfig.setMaxTotal(100);
		//最小空闲连接数
		jedisPoolConfig.setMinIdle(20);
		//当池内没有可用连接时，最大等待时间
		jedisPoolConfig.setMaxWaitMillis(10000);
		//其他属性可以自行添加
		return jedisPoolConfig;
	}



	/**
	 * jedis连接工厂
	 * @param
	 * @return
	 */
	@Bean
	public RedisConnectionFactory redisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration ();
		redisStandaloneConfiguration.setHostName(host);
		redisStandaloneConfiguration.setPort(Integer.parseInt(port));
		redisStandaloneConfiguration.setDatabase(Integer.parseInt(databaseIndex));
		redisStandaloneConfiguration.setPassword(password);

		//获得默认的连接池构造器(怎么设计的，为什么不抽象出单独类，供用户使用呢)
		JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpcb =
				(JedisClientConfiguration.JedisPoolingClientConfigurationBuilder)JedisClientConfiguration.builder();
		//指定jedisPoolConifig来修改默认的连接池构造器（真麻烦，滥用设计模式！）
		jpcb.poolConfig(jedisPoolConfig);
		//通过构造器来构造jedis客户端配置
		JedisClientConfiguration jedisClientConfiguration = jpcb.build();
		//单机配置 + 客户端配置 = jedis连接工厂
		return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
	}

	@Bean
	@Override
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method, Object... params) {
				if (params.length == 0) {
					return Constants.CACHE_KEY_PREFIX + method.getName();
				}
				if (params.length == 1) {
					Object param = params[0];
					if (param != null && !param.getClass().isArray()) {
						return Constants.CACHE_KEY_PREFIX + method.getName() + "_" + param;
					}
				}
				return Constants.CACHE_KEY_PREFIX + method.getName() + " [" + StringUtils.arrayToCommaDelimitedString(params) + "]";
			}
		};
	}


	@Bean(name = "redisTemplate")
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(factory);
		//redisTemplate.setEnableTransactionSupport(true);
		RedisSerializer stringSerializer = new StringRedisSerializer();
		redisTemplate.setKeySerializer(stringSerializer);
		redisTemplate.setValueSerializer(stringSerializer);
		redisTemplate.setHashKeySerializer(stringSerializer);
		redisTemplate.setHashValueSerializer(stringSerializer);
		redisTemplate.setValueSerializer(new RedisSerializer<Object>() {
			@Override
			public byte[] serialize(Object object) throws SerializationException {

				if (object == null) {
					return new byte[0];
				}
				if (!(object instanceof Serializable)) {
					throw new IllegalArgumentException("RedisSerializer.serialize requires a Serializable payload "
							+ "but received an object of type [" + object.getClass().getName() + "]");
				}
				return SerializationUtils.serialize((Serializable) object);
			}

			@Override
			public Object deserialize(byte[] bytes) throws SerializationException {
				if (bytes == null || bytes.length == 0) {
					return null;
				}
				return SerializationUtils.deserialize(bytes);
			}
		});

		redisTemplate.afterPropertiesSet();
		return redisTemplate;

	}



}
