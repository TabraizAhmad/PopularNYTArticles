import com.example.popularnyarticles.system.MyApp


class MyTestApp : MyApp() {

  var url = "https://127.0.0.1:8607"

  override fun getBaseUrl(): String {
    return url
  }
}