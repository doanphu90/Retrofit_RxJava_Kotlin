package com.kotlin.retrofit_rxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.retrofit_rxjava.adapter.WeartherAdapter
import com.kotlin.retrofit_rxjava.model.RequestWeather
import com.kotlin.retrofit_rxjava.retrofit.ApiClient
import com.kotlin.retrofit_rxjava.retrofit.ApiService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var apiService: ApiService? = null
    var compositeDisposable = CompositeDisposable()
    var adapter: WeartherAdapter? = null
    var liWearther: ArrayList<RequestWeather> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiService = ApiClient.getClient(this)?.create(ApiService::class.java)

        adapter = WeartherAdapter(liWearther, this)
        rv_wearther.setHasFixedSize(true)
        rv_wearther.layoutManager = LinearLayoutManager(this)
        rv_wearther.adapter = adapter

        fetchWeatchFromService()
    }

    fun fetchWeatchFromService() {
        compositeDisposable.add(
            apiService
                ?.fetchWearther("Hồ Chí Minh")
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeWith(object : DisposableSingleObserver<RequestWeather?>() {
                    override fun onSuccess(t: RequestWeather) {
                        Log.d("DoanPhu", "Data: " + t)
                        adapter?.updateData(t)
                    }

                    override fun onError(e: Throwable) {
                        Log.e("DoanPhu", "Data: " + e.message)
                    }
                })!!
        )
    }
}
