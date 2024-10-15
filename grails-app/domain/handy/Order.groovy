package handy

class Order {

    Long id
    Long id_order
    Long id_user
    String descriptionCustomer
    String productCode
    BigDecimal totalSales

    static mapping = {
        version false
        table 'order_table'
    }

    static constraints = {
        id_order unique: true
    }
}