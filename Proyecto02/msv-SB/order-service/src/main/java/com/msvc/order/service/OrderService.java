package com.msvc.order.service;

import com.msvc.order.dto.InventarioResponse;
import com.msvc.order.dto.OrderLineItemsDto;
import com.msvc.order.dto.OrderRequest;
import com.msvc.order.model.Order;
import com.msvc.order.model.OrderLineItems;
import com.msvc.order.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Slf4j
@Service
@Transactional

public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private WebClient.Builder webClientBulder;
    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setNumeroPedido(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        order.setOrderLineItems(orderLineItemsList);

        List<String> codigoSku = order.getOrderLineItems().stream()
                        .map(OrderLineItems::getCodigoSku)
                                .collect(Collectors.toList());
        log.info("Codigo sku : {}" + codigoSku);
        InventarioResponse[] inventarioResponseArray = webClientBulder.build().get()
                        .uri("http://inventario-service/api/inventario", uriBuilder
                                -> uriBuilder.queryParam("codigoSku",codigoSku).build())
                        .retrieve()
                        .bodyToMono(InventarioResponse[].class)
                        .block();

        boolean allProductosInStock = Arrays.stream(inventarioResponseArray)
                        .allMatch(InventarioResponse::isInStock);
        if (allProductosInStock) {
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("EL producto no esta en stock");
        }

    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto){
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrecio(orderLineItemsDto.getPrecio());
        orderLineItems.setCantidad(orderLineItemsDto.getCantidad());
        orderLineItems.setCodigoSku(orderLineItemsDto.getCodigoSku());
        return orderLineItems;
    }
}
