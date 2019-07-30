package org.zt.test.queue;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadFactory;

import org.zt.sweet.utils.stat.Stats;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.ExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

/**
 * 为什么disruptor高性能：
 * 1.环形数据结构：避免垃圾收集，采用数组而非链表。数组对处理器的缓存机制更友好
 * 2.元素位置定位：通过位运算加快定位
 * 3.无锁设计：每个生产者或消费者，会先申请可操作性元素的数组位置，申请到之后，直接读写，整个过程基于CAS ，保证线程安全。
 * @author Ternence
 * @date 2019年7月25日
 */
public class DisruptorTest {
    /**
     * 消息事件类
     */
    public static class MessageEvent{
        /**
         * 原始消息
         */
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    /**
     * 消息事件工厂类
     */
    public static class MessageEventFactory implements EventFactory<MessageEvent>{
        @Override
        public MessageEvent newInstance() {
            return new MessageEvent();
        }
    }

    /**
     * 消息转换类，负责将消息转换为事件
     */
    public static class MessageEventTranslator implements EventTranslatorOneArg<MessageEvent,String> {
        @Override
        public void translateTo(MessageEvent messageEvent, long l, String s) {
            messageEvent.setMessage(s);
        }
    }

    /**
     * 消费者线程工厂类
     */
    public static class MessageThreadFactory implements ThreadFactory{
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r,"Simple Disruptor Test Thread");
        }
    }

    /**
     * 消息事件处理类，这里只打印消息
     */
    public static class MessageEventHandler implements EventHandler<MessageEvent>{
        @Override
        public void onEvent(MessageEvent messageEvent, long l, boolean b) throws Exception {
//            System.out.println(messageEvent.getMessage());
        }
    }

    /**
     * 异常处理类
     */
    public static class MessageExceptionHandler implements ExceptionHandler<MessageEvent>{
        @Override
        public void handleEventException(Throwable ex, long sequence, MessageEvent event) {
            ex.printStackTrace();
        }

        @Override
        public void handleOnStartException(Throwable ex) {
            ex.printStackTrace();

        }

        @Override
        public void handleOnShutdownException(Throwable ex) {
            ex.printStackTrace();

        }
    }

    /**
     * 消息生产者类
     */
    public static class MessageEventProducer{
        private RingBuffer<MessageEvent> ringBuffer;

        public MessageEventProducer(RingBuffer<MessageEvent> ringBuffer) {
            this.ringBuffer = ringBuffer;
        }

        /**
         * 将接收到的消息输出到ringBuffer
         * @param message
         */
        public void onData(String message){
            EventTranslatorOneArg<MessageEvent,String> translator = new MessageEventTranslator();
            ringBuffer.publishEvent(translator,message);
        }
    }

    public static void main(String[] args) {
        String message = "Hello Disruptor!";
        int ringBufferSize = 1024;//必须是2的N次方
        Disruptor<MessageEvent> disruptor = new Disruptor<MessageEvent>(new MessageEventFactory(),ringBufferSize,new MessageThreadFactory(),ProducerType.SINGLE,new BlockingWaitStrategy());
        disruptor.handleEventsWith(new MessageEventHandler());
        disruptor.setDefaultExceptionHandler(new MessageExceptionHandler());
        RingBuffer<MessageEvent> ringBuffer = disruptor.start();
        MessageEventProducer producer = new MessageEventProducer(ringBuffer);
        Stats.start(new Callable() {

			@Override
			public Object call() throws Exception {
				producer.onData(message);
				return null;
			}
        	
        }, 400);
        
    }
}