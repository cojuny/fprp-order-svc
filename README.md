## Endpoints

Create an order
    `POST   /orders`
```json
{
    "customerId": "C1",
    "items": [
        { "productId": "P100", "quantity": 2 },
        { "productId": "P200", "quantity": 1 }
    ]
}
```

See all orders
    `GET    /orders`

See order by id
    `GET    /orders/{id}`

See order by customer ID
    `GET    /orders?customerId=C1`

Also see swagger for more info
    `/swagger-ui/index.html`