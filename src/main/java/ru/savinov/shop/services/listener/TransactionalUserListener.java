package ru.savinov.shop.services.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
public class TransactionalUserListener {

    @TransactionalEventListener
    public void handleUserTransactionalEvent(TransactionalEvent event) {
        log.info("transactional event type: {}, changed entity before: {}, after: {}", event.getType(),
                event.getBeforeCommit(), event.getAfterCommit());
    }

}