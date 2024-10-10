package handy

class Pedido {

    Long id_order
    Long id_user
    String descriptionCustomer
    String productCode
    BigDecimal totalSales

    static mapping = {
        id_order column: 'id_order'
    }

    static constraints = {
        id_order unique: true
    }
}