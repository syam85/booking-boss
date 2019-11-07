package com.bookingboss.app.repository;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookingboss.app.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	Collection<Product> findAllByEventIdAndEventTimestampAndIdIn(String eventId, Date date, Collection<Long> ids);

}
