package com.example.foodiemeetup.ViewModels

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodiemeetup.authentication.LoginRepository
import com.example.foodiemeetup.models.AvailableMatchesResponseModel
import com.example.foodiemeetup.models.StringResponseModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeMatchScreenViewModel() : ViewModel() {

    private val repository = LoginRepository()

    var aMatches: List<AvailableMatchesResponseModel> = listOf(AvailableMatchesResponseModel())
    var isJoinButtonShown by mutableStateOf(false)

    fun getAvailableMatches(token: String, context: Context, placeName: String, takePreferencesIntoAccount: String,  onResponse: (List<AvailableMatchesResponseModel>) -> Unit){
        viewModelScope.launch {
            val call: Call<List<AvailableMatchesResponseModel>> = repository.getAvailableMatches(token, takePreferencesIntoAccount, placeName)
            call.enqueue(object : Callback<List<AvailableMatchesResponseModel>> {
                override fun onResponse(
                    call: Call<List<AvailableMatchesResponseModel>>,
                    response: Response<List<AvailableMatchesResponseModel>>
                ) {
                    if (response.isSuccessful) {
                        //response.body()?.let { aMatches = it }
                        val responseBody = response.body()
                        aMatches = responseBody as List<AvailableMatchesResponseModel>
                        onResponse(aMatches)
                    } else {
                        val responseBody = response.errorBody()
                        val message = responseBody?.string()
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<AvailableMatchesResponseModel>>, t: Throwable) {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }

    fun postAddUserToMatch(token: String, context: Context, matchId: Int) {
        val call: Call<StringResponseModel> = repository.postAddUserToMatch(token, matchId)
        call.enqueue(object : Callback<StringResponseModel?> {
            override fun onResponse(
                call: Call<StringResponseModel?>,
                response: Response<StringResponseModel?>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    val message = responseBody?.message
                    Toast.makeText(context, "Response:" + message, Toast.LENGTH_SHORT).show()
                } else {
                    val responseBody = response.errorBody()
                    val message = responseBody?.string()
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<StringResponseModel?>, t: Throwable) {

                Toast.makeText(context, "See joined event in MyEvents tab", Toast.LENGTH_SHORT)
                    .show()
            }

        })

    }}