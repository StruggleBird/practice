package org.zt.test.reactor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ReactorProgramModeTest {

	@Test
	public void testCompletable() throws InterruptedException, ExecutionException {
		int i = 0;
		CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
			System.out.println("当前线程：" + Thread.currentThread().getName());
			return i + 1;
		}).thenApplyAsync((h) -> {
			h = h + 1;
			System.out.println("当前线程：" + Thread.currentThread().getName());
			return h;
		}).whenCompleteAsync((r, ex) -> {
			System.out.println("异步获取结果：" + r + ",当前线程：" + Thread.currentThread().getName());
		});
		
		System.out.println("result:" + future.get());
	}
	
	@Test
	public void testReactor() {
		Flux.just("Hello", "World").subscribe((str) ->{
			System.out.println(str);
			System.out.println("当前线程：" + Thread.currentThread().getName());
		});
		
		Mono.fromSupplier(()->{
			System.out.println("当前线程：" + Thread.currentThread().getName());
			return 1;
		}).doOnSuccessOrError((res,ex)->{
			System.out.println("res:"+res);
		});
	}

}
