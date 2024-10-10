package handy

class Pedido {

    Long id_order
    Long id_user
    String descriptionCustomer
    BigDecimal totalSales
    String productCode

    static mapping = {
        id_order column: 'id_order'
    }

    static constraints = {
        id_order unique: true
    }
}