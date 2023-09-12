/*
 * Copyright 2022-2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package example.order;

import example.order.internal.OrderInternal;
import example.shipping.ShippingManagement;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import example.StatusChangeEvent;

/**
 * @author Oliver Drotbohm
 */
@Service
@RequiredArgsConstructor
public class OrderManagement {

	private final @NonNull ApplicationEventPublisher events;
	private final @NonNull OrderInternal dependency;

	//event publisher
	@Transactional
	//@Async
	public void complete(Order order) {
		checkState(order);
		events.publishEvent(new OrderCompleted(order.getId()));

	}

	public void checkState(Order order){
		if (order.getId().hashCode() % 2 == 0){
			events.publishEvent(new StatusChangeEvent(this, "orderMangement", "inactive"));
			System.out.println("IN ORDERMANAGEMENT CHECKSTATE IF");
		} else {
			events.publishEvent(new StatusChangeEvent(this, "orderMangement", "active"));
			System.out.println("IN ORDERMANAGEMENT CHECKSTATE ELSE");
		}

	}

	public String getOrderID(Order order){
		return order.getId().toString();
	}

}
