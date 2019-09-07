package cn.com.cybertech.sdly;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class TestListener implements SmartApplicationListener {
    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return false;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {

    }

    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return false;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
