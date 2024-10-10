package handy

class Pedido {

    Long id
    Long id_order
    Long id_user
    String descriptionCustomer
    String productCode
    BigDecimal totalSales

    static mapping = {
        version false
    }

    static constraints = {
        id_order unique: true
    }
}