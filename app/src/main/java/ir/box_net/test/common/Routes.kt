package ir.box_net.test.common

sealed class Routes(val routes:String){
    object HomeScreen:Routes("home_screen")
    object DetailScreen:Routes("detail_screen")
}
