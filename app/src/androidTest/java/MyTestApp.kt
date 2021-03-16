import com.example.popularnyarticles.system.MyApp


open class MyTestApp : MyApp() {

  var url = "http://127.0.0.1:8607"

  override fun getBaseUrl(): String {
    return url
  }
}