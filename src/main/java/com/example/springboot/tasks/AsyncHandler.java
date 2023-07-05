package com.example.springboot.tasks;

import com.example.springboot.ApplicationContextHolder;
import com.example.springboot.controller.MyRedisTemplate;
import com.example.springboot.entity.AsyncFileResult;
import org.springframework.core.task.TaskExecutor;

import java.util.UUID;

public abstract class AsyncHandler {

    MyRedisTemplate redisTemplate;
    private TaskExecutor taskExecutor;

    private AsyncFileResult asyncFileResult = new AsyncFileResult();

    protected AsyncHandler() {
        taskExecutor = (TaskExecutor) ApplicationContextHolder.applicationContext.getBean("asyncTaskExecutor");
        redisTemplate = (MyRedisTemplate) ApplicationContextHolder.applicationContext.getBean("myRedisTemplate");
    }

    public abstract void doCycle(int cycle) throws Exception;

    public void beforeHandle(AsyncFileResult asyncFileResult) {
    }

    public String start() {
        String taskId = UUID.randomUUID().toString();
        taskExecutor.execute(() -> {
            int currentCycle= 0;
            try {
                beforeHandle(asyncFileResult);
                for (currentCycle = 0; currentCycle< asyncFileResult.getTotalCycle(); currentCycle++){
                    doCycle(currentCycle);
                    updateResult(taskId, currentCycle + 1, false, null);
                }
                afterHandle();
            }catch (Exception e) {
                updateResult(taskId, currentCycle+1, true, e.getMessage());
                exceptionHandle(e);
            }
        });
        return taskId;
    }
    public void  afterHandle(){
    }

    public void exceptionHandle(Exception e){
    }

    private void updateResult(String key, int currentCycle, boolean hasError, String msg) {
        asyncFileResult.setCurrentCycle(currentCycle);
        asyncFileResult.setHasError(hasError);
        asyncFileResult.setMsg(msg);
        redisTemplate.setObject(key, asyncFileResult);
    }

}
