package org.andy.work.admin.security;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import net.sf.ehcache.management.CacheManager;

import org.springframework.stereotype.Service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;


@Service  
public class ValidateAttemptService{ 
	
	private LoadingCache<String, Integer> Cache;
	
    public ValidateAttemptService() { 
    	super();
    	Cache = CacheBuilder
    			.newBuilder()
    			.concurrencyLevel(10)
    			.maximumSize(1000)
    			.expireAfterWrite(1, TimeUnit.HOURS)
    			.initialCapacity(10)
    	        .build(new CacheLoader<String, Integer>() {  
    	              public Integer load(String key) {  
    	                  return 0;  
    	              }  
    	          });  
    }  
    
    public void loginSucceeded(String key) {
    	//清除此cache关于key的缓存
        Cache.invalidate(key);  
    }  
    
    public void loginFailed(String key) {  
        int attempts = 0;
        try {  
        	if(key!=null){
            attempts = Cache.get(key);}
        } catch (ExecutionException e) {  
            attempts = 0;  
        }  
        attempts++;  
        Cache.put(key, attempts);  
    }  
   
    public boolean isLock(String key){
    	try{
    		if(Cache.get(key)>=4){
    			return true;
    		}
    		else{
    			return false;
    		}
    	}
    	catch (ExecutionException e){
    	}
    	return true;
     }
    	
}
