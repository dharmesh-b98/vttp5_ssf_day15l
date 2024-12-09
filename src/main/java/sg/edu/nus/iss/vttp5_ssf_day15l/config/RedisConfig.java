package sg.edu.nus.iss.vttp5_ssf_day15l.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import sg.edu.nus.iss.vttp5_ssf_day15l.constant.*;;

@Configuration
public class RedisConfig {
        // slide 17

    @Value("${spring.data.redis.host}")
    private String redisHost;// host connects to

    @Value("${spring.data.redis.port}") //defaults to 6379
    private Integer redisPort;

    @Value("${spring.data.redis.username}")
    private String redisUsername;

    @Value("${spring.data.redis.password}")
    private String redisPassword;


    // slide 18

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() { //redis configuration
        RedisStandaloneConfiguration rsc = new RedisStandaloneConfiguration();
        rsc.setHostName(redisHost); //config of hostname
        rsc.setPort(redisPort); //config of redis port

        if(redisUsername.trim().length() > 0) {
            rsc.setUsername(redisUsername); //config of redis username
            rsc.setPassword(redisPassword); //config of redis password
        }

        JedisClientConfiguration jcc = JedisClientConfiguration.builder().build(); // redis client configuration
        JedisConnectionFactory jcf = new JedisConnectionFactory(rsc, jcc); //connecting redis and redis client
        jcf.afterPropertiesSet();

        return jcf;
    }

    @Bean(Constants.template01)
    public RedisTemplate<String, String> redisObjectTemplate01() {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());

        return template;
    }


    @Bean(Constants.template02)
    public RedisTemplate<String, Object> redisObjectTemplate02() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());

        return template;
    }
}
