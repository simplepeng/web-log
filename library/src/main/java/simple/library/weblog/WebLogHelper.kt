package simple.library.weblog

import android.content.Context
import android.net.wifi.WifiManager

object WebLogHelper {

    fun getIpAddress(context: Context): String {
        val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val wifiInfo = wifiManager.connectionInfo
        val ipAddress = wifiInfo.ipAddress
        return intToIp(ipAddress)
    }

    private fun intToIp(ipInt: Int): String {
        return (ipInt and 0xFF).toString() + "." +
                (ipInt shr 8 and 0xFF) + "." +
                (ipInt shr 16 and 0xFF) + "." +
                (ipInt shr 24 and 0xFF)
    }

}