package com.java.email.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.email.entity.ProcessedEventEntity;

public interface ProcessedEventRepository extends JpaRepository<ProcessedEventEntity, Long>{

	ProcessedEventEntity findByMessageId(String messageId);

}
