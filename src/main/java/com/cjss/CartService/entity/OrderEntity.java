package com.cjss.CartService.entity;

import com.cjss.CartService.util.CustomCustomerIdGenerator;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name =  "orders_tab")
public class OrderEntity {

    @Id
    @GenericGenerator(name = "order_code_gen", strategy = "com.cjss.CartService.util.CustomCustomerIdGenerator", parameters = {@org.hibernate.annotations.Parameter(name = CustomCustomerIdGenerator.VALUE_PREFIX_PARAMETER, value = "ORDER"), @org.hibernate.annotations.Parameter(name = CustomCustomerIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d")})
    @GeneratedValue(generator = "order_code_gen", strategy = GenerationType.IDENTITY)
    private String orderCode;

    public OrderEntity(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    private String orderStatus;
/*   @JsonManagedReference
    public List<ItemsEntity> getItemsOrderedList() {
        return itemsOrderedList;
    }
    @JsonManagedReference
    public ShippingAddressEntity getShippingAddressEntity() {
        return shippingAddressEntity;
    }
    @JsonManagedReference
    public BillingAddressEntity getBillingAddressEntity() {
        return billingAddressEntity;
    }*/

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "orderEntity")
    @JsonIgnoreProperties("orderEntity")
    private List<ItemsEntity> itemsOrderedList;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "ShippingAddressId",referencedColumnName = "ShippingAddressId")
    private ShippingAddressEntity shippingAddressEntity;

    @OneToOne( cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "BillingAddressId",referencedColumnName = "BillingAddressId")
    private BillingAddressEntity billingAddressEntity;

}
