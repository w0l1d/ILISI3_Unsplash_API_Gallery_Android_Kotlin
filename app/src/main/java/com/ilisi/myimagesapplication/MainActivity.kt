package com.ilisi.myimagesapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ilisi.myimagesapplication.data.ImageAdapter
import com.ilisi.myimagesapplication.ui.theme.MyImagesApplicationTheme
import com.ilisi.myimagesapplication.viewmodel.MainViewModel
import android.graphics.Rect
import android.view.View

class MainActivity : ComponentActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var tvResult: TextView
    private lateinit var btnSend: Button
    private lateinit var rvImages: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        mainViewModel = MainViewModel()
        subscribe()


        tvResult = findViewById(R.id.tv_result)
        btnSend = findViewById(R.id.btn_refrech)
        rvImages = findViewById(R.id.rv_images) // Assuming you have a RecyclerView with id rv_images in your layout

        rvImages.layoutManager = LinearLayoutManager(this)
        rvImages.addItemDecoration(SpaceItemDecoration(10))

        btnSend.setOnClickListener {
            mainViewModel.getImagesData()
        }

    }



    private fun subscribe() {
        mainViewModel.isLoading.observe(this) { isLoading ->
            // Set the result text to Loading
            if (isLoading) tvResult.text = resources.getString(R.string.loading)
        }

        mainViewModel.isError.observe(this) { isError ->
            // Hide display image and set the result text to the error message
            if (isError) {
//                imgCondition.visibility = View.GONE
                tvResult.text = mainViewModel.errorMessage
            }
        }

        mainViewModel.imagesData.observe(this) { imagesData ->
            // Display weather data to the UI
            tvResult.text = "Fetching data success"
            rvImages.adapter = ImageAdapter(imagesData)
        }
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!\n",
        modifier = modifier
    )
}

//@Composable
//fun DiplayImages(modifier: Modifier = Modifier, viewModel: ImageViewModel = ImageViewModel()) {
//
//    val images = viewModel.images.value
//
//    if (images != null) {
//        for (image in images) {
//            Text(
//                text = image,
//                modifier = modifier
//            )
//        }
//    }
//}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyImagesApplicationTheme {
        Greeting("Android")
    }
}


class SpaceItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        outRect.left = space
        outRect.right = space
        outRect.bottom = space*6

        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = space
        }
    }
}