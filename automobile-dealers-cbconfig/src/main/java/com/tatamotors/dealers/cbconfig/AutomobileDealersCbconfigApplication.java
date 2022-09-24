package com.tatamotors.dealers.cbconfig;

import java.time.Duration;
import java.util.function.Consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

@SpringBootApplication
public class AutomobileDealersCbconfigApplication {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public Customizer<Resilience4JCircuitBreakerFactory> slowConfig(){
		return new Customizer<Resilience4JCircuitBreakerFactory> (){

			@Override
			public void customize(Resilience4JCircuitBreakerFactory tocustomize) {
				// TODO Auto-generated method stub
				tocustomize.configure(new Consumer<Resilience4JConfigBuilder>() {
					
					@Override
					public void accept(Resilience4JConfigBuilder builer) {
						CircuitBreakerConfig cbConfig = CircuitBreakerConfig.custom().failureRateThreshold(10).waitDurationInOpenState(Duration.ofSeconds(2)).slidingWindowSize(3).build();
						
						TimeLimiterConfig tlConfig = TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(3)).build();
						
						builer.circuitBreakerConfig(cbConfig).timeLimiterConfig(tlConfig).build();
						
					}
				}, "slow");
			}
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(AutomobileDealersCbconfigApplication.class, args);
	}

}
